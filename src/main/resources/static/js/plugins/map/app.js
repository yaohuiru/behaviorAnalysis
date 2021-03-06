var orderDate = "201906";

//地图容器
var chart = echarts.init(document.getElementById('map'));
//34个省、市、自治区的名字拼音映射数组
var provinces = {
    //23个省
    "台湾": "taiwan",
    "河北": "hebei",
    "山西": "shanxi",
    "辽宁": "liaoning",
    "吉林": "jilin",
    "黑龙江": "heilongjiang",
    "江苏": "jiangsu",
    "浙江": "zhejiang",
    "安徽": "anhui",
    "福建": "fujian",
    "江西": "jiangxi",
    "山东": "shandong",
    "河南": "henan",
    "湖北": "hubei",
    "湖南": "hunan",
    "广东": "guangdong",
    "海南": "hainan",
    "四川": "sichuan",
    "贵州": "guizhou",
    "云南": "yunnan",
    "陕西": "shanxi1",
    "甘肃": "gansu",
    "青海": "qinghai",
    //5个自治区
    "新疆": "xinjiang",
    "广西": "guangxi",
    "内蒙古": "neimenggu",
    "宁夏": "ningxia",
    "西藏": "xizang",
    //4个直辖市
    "北京": "beijing",
    "天津": "tianjin",
    "上海": "shanghai",
    "重庆": "chongqing",
    //2个特别行政区
    "香港": "xianggang",
    "澳门": "aomen"
};

//直辖市和特别行政区-只有二级地图，没有三级地图
var special = ["北京", "天津", "上海", "重庆", "香港", "澳门"];
var mapdata = [];
//用户所属区域信息
var userArea = 'china';

//绘制全国地图
function initMap() {
    var d = [];
    //获取权限
    userArea=document.getElementById("userArea").value;
    console.log(userArea);
    //不是顶级菜单，判断是省级还是市级
    var name = userArea.substr(0, userArea.length - 1);
    var path = "";
    console.log(userArea);
    console.log(name);
    //判断是直辖市或者省
    if (name in provinces) {
        //省级
        path = '../map/province/' + provinces[name] + '.json';
    } else if (name in cityMap) {
        //市级
        path = '../map/city/' + cityMap[name] + '.json';
    } else {
        //顶级
        path = '../map/' + userArea + '.json';
    }
    //获取json，初始化地图
    $.getJSON(path, function (data) {
        for (var i = 0; i < data.features.length; i++) {
            d.push({
                name: data.features[i].properties.name
            })
        }
        mapdata = d;
        //注册地图
        echarts.registerMap(name, data);
        //绘制地图
        renderMap(name, d);
    });


}


//地图点击事件
chart.on('click', function (params) {
    var d = [];
    console.log(params);
    /*$("#businessTitle").before(params.name);*/
    areaName = params.name;
    initCountBusiness(orderDate, areaName);
    if (params.name in provinces) {
        //如果点击的是34个省、市、自治区，绘制选中地区的二级地图
        $.getJSON('../map/province/' + provinces[params.name] + '.json', function (data) {
            echarts.registerMap(params.name, data);
            for (var i = 0; i < data.features.length; i++) {
                d.push({
                    name: data.features[i].properties.name
                })
            }
            renderMap(params.name, d);
        });
    } else if (params.seriesName in provinces) {
        //如果是【直辖市/特别行政区】只有二级下钻
        if (special.indexOf(params.seriesName) >= 0) {
            //返回顶级
            initMap();
        } else {
            //显示县级地图
            $.getJSON('../map/city/' + cityMap[params.name] + '.json', function (data) {
                echarts.registerMap(params.name, data);
                for (var i = 0; i < data.features.length; i++) {
                    d.push({
                        name: data.features[i].properties.name
                    })
                }
                renderMap(params.name, d);
            });
        }
    } else {
        //返回顶级
        initMap();
        initCountBusiness(orderDate, userArea);
    }
});

//初始化绘制全国地图配置
var option = {
    backgroundColor: '#fff',
    title: {
        /*text: '中国地图',*/
        subtext: '省市区三级下钻',
        //link:'http://www.ldsun.com',
        left: 'center',
        textStyle: {
            color: '#91c7ae',
            fontSize: 16,
            fontWeight: 'normal',
            fontFamily: "Microsoft YaHei"
        },
        subtextStyle: {
            color: '#ccc',
            fontSize: 13,
            fontWeight: 'normal',
            fontFamily: "Microsoft YaHei"
        }
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}'
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            dataView: {readOnly: false},
            restore: {},
            saveAsImage: {}
        },
        iconStyle: {
            normal: {
                color: '#fff'
            }
        }
    },
    animationDuration: 1000,
    animationEasing: 'cubicOut',
    animationDurationUpdate: 1000

};

function renderMap(map, data) {
    option.title.subtext = map;
    option.series = [
        {
            name: map,
            type: 'map',
            mapType: map,
            roam: false,
            nameMap: {
                'china': '中国'
            },
            label: {
                normal: {
                    show: true,
                    //区域名称字体设置
                    textStyle: {
                        color: '#fff',
                        fontSize: 10
                    }
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        color: '#fff',
                        fontSize: 13
                    }
                }
            },
            itemStyle: {
                normal: {
                    areaColor: '#ffa640',
                    borderColor: '#86280e'
                },
                emphasis: {
                    areaColor: '#FF8900'
                }
            },
            data: data
        }
    ];
    //渲染地图
    chart.setOption(option);
}