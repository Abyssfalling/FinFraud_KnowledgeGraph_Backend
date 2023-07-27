
var myChart = echarts.init(document.getElementById('knowledge_graph'));
var respData = null;
var datas = [];//节点
var linkmes = [];//边
var rightNodeNum = 0;
var rightEdgeNum = 0;
var errorNodeNum = 0;
var errorEdgeNum = 0;

//混淆矩阵（关系）
var node_tp = 0;
var node_fp = 0;
var node_fn = 0;
var node_tn = 0;

//混淆矩阵（节点）
var edge_tp = 0;
var edge_fp = 0;
var edge_fn = 0;
var edge_tn = 0;


var categories = [];//节点种类（用于关系图绑定）

var dataSet = new Set();
var linkSet = new Set();

//洗钱账户
var amlNode = null;
//洗钱交易
var amlEdge = null;




//用于判断是否有重复边
var map = new Map();
var curve_list = [0,0.2,0.4,0.6,0.8,-0.2,-0.4,-0.6,-0.8];//幅度列表

//页面加载时就加载数据
loadData();

//加载所有数据
let reLoadDataCount = 5;
function loadData() {
    $.ajax({
        url:"/getAll",
        success:function (data) {
            respData = data;
            //更新节点信息
            updateNodes();
            //更新边的信息
            updateEdges();
            //设置参数，构建图谱
            setOprs();
        },
        error:function () {
            console.log("剩余调用loadData()次数："+reLoadDataCount);
            if(reLoadDataCount>0){
                reLoadDataCount -= 1;
                loadData();
            }
        }
    });
}

//根据洗钱案例编号筛选数据
function filterDataByCaseId(caseId) {
    $.get({
        url:"/model/getCaseById",
        data:{caseId},
        success:function (data) {
            respData.nodeList = data.nodeList;
            respData.relationList = data.relationList;
            updateNodes();
            updateEdges();
            setOprs();
        }
    });
}

//根据条件筛选数据
function filterData() {
    let name = $("#filterByName").val();
    let type = $("#filterByType").val();
    $.ajax({
        url:"/filterData",
        data:{name:name,type:type},
        success:function (data) {
            respData.nodeList = data.nodeList;
            respData.relationList = data.relationList;
            updateNodes();
            updateEdges();
            setOprs();
        }
    });
}




//更新节点
function updateNodes() {
    rightNodeNum = 0;
    errorNodeNum = 0;
    node_fn = 0;
    node_fp = 0;
    node_tn = 0;
    node_tp = 0;
    datas = [];
    dataSet.clear();
    for(let i in respData.nodeList){
        if(dataSet.has(respData.nodeList[i].id)){
            continue;
        }
        dataSet.add(respData.nodeList[i].id);
        let borderColor = 'red';
        let color = 'red';
        if(amlNode==null){//没有使用模型检测
            if(respData.nodeList[i].ml_model=="0"){
                borderColor = 'green';
                color = 'green';
                rightNodeNum += 1;
            }else{
                borderColor = 'red';
                color = 'red';
                errorNodeNum += 1;
            }
        }else{//使用了模型检测
            if(amlNode[respData.nodeList[i].id]==0){
                borderColor = 'green';
                rightNodeNum += 1;
                if(respData.nodeList[i].ml_model=="0"){
                    node_tn += 1;
                    color = 'green';
                }else{
                    node_fn += 1;
                    color = 'red';
                }
            }else{
                borderColor = 'red';
                errorNodeNum += 1;
                if(respData.nodeList[i].ml_model=="0"){
                    node_fp += 1;
                    color = 'green';
                }else{
                    node_tp += 1;
                    color = 'red';
                }
            }
        }

        let item = {
            id:respData.nodeList[i].id,
            name: respData.nodeList[i].name,
            itemType:"node",
            // symbolSize: respData.nodeList[i].size,
            draggable: true,//加上这句话后不可点击
            itemStyle: {
                normal: {
                    color:color,
                    borderWidth: 4,
                    borderColor: borderColor,
                }
            }
        };
        datas.push(item);
    }
}

