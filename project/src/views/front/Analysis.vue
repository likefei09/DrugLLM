<template>
    <div>
        <el-row :gutter="10" style="margin-bottom: 50px;">
            <el-col :span="6">
                <el-card style="color :#409EFF">
                    <div><i class="el-icon-user-solid" />用户总数</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold;">
                    100
                    </div>
                </el-card>
        </el-col>
            <el-col :span="6">
                <el-card style="color :#F56C6C">
                    <div><i class="el-icon-money" />销售总量</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold;">
                    ¥ 100000000
                    </div>
                </el-card>
        </el-col>
            <el-col :span="6">
                <el-card style="color :#67C23A">
                    <div><i class="el-icon-bank-card" />收益总额</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold;">
                    ¥ 90000000
                    </div>
                </el-card>
        </el-col>
            <el-col :span="6">
                <el-card style="color :#E6A23C">
                    <div><i class="el-icon-s-shop" />门店总数</div>
                    <div style="padding: 10px 0; text-align: center; font-weight: bold;">
                    20
                    </div>
                </el-card>
        </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <div id="main" style="width: 500px; height: 450px"></div>
            </el-col>

            <el-col :span="12">
                <div id="pie" style="width: 500px; height: 400px"></div> 
            </el-col>
        </el-row>
    </div>
</template>

<script>
import * as echarts from 'echarts';



export default {
  name: "analysis",
    data() {
        return {

        }
    },
  mounted() {  //页面元素渲染之后再触发，created是元素还没有渲染完成就触发方法
    var option = {
        title: {
    text: '各个省份人员统计',
    subtext: '统计图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
    xAxis: {
        type: 'category',
        data: ["山东", "河南", "贵州", "陕西"]
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
        name: "星巴克",
        data: [],
        type: 'bar'
        },
        {
        name: "星巴克",
        data: [],
        type: 'line'
        },
        {
        name: "瑞幸",
        data: [],
        type: 'bar'
        },
        {
        name: "瑞幸",
        data: [],
        type: 'line'
        }
    ]
};

//饼图
var pieOption = {
  title: {
    text: '各个省份人员统计',
    subtext: '趋势图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '星巴克',
      type: 'pie',
      radius: '60%',
      center: ['25%', '50%'],
      label:{
        normal:{
            show:true,
            position:'inner',  //标签的位置
            textStyle : {
                fontWeight: 300,
                fontSize : 16,  //文字的的字体大小
            },
            formatter: '{c}({d})%'
        }
      },
      data: [],
    },
    {
      name: '瑞幸咖啡',
      type: 'pie',
      radius: '50%',
      center: ['75%', '50%'],
      label:{
        normal:{
            show:true,
            position:'inner',  //标签的位置
            textStyle : {
                fontWeight: 300,
                fontSize : 16,  //文字的的字体大小
            },
            formatter: '{d}%'
        }
      },
      data: [
        {name: "山东",value: 5},
            {name: "河南",value: 6},
            {name: "贵州",value: 7},
            {name: "陕西",value: 8},
        ],
    }
  ],
  
};


    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);
    

    this.request.get("/echarts/members").then(res =>{
        //option.xAxis.data = res.data.x
        option.series[0].data = res.data
        option.series[1].data = res.data
        option.series[2].data = [5,6,7,8]
        option.series[3].data = [5,6,7,8]
        console.log(res)
        console.log(res.data)
        //数据准备完毕之后再set
        myChart.setOption(option);


        pieOption.series[0].data = [
            {name: "山东",value: res.data[0]},
            {name: "河南",value: res.data[1]},
            {name: "贵州",value: res.data[2]},
            {name: "陕西",value: res.data[3]},
        ]
        pieChart.setOption(pieOption);
    })
    




    }
}
</script>
