$(function(){
 	initPie();
 	initMap();
 	initCountBusiness();
 	inittendencyInfo();
 	initcomparedInfo();
})

function initCountBusiness (){
    $.ajax({
        type : 'post', //测试get，正式post
        cache : false,
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        async:false,
        url:getRootPath_web()+"/countUserDevelop",
        data: JSON.stringify({
            codeName: "北京市",
            orderDate:"201906"
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
    var myChart = echarts.init(document.getElementById('comparedInfo'));
    comparedOption = {
        title : {
            text: '营业额同比交易量',
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
            x:40,
            y:60,
            x2:40,
            y2:50,
            borderWidth:1
        },
        dataset: {
            dimensions: ['product', '2015', '2016', '2017'],
            source: [
                {product: 'Matcha Latte', '2015': 43.3, '2016': 85.8, '2017': 93.7},
                {product: 'Milk Tea', '2015': 83.1, '2016': 73.4, '2017': 55.1},
                {product: 'Cheese Cocoa', '2015': 86.4, '2016': 65.2, '2017': 82.5},
                {product: 'Walnut Brownie', '2015': 72.4, '2016': 53.9, '2017': 39.1}
            ]
        },
        xAxis: {type: 'category'},
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [
            {type: 'bar'},
            {type: 'bar'},
            {type: 'bar'}
        ]
    };
    myChart.setOption(comparedOption,true);
    window.addEventListener("resize",function(){
        myChart.resize();
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