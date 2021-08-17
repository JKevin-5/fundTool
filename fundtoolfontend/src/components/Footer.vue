<template>
    <van-tabbar v-model="active">
        <van-tabbar-item icon="home-o">标签</van-tabbar-item>
        <van-tabbar-item icon="search">标签</van-tabbar-item>
        <van-tabbar-item icon="friends-o">标签</van-tabbar-item>
        <van-tabbar-item icon="setting-o">标签</van-tabbar-item>
    </van-tabbar>
</template>

<script>
import { ref } from 'vue';
export default {
    setup() {
        const active = ref(0);
        return { active };
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