//更新边
function updateEdges() {
    rightEdgeNum = 0;
    errorEdgeNum = 0;
    edge_fn = 0;
    edge_fp = 0;
    edge_tn = 0;
    edge_tp = 0;
    linkmes = [];
    linkSet.clear();
    map.clear();//map主要用于判断是否有重复边
    for(let i in respData.relationList){
        let id = respData.relationList[i].id;
        let startNode = respData.relationList[i].startNode;
        let endNode = respData.relationList[i].endNode;
        let name = respData.relationList[i].name;
        let lineType = respData.relationList[i].lineType;//边的类型：实边或虚边
        let curve_index = 0;
        //存在重复的边，设置幅度
        if(map.has(startNode.id+"-"+endNode.id)||map.has(endNode.id+"-"+startNode.id)){
            curve_index = (map.get(startNode.id+"-"+endNode.id)!=undefined?
                map.get(startNode.id+"-"+endNode.id):map.get(endNode.id+"-"+startNode.id));
            curve_index += 1;
        }
        map.set(startNode.id+"-"+endNode.id,curve_index);


        let edgeColor = 'red';
        if(amlEdge==null){
            if(respData.relationList[i].ml_model=="0"){
                edgeColor = 'green';
                rightEdgeNum += 1;
            }else{
                edgeColor = 'red';
                errorEdgeNum += 1;
            }
        }else{
            if(amlEdge[respData.relationList[i].id]==0){
                edgeColor = 'green';
                rightEdgeNum += 1;
                if(respData.relationList[i].ml_model=="1"){
                    lineType = 'dotted';
                    edge_fn += 1;
                }else{
                    edge_tn += 1;
                }
            }else{
                edgeColor = 'red';
                errorEdgeNum += 1;
                if(respData.relationList[i].ml_model=="0"){
                    lineType = 'dotted';
                    edge_fp += 1;
                }else{
                    edge_tp += 1;
                }
            }
        }
        var item = {
            id:id,
            source: getIndexByNodeId(startNode.id),
            target: getIndexByNodeId(endNode.id),
            itemType:"edge",
            lineStyle:{
                curveness: curve_list[curve_index],
                color: edgeColor,
                width: 3,
                borderColor:"red",
                shadowColor: "rgb(33,245,233)",
                type:lineType//solid【default】、dashed、dotted
            },
            name:name
        }
        linkmes.push(item);
        linkSet.add(startNode.id+"-"+endNode.id);
    }
}

//扩展节点关系
function expandNodeRelation(id) {
    $.ajax({
        url:"/getNodeRelationById",
        data:{id:id},
        success:function (data) {
            //data代表返回与id节点关联的所有边（但可能会有已经存在的边）
            let newRelation = [];//表示没有重复的边
            //1、筛选出未出现的边
            for(let i in data){
                if(dataSet.has(data[i].startNode.id)&&dataSet.has(data[i].endNode.id)
                    &&(linkSet.has(data[i].startNode.id+"-"+data[i].endNode.id)||(linkSet.has(data[i].endNode.id+"-"+data[i].startNode.id)))){
                    continue;
                }
                newRelation.push(data[i]);
                linkSet.add(data[i].startNode.id+"-"+data[i].endNode.id);
            }

            //2、增加邻居节点
            for(let i in newRelation){
                let neighbor = newRelation[i].startNode;
                if(dataSet.has(neighbor.id)){
                    neighbor = newRelation[i].endNode;
                }
                if(dataSet.has(neighbor.id)){
                    continue;
                }
                dataSet.add(neighbor.id);
                respData.nodeList.push({
                    id:neighbor.id,
                    name:neighbor.name,
                    ml_model:neighbor.ml_model
                });
            }
            for(let i in newRelation){
                //1增加一条边
                respData.relationList.push({
                    id:newRelation[i].id,
                    startNode:newRelation[i].startNode,
                    endNode:newRelation[i].endNode,
                    ml_model:newRelation[i].ml_model
                });
            }
            updateNodes();
            updateEdges();
            setOprs();
        }
    })
}


//根据节点id获取节点在datas数组中的位置
//【更新边的方法会用到】
function getIndexByNodeId(id) {
    for(let i=0;i<datas.length;i++){
        if(datas[i].id==id){
            return i;
        }
    }
    return -1;
}





