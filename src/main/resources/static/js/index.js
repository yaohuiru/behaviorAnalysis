var orderDate = "201906";
var areaName = "all";

$(function () {
    initPie();
    initMap();
    initCountBusiness(orderDate, areaName);
    inittendencyInfo();
    initcomparedInfo();
    initUserRank();
    $("#username").html(localStorage.getItem("userNum"));
    $("#city").html(localStorage.getItem("city"));
});

$("#closed").click(function(){
    window.location.href="/behavioranalysis/login.html";
})

//初始化用户发展排行信息
function initUserRank() {
    areaName=document.getElementById("userArea").value;
    console.log(areaName);
    $.ajax({
        type: 'post', //测试get，正式post
        cache: false,
        dataType: 'json',
        async: false,
        url: getRootPath_web() + "/ranking",
        data: {
            "type": "develop",
            "areaName": areaName
        },
        success: function (result) {
            console.log(result);
            //判断请求成功
            if (result.code == 1) {
                //清空子元素
                var tbody=$("#user_rank");
                tbody.innerHTML="";
                //遍历集合，初始化排行列表
                var len = 3;
                if (result.data.length<3){
                    len = result.data.length;
                }
                for (var i = 0; i < len; i++) {
                    var j = i+1;
                    tbody.append('<tr style="background-color: #d48265">> ' +
                        '<td class="tdClass" >' + j+ '</td>' +
                        '<td class="tdClass" >' + result.data[i].areaName + '</td>' +
                        '<td class="tdClass" >' + result.data[i].totalAmount + '</td>' +
                        '</tr>')
                }
            }
        },
        error: function () {
            console.error("出现异常");
        }
    });
    $.ajax({
        type: 'post', //测试get，正式post
        cache: false,
        dataType: 'json',
        async: false,
        url: getRootPath_web() + "/ranking",
        data: {
            "type": "income",
            "areaName": areaName
        },
        success: function (result) {
            console.log(result);
            //判断请求成功
            if (result.code == 1) {
                //清空子元素
                var tbody=$("#income");
                tbody.innerHTML="";
                //遍历集合，初始化排行列表
                var len = 3;
                if (result.data.length<3){
                    len = result.data.length;
                }
                for (var i = 0; i < len; i++) {
                    var j = i+1;
                    tbody.append('<tr style="background-color: #61a0a8">> ' +
                        '<td class="tdClass" >' + j + '</td>' +
                        '<td class="tdClass" >' + result.data[i].areaName + '</td>' +
                        '<td class="tdClass" >' + result.data[i].totalIncome + '</td>' +
                        '</tr>')
                }
            }
        },
        error: function () {
            console.error("出现异常");
        }
    });
}

function initCountBusiness(orderDate, areaName) {
    console.log(orderDate, areaName)
    $.ajax({
        type: 'post', //测试get，正式post
        cache: false,
        dataType: 'json',
        //contentType: 'application/json;charset=UTF-8',
        async: false,
        url: getRootPath_web() + "/countUserDevelop",
        data: {
            "orderDate": orderDate,
            "areaName": areaName
        },
        error: function () {
            console.error("出现异常");
        },
        success: function (data) {
            console.log(data);
            //console.log(data.devAmount)
            $("#countUser").html(data.devAmount);
            $("#countOrder").html(data.devAmount);
            $("#countAverage").html((data.dayTurnover).toFixed(2));
            $("#countTotal").html(data.totalTurnover);
        }
    });

}


