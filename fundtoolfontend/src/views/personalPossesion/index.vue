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
            <!-- 共有多少查询结果 -->
            <div v-if="array.funds.length>0">一共有{{array.funds.length}}条数据</div>
        </div>
        <!-- 显示部分 -->
        <div class="possesions">
            <van-row v-if="flag">
                <van-col span="10"></van-col>
                <van-col span="4"><van-loading type="spinner"/></van-col>
                <van-col span="10"></van-col>
            </van-row>
            <van-cell-group inset >
                <van-cell is-link @click="showPopup" v-for="item in array.funds" :key="item">基金名字：{{item.fundName}}</van-cell>
            </van-cell-group>
            <van-popup
                v-model:show="show"
                round
                position="bottom"
                :style="{ height: '50%' }"
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
        const flag = ref(false);
        const showPopup = () => {
            show.value = true;
        };
        const array= ref({
            funds:[]
        });
        const onSearch= () =>{
            flag.value= true;
            array.value.funds=[];
            getFund(v.value).then(res=>{
               if(res.data.status==200){
                   flag.value=false;
                   console.log(res.data.err_massage);
                   array.value.funds=res.data.data.data;
               }else{
                   console.log(res.data.err_massage);
               }
            })
        }
        return { 
            v,
            show,
            showPopup,
            onSearch,
            array,
            flag};
    },
}
</script>
<style scoped>
    .search{
        padding:10px;
    }

    .possesions{
        padding: 10px;
        background-color: #D8D8D8;
    }
</style>