//重新设计参数
function setOprs() {
    option = {
        renderer:"canvas",
        color:["blueviolet","chocolate","darkcyan"
            ,"brown","deeppink","fuchsia","coral"
            ,"aqua","aquamarine","yellow","chartreuse"],
        legend: [{
            data: categories.map(function (a) {
                return a.name;
            }),
            top:20,
            textStyle:{
                color:"white"
            }
        }],
        // 提示框的配置
        tooltip: {
            formatter: function (x) {
                return x.data.des;
            }
        },
        series: [{
            type: 'graph', // 类型:关系图
            layout: 'force', //图的布局
            roam: true, // 是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移,可以设置成 'scale' 或者 'move'。设置成 true 为都开启
            // zoom:zoom,
            draggable: true,
            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [3, 10],
            emphasis: {
                focus: 'adjacency',
                lineStyle: {
                    shadowBlur:5,
                    shadowColor:"blue",
                    color:"blueviolet",
                    label:{
                        show:true
                    }
                },
                itemStyle: {
                    shadowBlur: 15,
                    shadowColor:"red",
                    label:{
                        show:true
                    }
                },
                edgeLabel: {
                    textStyle: {
                        fontSize: 15
                    },
                    show: true,
                    formatter: function (x) {
                        return x.data.name;
                    }
                },
            },
            // force: {
            //     repulsion: 5000,
            //     edgeLength: 800
            // },

            // 数据
            // focusNodeAdjacency:true,
            data: datas,
            links: linkmes,
            categories: categories,
        }],
        //悬浮的混淆矩阵
        graphic: {
            elements: [{
                type: 'rect',
                left: 'left',
                top: "top",
                shape: {
                    width: 180,
                    height: 80
                },
                style: {
                    fill: 'rgb(8,24,50)',
                    stroke: '#000',
                    lineWidth: 1,
                    text: '节点TP:'+node_tp+",\t节点FP:"+node_fp+"" +
                    "\n\n节点FN:"+node_fn+",\t节点TN:"+node_tn+"" +
                    "\n\np:"+(node_tp/(node_tp+node_fp)).toFixed(2)+",\tr:"+(node_tp/(node_tp+node_fn)).toFixed(2)
                    +",\tf:"+(2*node_tp/(2*node_tp+node_fp+node_fn)).toFixed(2),
                    textFill: 'white',
                    textPosition: 'inside',
                    fontSize: 14,
                    fontWeight: 'normal'
                },
                z: 100,
                draggable:true
            },{
                type: 'rect',
                left: 'right',
                top: "top",
                shape: {
                    width: 180,
                    height: 80
                },
                style: {
                    fill: 'rgb(8,24,50)',
                    stroke: '#000',
                    lineWidth: 1,
                    text: '关系TP:'+edge_tp+",\t关系FP:"+edge_fp+"" +
                    "\n\n关系FN:"+edge_fn+",\t关系TN:"+edge_tn+"" +
                    "\n\np:"+(edge_tp/(edge_tp+edge_fp)).toFixed(2)+",\tr:"+(edge_tp/(edge_tp+edge_fn)).toFixed(2)
                    +",\tf:"+(2*edge_tp/(2*edge_tp+edge_fp+edge_fn)).toFixed(2),
                    textFill: 'white',
                    textPosition: 'inside',
                    fontSize: 14,
                    fontWeight: 'normal'
                },
                z: 100,
                draggable:true
            }]
        }
    };
    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();});
    $("#nodeNum").text(datas.length);
    $("#edgeNum").text(linkmes.length);
    $("#rightNodeNum").text(rightNodeNum);
    $("#rightEdgeNum").text(rightEdgeNum);
    $("#errorNodeNum").text(errorNodeNum);
    $("#errorEdgeNum").text(errorEdgeNum);
    showPie(rightNodeNum,errorNodeNum);
}




/**
 * 鼠标左击事件
 */
//绑定echarts的单击事件
myChart.on('click', function(params) {
    // $.ajax({
    //     url:"/findById",
    //     data:{id:params.data.id,type:params.data.itemType},
    //     success:function (data) {
    //         data = JSON.stringify(data);
    //         data = JSON.parse(data);
    //         infos.length = 0;
    //         for (let key in data){
    //             if(data[key]==null||data[key]==""||key=="size")
    //                 continue;
    //             infos.push({key,value:data[key]});
    //         }
    //     }
    // });
});

/**
 * 鼠标右击事件
 */
//去除鼠标默认的右击事件
document.oncontextmenu = function () { return false; };
//绑定echarts的右击事件
myChart.on('contextmenu',function (params) {
    if(params.data.itemType=="node"){
        expandNodeRelation(params.data.id);
    }
});