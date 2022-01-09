<template>
    <div class="container">
      <!--顶部-->
      <div class="header">
          <Header></Header>
      </div>
      <!--主部分-->
      <div class="main">
        <router-view v-slot="{ Component }">
          <keep-alive>
            <component :is="Component" v-if="$route.meta.keepAlive"/>
          </keep-alive>
          <component :is="Component"  v-if="!$route.meta.keepAlive"/>
        </router-view> 
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

export default {
  
  components:{
    Header,
    Footer
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
    z-index: 2;
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