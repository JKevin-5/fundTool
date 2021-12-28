<template>
    <n-page-header subtitle="查询基金详细信息">
        <template #title>
            <router-link to="/home/funds" style="text-decoration: none; color: inherit;">Search Funds Details</router-link>
        </template>
        <template #header>
            <n-breadcrumb>
                <n-breadcrumb-item to="/home">主页</n-breadcrumb-item>
                <n-breadcrumb-item>查询基金详细信息</n-breadcrumb-item>
            </n-breadcrumb>
        </template>
    </n-page-header>
    <div >
        <n-card title="查询条件">
            <n-form
                inline
                :label-width="80"
                :model="formValue"
                :rules="rules"
                :size="size"
                ref="formRef"
            >
                <n-form-item label="基金代码/名称" path="fund.name">
                    <n-input v-model:value="formValue.fund.name" placeholder="输入基金代码或名称"></n-input>
                </n-form-item>
                <n-form-item>
                    <n-button @click="onSearch" attr-type="button">搜索</n-button>
                </n-form-item>
            </n-form>
        </n-card>
        <n-card title="查询结果">
            <div id="main" style="width:700px;height:500px;"></div>
        </n-card>
    </div>
    
</template>

<script>
import { defineComponent, onMounted, ref } from "@vue/runtime-core"
import { useMessage } from "naive-ui"
import { getFundHis } from "@/network/fund"
import * as echarts from 'echarts';

export default defineComponent({
    setup(){
        const formRef = ref(null)
        var fundData = ref(null)
        var formData = ref([])
        var formValue = ref({
                fund:{
                    name:''
                }
            })
        let chart;
        const loadingRef = ref(false)
        window.$message = useMessage()
        const chartOption = (param) => chart.setOption({
            title: {
                left: 'center',
                text: '富国中证新能源汽车指数(LOF)A'
            },
            toolbox: {
                feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
                }
            },
            tooltip: {},
            dataset: {
                // 提供一份数据。
                source: param
            },
            dataZoom: [
                {
                type: 'inside',
                start: 0,
                end: 20
                },
                {
                start: 0,
                end: 20
                }
            ],
            xAxis: {
                type: 'time',
                boundaryGap: false
            },
            yAxis: {},
            series: [
                {
                    type: 'line',
                    encode: {
                        // 将 "amount" 列映射到 X 轴。
                        x: 'date',
                        // 将 "product" 列映射到 Y 轴。
                        y: 'growth',
                        tooltip: ['value', 'growth']
                    }
                }
            ]
            })
        onMounted(()=>{
            chart = echarts.init(document.getElementById('main'));
            chartOption(formData);
            window.onresize = function(){
                chart.resize();
            }
        })
        var onSearch = async ()=>{
            loadingRef.value = true;
            if(formRef.value.model.fund.name!=''){
                fundData =await getFundHis(formRef.value.model.fund.name).then(res=>{
                    console.log(res.data);
                    formData.value = res.data.data.data[0]?.netWorthData;
                    loadingRef.value = false;
                    return res.data;
                }).catch(err=>{
                    loadingRef.value = false;
                    window.$message.warning('未知错误');
                    console.log(err);
                    return [];
                })
                if(fundData!=null) {
                    loadingRef.value = false;
                    chart.clear();
                    let array=[["date","value","growth"]];
                    for(var i in eval(formData.value)){
                        array.push(eval(formData.value)[i]);
                    }
                    chartOption(array);
                    window.$message.info(fundData?.msg);
                }
                
            }else{
                loadingRef.value = false;
                window.$message.warning('请输入查询条件');
                return;
            }
        }
        // watchEffect(()=>{
        //     console.log("更新数据");
        //     initEcharts(formData);
        // })
        return {
            formRef,
            fundData,
            formData,
            formValue,
            onSearch,
            size: ref('medium'),
            rules: {
                fund:{
                    name:{
                        required: true,
                        message: '请输入基金代码或名称',
                        trigger: ['input','blur']
                    }
                }
            },
        }
    }
})
</script>

<style>
.n-card {
  max-width: 800px;
  margin: 20px;
}
</style>