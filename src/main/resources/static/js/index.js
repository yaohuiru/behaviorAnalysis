$(function(){
 	initPie();
 	initMap();
})

function initPie(){
	var myChart = echarts.init(document.getElementById('pieGraph'));
		pieoption = {
	    title : {
	        text: '某站点用户访问来源',
	        x:'center',
	        textStyle: {
              fontSize: 16,
              fontWeight: 'bolder',
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