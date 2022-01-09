<template>
    <n-page-header subtitle="管理个人基金持仓">
        <template #title>
            <router-link to="/home/funds" style="text-decoration: none; color: inherit;">Search Personal Possessions</router-link>
        </template>
        <template #header>
            <n-breadcrumb>
                <n-breadcrumb-item to="/home">主页</n-breadcrumb-item>
                <n-breadcrumb-item>个人基金持仓管理</n-breadcrumb-item>
            </n-breadcrumb>
        </template>
    </n-page-header>
    <div class="main">
        <n-card>
            <n-row>
                <n-col :span="12">
                    <n-statistic label="目前持有基金数量" :value="99">
                        <!-- <template #prefix>
                            <n-icon>
                                <fundViewOutlined/>
                            </n-icon>
                        </template> -->
                    </n-statistic>
                </n-col>
                <n-col :span="12">
                    <n-statistic label="活跃天数">
                        <!-- <template #prefix>
                            <n-icon>
                                <fundViewOutlined/>
                            </n-icon>
                        </template> -->
                        123
                    </n-statistic>
                </n-col>
            </n-row>
        </n-card>
        <n-card title="目前持仓">
            <template #header-extra style="margin:5px">
                <n-button @click="showModal=true">Add Possession</n-button>
            </template>
            <el-table
                :data="tableData"
                :default-sort="{ prop: 'date', order: 'descending' }"
                style="width: 100%"
            >
                <el-table-column prop="fundName" label="基金名称" width="180" />
                <el-table-column prop="possessionWorth" label="成本" sortable width="90" />
                <el-table-column prop="possessionNum" label="份额" width="90" />
                <el-table-column prop="earnPercent" label="收益率" sortable width="90" />
                <el-table-column prop="earn" label="收益" sortable width="90" />
                <el-table-column prop="totalWorth" label="市值" width="90" />
                <el-table-column prop="sellHis" label="操作历史" />
            </el-table>
        </n-card>
    </div>
    <n-modal v-model:show="showModal" transform-origin="center" to="body">
        <n-card
            style="width: 600px;position: fixed; left:50%; top: 50%; margin: -300px 0 0 -300px;"
            title="新增持仓信息"
            :bordered="false"
            size="huge"
            role="dialog"
            aria-modal="true"
            >
            <addPossession @closeModal="closeModal"/>
        </n-card>
    </n-modal>
</template>

<script>
import { defineComponent, onMounted, ref} from 'vue'
// import { FundViewOutlined } from '@vicons/antd'
import addPossession from './addPossession.vue'
import { getPossessions } from '@/service/possession'
import { getUserNo } from '@/utils/userInfoUtil'

export default defineComponent({
    components:{
        // FundViewOutlined
        addPossession
    },
    setup(){
        const loadingRef = ref(false)
        const optionsRef = ref([])
        const tableDataRef = ref([])
        const showModal = ref(false)
        onMounted(()=>{
            getPossessions(getUserNo()).then(res=>{
                console.log(res.data.data.data)
                tableDataRef.value=res.data.data.data
            })
        })
        return{
            tableData:tableDataRef,
            searchInfo:ref(null),
            loading: loadingRef,
            options: optionsRef,
            showModal,
            // 接收子件的数据
            closeModal:(val) =>{
                showModal.value=val;
            }
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