function initPie() {
    var myChart = echarts.init(document.getElementById('pieGraph'));
    pieoption = {
        title: {
            text: '6月份业务受理情况:',
            x: '10',
            y: '10',
            textStyle: {
                fontSize: 14,
                fontWeight: 600,
                color: '#000'
            },
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)",

        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '50%',
                center: ['50%', '55%'],
                data: [
                    {value: 33, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    }
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(pieoption, true);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}

function initcomparedInfo() {
    var amount = [];
    $.ajax({
        type: 'get', //测试get，正式post
        cache: false,
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        async: false,
        url: getRootPath_web() + "/countOrderAmount",
        error: function () {
            console.error("出现异常");
        },
        success: function (data) {
            amount = data;
            console.log(data);
            //console.log(data.devAmount)
        }
    });

    var myChart = echarts.init(document.getElementById('comparedInfo'));
    var title = ['product', amount[1].month + '月', amount[1].last_month + '月'];
    var content = [];
    for (var i = 0; i < amount.length; i++) {
        var piece = [amount[i].product_name, amount[i].sum, amount[i].last_sum];
        content.push(piece);
    }
    myChart.setOption(
        {
            title: {
                text: '营业额同比交易量',
                x: 'center',
                textStyle: {
                    fontSize: 14,
                    fontWeight: 600,
                    color: '#000'
                },
            },
            color: ["#91C7AE", "#2d91df"],
            legend: {
                top: '20',
                textStyle: {
                    fontSize: 13,
                    color: '#000'
                }
            },
            tooltip: {},
            grid: {
                x: 60,
                y: 60,
                x2: 60,
                y2: 50,
                borderWidth: 1
            },
            dataZoom: [
                {   // 这个dataZoom组件，默认控制x轴。
                    type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                    height: 10,
                    bottom: 20,
                    start: 0,      // 左边在 0% 的位置。
                    end: 15         // 右边在 10% 的位置。
                },
                {
                    type: 'inside',
                    start: 0,      // 左边在 0% 的位置。
                    end: 15         // 右边在 10% 的位置。
                }
            ],
            dataset: {

                dimensions: title,
                source: content
            },
            xAxis: {
                type: 'category',
                axisLine: {
                    lineStyle: {
                        color: '#000',
                    }
                }
            },
            yAxis: {
                axisLine: {
                    lineStyle: {
                        color: '#000',
                    }
                },
                splitLine: {
                    show: 'true',
                    lineStyle: {
                        color: '#cccccc',
                    }
                },
            },
            // Declare several bar series, each will be mapped
            // to a column of dataset.source by default.
            series: [
                {type: 'bar'},
                {type: 'bar'}
            ]
        }
    );

    window.addEventListener("resize", function () {
        myChart.resize();
    });
    myChart.on('click', function (param) {
        var index = param.dataIndex;
        var startIndex = 100 * index / amount.length;
        myChart.setOption({
            dataZoom: [
                {   // 这个dataZoom组件，默认控制x轴。
                    type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                    start: startIndex - 1,      // 左边在 0% 的位置。
                    end: startIndex   // 右边在 1% 的位置。
                },
                {
                    type: 'inside',
                    start: startIndex - 1,      // 左边在 0% 的位置。
                    end: startIndex       // 右边在 10% 的位置。
                }
            ]
        });
    });
}

function inittendencyInfo() {
    var myChart = echarts.init(document.getElementById('tendencyInfo'));
    tendencyOption = {
        title : {
            text: '用户发展量走势图',
            x:'center',
            textStyle: {
                fontSize: 14,
                fontWeight: 600,
                color: '#000'
            },
        },
        legend: {
            bottom:'0',
        },
        tooltip: {

        },
        grid:{
            x:70,
            y:60,
            x2:40,
            y2:50,
            borderWidth:1
        },
        xAxis: {
            type: 'category',
            axisLine:{
                lineStyle:{
                    color:'#000',
                }
            },
            boundaryGap: false,
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value',
            axisLine:{
                lineStyle:{
                    color:'#000',
                }
            },
            splitLine:{
                show: 'true',
                lineStyle: {
                    color: '#cccccc',
                }
            },
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            itemStyle: {
                normal: { //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                            offset: 0, color: '#c23531' // 0% 处的颜色
                        }, {
                            offset: 0.4, color: '#C24F45' // 100% 处的颜色
                        }, {
                            offset: 1, color: '#ffffff' // 100% 处的颜色
                        }]
                    ), //背景渐变色
                    lineStyle: {        // 系列级个性化折线样式
                        width: 2,
                        type: 'solid',
                        color: "#c23531" //折线的颜色
                    }
                },
                emphasis: {
                    color: '#c23531',
                    lineStyle: {        // 系列级个性化折线样式
                        width: 1,
                        type: 'dotted',
                        color: "c23531"
                    }
                }
            },
            symbolSize:2, //折线点的大小
            areaStyle: {}
        }]
    };
    myChart.setOption(tendencyOption,true);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}

function getRootPath_web() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}