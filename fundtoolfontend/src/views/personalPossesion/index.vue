<template>
    <div class="container">
        <!-- 搜索框 -->
        <div class="search">
            <van-search
                v-model="v"
                show-action
                label="基金"
                placeholder="请输入搜索关键词"
                @search="onSearch"
            >
                <template #action>
                    <div @click="onSearch">搜索</div>
                </template>
            </van-search>
        </div>
        <!-- 显示部分 -->
        <div class="possesions">
            <van-cell-group inset>
                <van-cell is-link @click="showPopup">{{v}}</van-cell>
            </van-cell-group>

            <van-popup
                v-model:show="show"
                round
                position="bottom"
                :style="{ height: '30%' }"
            />
        </div>
    </div>
</template>
<script>
import {ref} from 'vue'
import {getFund} from '@/network/fund'
export default {
    setup() {
        //搜索内容
        const v = ref('');
        const show = ref(false);
        const showPopup = () => {
            show.value = true;
        };
        const array=['']
        // const {proxy}=getCurrentInstance();
        const onSearch= () =>{
            console.log("value:====>"+v.value);
            // proxy.$axios.post('/findFunds/?info=白酒').then(res=>{
            //     console.log(res);
            // })
            getFund(v.value).then(res=>{
               if(res.data.status==200){
                   console.log("请求成功！"+res.data.err_massage);
                   array.value=res.data.data;
               } 
            })
            console.log(array)
        }
        return { 
            v,
            show,
            showPopup,
            onSearch };
    },
}
</script>
<style scoped>
    .search{
        padding:10px;
    }

    .possesions{
        padding: 10px;
        background: bisque;
    }
</style>