
var myChart = echarts.init(document.getElementById('knowledge_graph'));
var respData = null;
var datas = [];//节点
var linkmes = [];//边


var categories = [];//节点种类（用于关系图绑定）

var dataSet = new Set();
var linkSet = new Set();



var targetStartAccountId = -1;
var targetEndAccountId = -1;
var targetTransactionId = -1;




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




//更新节点
function updateNodes() {
    datas = [];
    dataSet.clear();
    for(let i in respData.nodeList){
        if(dataSet.has(respData.nodeList[i].id)){
            continue;
        }
        dataSet.add(respData.nodeList[i].id);
        let borderColor = 'red';
        let color = 'red';
        let shadowBlur = 0;
        let symbolSize = 5;

        if(respData.nodeList[i].ml_model=="0"){//正常账户为绿色
            borderColor = 'green';
            color = 'green';
        }else if(respData.nodeList[i].ml_model=="1"){//洗钱账户为红色
            borderColor = 'red';
            color = 'red';
        }else{//未知账户为灰色
            borderColor = 'grey';
            color = 'grey';
        }

        if(respData.nodeList[i].id==targetStartAccountId||respData.nodeList[i].id==targetEndAccountId){
            shadowBlur = 10;
            symbolSize = 8;
            borderColor = "blueviolet";
        }

        let item = {
            id:respData.nodeList[i].id,
            name: respData.nodeList[i].name,
            itemType:"node",
            symbolSize: symbolSize,
            draggable: true,//加上这句话后不可点击
            itemStyle: {
                normal: {
                    color:color,
                    borderWidth: 4,
                    borderColor: borderColor,
                    shadowBlur:shadowBlur,
                    shadowColor:"blueviolet"
                }
            }
        };
        datas.push(item);
    }
}

//更新边
function updateEdges() {
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
        if(respData.relationList[i].ml_model=="0"){
            edgeColor = 'green';
        }else if(respData.relationList[i].ml_model=="1"){
            edgeColor = 'red';
        }else{
            edgeColor = 'grey';
        }
        let shadowBlur = 0;
        let width=2;
        if(respData.relationList[i].id==targetTransactionId){
            shadowBlur = 15;
            width = 5;
            edgeColor = "blueviolet";
        }
        var item = {
            id:id,
            source: getIndexByNodeId(startNode.id),
            target: getIndexByNodeId(endNode.id),
            itemType:"edge",
            lineStyle:{
                curveness: curve_list[curve_index],
                color: edgeColor,
                width: width,
                borderColor:"red",
                shadowBlur:shadowBlur,
                shadowColor: "blueviolet",
                type:lineType//solid【default】、dashed、dotted
            },
            name:name
        }
        linkmes.push(item);
        linkSet.add(startNode.id+"-"+endNode.id);
    }
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

//扩增一层邻居
function expandNodeNeighbors() {

    let tmpIdList = [];
    for(let id of dataSet){
        tmpIdList.push(id);
    }
    for(let i in tmpIdList){
        expandNodeRelation(tmpIdList[i],false);
    }

    updateNodes();
    updateEdges();
    setOprs();
}

//扩展节点关系
function expandNodeRelation(id,isUpdate) {
    $.ajax({
        url:"/getNodeRelationById",
        data:{id:id},
        async:false,
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

            if(isUpdate){
                updateNodes();
                updateEdges();
                setOprs();
            }

        }
    })
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
                    color:"yellow",
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
        }]
    };
    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();});
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
        expandNodeRelation(params.data.id,true);
    }
});