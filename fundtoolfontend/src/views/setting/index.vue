<template>
    <div class="container">
        <!-- 搜索框 -->
        <div class="search">
            <van-search
                v-model="v"
                show-action
                label="基金"
                placeholder="请输入基金代码"
                @search="addFund"
            >
                <template #action>
                    <div @click="addFund">添加</div>
                </template>
            </van-search>
        </div>
        <!-- F2显示框  -->
        <div>
            <canvas id="myChart" width="350" height="260"></canvas>
        </div>
    </div>  
</template>
<script>

</script>
<style scoped>
</style>