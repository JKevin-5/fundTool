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
                <el-table-column prop="nowWorth" label="净值" width="90" />
                <el-table-column prop="dayGrowth" label="日涨幅" width="90" />
                <el-table-column prop="updateTime" label="更新时间" width="150" />
                <el-table-column prop="possessionWorth" label="成本" sortable width="90" />
                <el-table-column prop="possessionNum" label="份额" width="90" />
                <el-table-column prop="nowEarn" label="今日收益" sortable width="90" />
                <el-table-column prop="earnPercent" label="收益率" sortable width="90" />
                <el-table-column prop="earn" label="收益" sortable width="90" />
                <el-table-column prop="totalWorth" label="市值" width="90" />
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
// import { getPossessions } from '@/service/possession'
// import { getUserNo } from '@/utils/userInfoUtil'
import { getFundListNow } from '@/service/fund'

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
        const possessions = ref([
            {
                fundCode:'012414',
                fundName:'',
                possessionWorth:'1.2942',
                possessionNum:'175.6',
                updateTime:'',
                nowWorth:'',
                dayGrowth:''
            },{
                fundCode:'004070',
                fundName:'',
                possessionWorth:'1.1793',
                possessionNum:'170.13',
                updateTime:'',
                nowWorth:'',
                dayGrowth:''
            }
        ])
        const dataTableRef = ref([])
        function makeFunds(str,possession){
            str = str.substring(8,str.length-2);
            str = JSON.parse(str)
            possession.fundName = str.name
            possession.updateTime = str.gztime
            possession.nowWorth = str.gsz
            possession.dayGrowth = str.gszzl
            if(possession.possessionNum!==''&&possession.possessionWorth!==''){
                // 市值
                possession.totalWorth = (str.gsz * possession.possessionNum).toFixed(2)
                // 收益率
                possession.earnPercent = ((str.gsz - possession.possessionWorth)/possession.possessionWorth).toFixed(2)+'%'
                // 收益
                possession.earn = ((str.gsz - possession.possessionWorth)*possession.possessionNum).toFixed(2)
                // 今日收益
                possession.nowEarn = ((str.gsz - str.dwjz)*possession.possessionNum).toFixed(2)
            }
            tableDataRef.value.push(possession)
        }
        const getdata = async ()=>{
            for(let i =0;i<possessions.value.length;i++){
                // 调用系统外部api获取实时基金数据
                await getFundListNow(possessions.value[i].fundCode).then(res=>{
                    makeFunds(res.data,possessions.value[i])
                })
            }
            console.log(dataTableRef.value)
        }
        onMounted(()=>{
            // 调用系统内部api获取持有份额
            // getPossessions(getUserNo()).then(res=>{
            //     console.log(res.data.data.data)
            //     tableDataRef.value=res.data.data.data
            // })
            getdata()
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
  max-width: 1000px;
  margin: 20px;
}
</style>