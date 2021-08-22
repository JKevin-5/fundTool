<template>
    <van-tabbar v-model="active">
        <van-tabbar-item replace to="/home" icon="home-o" @click="logFuction">首页</van-tabbar-item>
        <van-tabbar-item replace to="/home/personalPossesion" icon="search">基金</van-tabbar-item>
        <van-tabbar-item replace to="/home" icon="friends-o">朋友圈</van-tabbar-item>
        <van-tabbar-item replace to="/home/setting" icon="setting-o">设置</van-tabbar-item>
    </van-tabbar>
</template>

<script>
import { getCurrentInstance } from 'vue';
export default {
    setup() {
        const {proxy} = getCurrentInstance();
        const active = proxy.$router.currentRoute.value;
        const logFuction=()=>{
            console.log(proxy.$router.currentRoute.value.fullPath)
        };
        return { active,logFuction };
    },
};
</script>

<style>
    .footer{
        flex-direction: row;
    }
    ul{
        display: flex;
        list-style: none;
        /* padding:8px 0; */
        margin:0;
        justify-content: space-around;
        
    }
    ul > li a{
        display: block;
        /* padding: 4px 8px; */
        text-decoration: none; 
    }
</style>