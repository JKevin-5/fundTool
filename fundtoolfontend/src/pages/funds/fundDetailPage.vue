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
                    <n-select
                        v-model:value="formValue.fund.name"
                        filterable
                        placeholder="输入基金代码或名称"
                        :options="options"
                        :loading="loading"
                        clearable
                        remote
                        @search="getOptionList"
                        @keydown.enter.prevent
                    />
                </n-form-item>
                <n-form-item>
                    <n-button @click="onSearch" attr-type="button">搜索</n-button>
                </n-form-item>
                <n-form-item>
                    <n-button @click="onUpdate" attr-type="button" type="primary">更新基金历史数据</n-button>
                </n-form-item>
                <pre>
                    {{formValue}}
                </pre>
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
import { getFundHis ,getFundList,updateFundHis} from "@/service/fund"

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
        var fundName = ref('');
        const optionsRef = ref([])
        let chart;
        const loadingRef = ref(false)
        window.$message = useMessage()
        const chartOption = (param,fundData) => chart.setOption({
            title: {
                left: 'center',
                text: fundData == null ? '':fundData.value?.data.data[0].fundName
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
                end: 40
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
            chartOption(formData,fundData);
            window.onresize = function(){
                chart.resize();
            }
        })
        var onSearch = async ()=>{
            loadingRef.value = true;
            if(formRef.value.model.fund.name!=''){
                fundData.value =await getFundHis(formRef.value.model.fund.name).then(res=>{
                    formData.value = res.data.data.data[0]?.netWorthData;
                    fundName.value = res.data.data;
                    loadingRef.value = false;
                    return res.data;
                }).catch(err=>{
                    loadingRef.value = false;
                    window.$message.warning('未知错误：'+err);
                    return [];
                })
                if(fundData!=null) {
                    loadingRef.value = false;
                    chart.clear();
                    let array=[["date","value","growth"]];
                    for(var i in eval(formData.value)){
                        array.push(eval(formData.value)[i]);
                    }
                    chartOption(array,fundData);
                    window.$message.info(fundData.value?.err_massage);
                }
                
            }else{
                loadingRef.value = false;
                window.$message.warning('请输入查询条件');
                return;
            }
        }
        var onUpdate = () =>{
            updateFundHis({
                fundCode:formValue.value.fund.name,
                date:'2021-01-01'
            }
            ).then(res=>{
                window.$message.info(res.data.msg);
            }).catch(err=>{
                console.log(err);
            })
        }
        return {
            formRef,
            fundData,
            formData,
            formValue,
            onSearch,
            onUpdate,
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
            options:optionsRef,
            loading:loadingRef,
            // 获取fund下拉框
            getOptionList:(query)=>{
                if (!query.length) {
                    optionsRef.value = []
                    return
                }
                loadingRef.value = true
                window.setTimeout(() => {
                    getFundList(query).then(res=>{
                        optionsRef.value = res.data.data.data.filter(
                            (item) => ~item.label.indexOf(query)
                        )
                    })
                    loadingRef.value = false
                }, 1000)
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