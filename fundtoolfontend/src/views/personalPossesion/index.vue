<template>
    <div class="container">
        <!-- 搜索框 -->
        <div class="search">
            <van-search v-model="value" placeholder="请输入搜索关键词" />
        </div>
        <!-- 显示部分 -->
        <div class="possesions">
            <van-cell-group inset>
                <van-cell @click="showPopup">内容</van-cell>
                <van-cell is-link @click="showPopup">展示弹出层</van-cell>
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
export default {
    setup() {
        //搜索内容
        const value = ref('');
        const show = ref(false);
        const showPopup = () => {
            show.value = true;
        };
        return { 
            value,
            show,
            showPopup };
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