/**
 * Created by 30947 on 2018/7/18.
 */

let nodeGraph = echarts.init($("#nodeGraph")[0]);
let pieGraph = echarts.init($("#pieGraph")[0]);





//加载洗钱案例列表
let reTryCount = 5;//5次重试次数
function loadCaseList() {
    $.get({
        url:"/model/getCaseList",
        success:function (data) {
            for(let i in data){
                caseListVO.push(data[i]);
            }
        },
        error:function () {
            console.log("剩余重试次数："+reTryCount);
            if(reTryCount>0){
                reTryCount -= 1;
                loadCaseList();
            }
        }
    });
}


function line(model) {

    $.ajax({
        url:"/readCsvByModel",
        data:{model},
        success:function (data) {
            $("#nodeModel").text(model+"模型性能");
            let eopchs = [];
            for(let i=0;i<data[0].length;i++){
                eopchs.push(i+1);
            }
            let option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['precision', 'recall', 'f1-score','v_precision', 'v_recall', 'v_f1-score'],
                    textStyle:{
                        color:"white"
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: eopchs,
                    axisLine:{
                        lineStyle:{
                            color:"white"
                        }
                    },
                    splitLine: {
                        show:false
                    }
                },
                yAxis: {
                    type: 'value',
                    axisLine:{
                        lineStyle:{
                            color:"white"
                        }
                    },
                    splitLine: {
                        show:false
                    }
                },
                series: [
                    {
                        name: 'precision',
                        type: 'line',
                        data: data[0],
                        color:"red",
                        lineStyle:{
                            width:2
                        }
                    },
                    {
                        name: 'recall',
                        type: 'line',
                        data: data[1],
                        color:"green",
                        lineStyle:{
                            width:2
                        }
                    },
                    {
                        name: 'f1-score',
                        type: 'line',
                        data: data[2],
                        color:"purple",
                        lineStyle:{
                            width:2
                        }
                    },
                    {
                        name: 'v_precision',
                        type: 'line',
                        data: data[3],
                        color:"yellow",
                        lineStyle:{
                            width:2
                        }
                    },
                    {
                        name: 'v_recall',
                        type: 'line',
                        data: data[4],
                        color:"pink",
                        lineStyle:{
                            width:2
                        }
                    },
                    {
                        name: 'v_f1-score',
                        type: 'line',
                        data: data[5],
                        color:"white",
                        lineStyle:{
                            width:2
                        }
                    },

                ]
            };
            nodeGraph.setOption(option);
            window.addEventListener('resize', function () {nodeGraph.resize();});
        }
    });



}


//饼状图
function showPie(rightNum,errorNum) {
    let option = {
        // tooltip: {
        //     trigger: 'item'
        // },
        color:['green','red'],
        legend: {
            top: '5%',
            left: 'center',
            textStyle:{
                color:"white"
            }
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center',
                    formatter: '{b}: {d}%',
                    textStyle:{
                        color:"yellow"
                    }
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    { value: rightNum, name: '正常节点' },
                    { value: errorNum, name: '洗钱节点' },
                ]
            }
        ]
    };
    pieGraph.setOption(option);
    window.addEventListener('resize', function () {pieGraph.resize();});
}