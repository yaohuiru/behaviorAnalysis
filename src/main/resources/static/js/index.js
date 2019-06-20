$(function(){
 	initPie();
 	initMap();
 	initCountBusiness();
 	inittendencyInfo();
 	initcomparedInfo();
})

function initCountBusiness (codeName,orderDate){
    $.ajax({
        type : 'post', //测试get，正式post
        cache : false,
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        async:false,
        url:getRootPath_web()+"/countUserDevelop",
        data: JSON.stringify({
            codeName: codeName,
            orderDate:orderDate
        }),
        error : function(){
            console.error("出现异常");
        },
        success : function(data){
            console.log(data);
            //console.log(data.devAmount)
            $("#countUser").html(data.devAmount);
            $("#countOrder").html(data.acceptAmount);
            $("#countAverage").html((data.dayTurnover).toFixed(2));
            $("#countTotal").html(data.totalTurnover);
        }
    });

}


function initPie(){
	var myChart = echarts.init(document.getElementById('pieGraph'));
		pieoption = {
	    title : {
	        text: '6月份业务受理情况',
	        x:'center',
	        textStyle: {
              fontSize: 16,
              fontWeight: 600,
              color: '#ff6600'
            },
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '50%',
	            center: ['50%', '55%'],
	            data:[
	                {value:33, name:'直接访问'},
	                {value:310, name:'邮件营销'},
	                {value:234, name:'联盟广告'},
	                {value:135, name:'视频广告'},
	                {value:1548, name:'搜索引擎'}
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
        myChart.setOption(pieoption,true);
        window.addEventListener("resize",function(){
			myChart.resize();
       });
}

function initcomparedInfo() {
    var amount=[];
    $.ajax({
        type : 'get', //测试get，正式post
        cache : false,
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        async:false,
        url:getRootPath_web()+"/countOrderAmount",
        error : function(){
            console.error("出现异常");
        },
        success : function(data){
            amount = data;
            console.log(data);
            //console.log(data.devAmount)
        }
    });

    var myChart = echarts.init(document.getElementById('comparedInfo'));
    var title = ['product', amount[1].month+'月', amount[1].last_month+'月'];
    var content=[];
    for (var i = 0; i < amount.length; i++) {
        var piece = [amount[i].product_name, amount[i].sum, amount[i].last_sum];
        content.push(piece);
    }
    myChart.setOption(
        {
            title : {
                text: '营业额同比交易量',
                x:'center',
                textStyle: {
                    fontSize: 16,
                    fontWeight: 600,
                    color: '#ff6600'
                },
            },
            color:["#91C7AE", "#61A0A8"],
            legend: {
                top:'20',
            },
            tooltip: {

            },
            grid:{
                x:60,
                y:60,
                x2:60,
                y2:50,
                borderWidth:1
            },
            dataZoom: [
                {   // 这个dataZoom组件，默认控制x轴。
                    type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                    height:10,
                    bottom: 20,
                    start: 0,      // 左边在 0% 的位置。
                    end: 5         // 右边在 10% 的位置。
                },
                {
                    type: 'inside',
                    start: 0,      // 左边在 0% 的位置。
                    end: 5         // 右边在 10% 的位置。
                }
            ],
            dataset: {

                dimensions: title,
                source: content
            },
            xAxis: {type: 'category'},
            yAxis: {},
            // Declare several bar series, each will be mapped
            // to a column of dataset.source by default.
            series: [
                {type: 'bar'},
                {type: 'bar'}
            ]
        }
    );

    window.addEventListener("resize",function(){
        myChart.resize();
    });
    myChart.on('click',function () {
        myChart.setOption({
            dataZoom: [
                {   // 这个dataZoom组件，默认控制x轴。
                    type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                    start: 0,      // 左边在 0% 的位置。
                    end: 1     // 右边在 1% 的位置。
                },
                {
                    type: 'inside',
                    start: 0,      // 左边在 0% 的位置。
                    end: 1        // 右边在 10% 的位置。
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
                fontSize: 16,
                fontWeight: 600,
                color: '#ff6600'
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
            boundaryGap: false,
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
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