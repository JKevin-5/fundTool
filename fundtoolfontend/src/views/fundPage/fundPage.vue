<template>
    <n-page-header subtitle="查询基金信息">
        <template #title>
            <router-link to="/home/funds" style="text-decoration: none; color: inherit;">Search Funds Info</router-link>
        </template>
        <template #header>
            <n-breadcrumb>
                <n-breadcrumb-item to="/home">主页</n-breadcrumb-item>
                <n-breadcrumb-item>查询基金信息</n-breadcrumb-item>
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
            <n-data-table
                remote
                ref="table"
                :columns="columns"
                :data="formData"
                :loading="loading"
                :pagination="pagination"
                :row-key="rowKey"
            />
        </n-card>
    </div>
    
</template>

<script>
import { defineComponent, ref ,reactive} from "@vue/runtime-core"
import { useMessage } from "naive-ui"
import { getFunds } from "@/network/fund"

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
        const columns = [
            {
                title:'基金代码',
                key:'fundCode'
            },{
                title:'名称',
                key:'fundName'
            },{
                title:'类型',
                key:'fundType'
            },{
                title:'数据更新时间',
                key:'updateTime'
            }
        ]
        const paginationReactive = reactive({
            page: 1,
            pageSize: 5,
            showSizePicker: true,
            pageSizes: [1, 3, 5],
            onChange: (page) => {
                paginationReactive.page = page
            },
            onPageSizeChange: (pageSize) => {
                paginationReactive.pageSize = pageSize
                paginationReactive.page = 1
            }
        })
        const loadingRef = ref(false)
        window.$message = useMessage()
        var onSearch = async ()=>{
            loadingRef.value = true;
            if(formRef.value.model.fund.name!=''){
                fundData = await getFunds(formRef.value.model.fund.name).then(res=>{
                    formData.value = res.data.data.data;
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
                    window.$message.info(fundData?.msg);
                }
            }else{
                loadingRef.value = false;
                window.$message.warning('请输入查询条件');
                return;
            }
        }

        return {
            formRef,
            fundData,
            formData,
            formValue,
            onSearch,
            size: ref('medium'),
            columns,
            loading:loadingRef,
            pagination:paginationReactive,
            rules: {
                fund:{
                    name:{
                        required: true,
                        message: '请输入基金代码或名称',
                        trigger: ['input','blur']
                    }
                }
            },
            rowKey (rowData) {
                console.log(rowData.fundCode);
                return rowData.fundCode
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