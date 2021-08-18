<template>
    <div class="container">
      <!--顶部-->
      <div class="header">
        <Header></Header>
      </div>
      <!--主部分-->
      <div class="main">
        <canvas id="myChart" width="350" height="260"></canvas>
        <router-view></router-view>
      </div>
      <!--底部-->
      <div class="footer">
        <Footer></Footer>
      </div>    
    </div>

</template>
<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import {getCurrentInstance,nextTick} from 'vue'
export default {
  
  components:{
    Header,
    Footer
  },
  setup(){
    const {proxy} = getCurrentInstance();
    const data = [
              { genre: 'Sports', sold: 275 },
              { genre: 'Strategy', sold: 115 },
              { genre: 'Action', sold: 120 },
              { genre: 'Shooter', sold: 350 },
              { genre: 'Other', sold: 150 },
            ];
    //等待dom加载完成再生成F2图
    nextTick(()=>{
        const chart = new proxy.F2.Chart({
           id: 'myChart',
           pixelRatio: window.devicePixelRatio // 指定分辨率
         });

        // Step 2: 载入数据源
        console.log(data);
        chart.source(data);

        // Step 3：创建图形语法，绘制柱状图，由 genre 和 sold 两个属性决定图形位置，genre 映射至 x 轴，sold 映射至 y 轴
        chart.interval().position('genre*sold').color('genre');

        // Step 4: 渲染图表
        chart.render();
    }); 
      
  },
  methods:{}
}
</script>
<style>
  .item{
    height: 300px;
    
  }
  .header,.footer{
    height:40px;
    line-height:40px;
    background-color:#D8D8D8;
    text-align:center;
    left:0;
    width:100%;
  }
  .header{position: absolute;top:0;}
  .footer{
    position: fixed;
    bottom:0;
  }
  .main{
    position:absolute;
    z-index:1;
    top:40px;
    left:0;
    bottom:40px;
    width:100%;
    overflow: auto;}
  .container{width:100%;}
</style>