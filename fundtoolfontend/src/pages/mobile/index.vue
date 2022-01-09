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
        <!-- 显示部分 -->
        <van-pull-refresh v-model="loading" @refresh="onRefresh">
        <div class="possesions">
            <van-cell-group inset >
                <van-swipe-cell v-for="(item,index) in array.funds" :key="index">
                    <van-cell is-link @click="showPopup(index)">
                        <div class="sign">
                            <img v-if="item.dayGrowth>=0" src="@/assets/up.png" width="24" height="24" alt="">
                            <img v-else src="@/assets/down.png" width="24" height="24" alt="">
                            {{item.dayGrowth}}%   {{item.name}}
                        </div>
                    </van-cell>
                    <!-- 从右往左滑动 可以删除该条基金数据 -->
                    <template #right>
                        <van-button square type="danger" text="删除" @click="deleteFund(index)"/>
                    </template>
                </van-swipe-cell>
            </van-cell-group>
            
            <van-popup
                v-model:show="show"
                round
                safe-area-inset-bottom
                position="bottom"
                default="11"
                :style="{ height: '50%' }"
            >
            <template #default>
                <div class="content">
                    基金名称：{{pop_content.name}} <br>
                    基金代码：{{pop_content.code}} <br>
                    基金今日涨幅：{{pop_content.dayGrowth}}% <br>
                    基金今日收益：{{earn}} <br>
                    基金持有收益率：{{take}}%<br>
                    <van-cell-group>
                        <van-cell>
                            <van-field v-model="pop_content.amount" placeholder="请输入用户名">
                                <template #label>
                                    基金持仓金额
                                </template>
                                <template #button>
                                    <van-button size="small" type="primary" @click="commitAmount">确认</van-button>
                                </template>
                            </van-field>
                        </van-cell>
                        <van-cell>
                            <van-field v-model="pop_content.cost" placeholder="请输入用户名">
                                <template #label>
                                    基金成本净值
                                </template>
                            </van-field>
                        </van-cell> 
                    </van-cell-group>
                </div>
                
            </template>
            </van-popup>
        </div>
        </van-pull-refresh>
    </div>  
</template>
<script>
import {onBeforeMount, ref, getCurrentInstance, computed} from 'vue'
import {getFund} from '@/network/out_search'
import { Toast,Dialog} from 'vant';
export default ({
    setup() {
        //搜索内容
        const {proxy} = getCurrentInstance();
        const v = ref('');
        const show = ref(false);
        const flag = ref(false);
        const loading = ref(false);
        const pop_content=ref({});
        const array= ref({
            code:[],
            funds:[]
        });
        const earn= computed(()=> (pop_content.value.dayGrowth * pop_content.value.amount * 0.01).toFixed(2));
        const take= computed(()=> pop_content.value.amount==0||pop_content.value.cost==0?0:((pop_content.value.netWorth - pop_content.value.cost)*100/pop_content.value.cost).toFixed(2));
        const showPopup = (i) => {
            show.value = true;
            pop_content.value=array.value.funds[i];
            pop_content.value.id=i;
            console.log(pop_content.value.name);
        };
        
        //下拉刷新
        const onRefresh= async ()=>{
            setTimeout(() => {
                Toast('刷新成功');
                loading.value = false;
            }, 500);
            var storage=window.localStorage;
            var array_def=JSON.parse(storage.funds);
            var codes=array_def.code;
            var funds=array_def.funds;
            var search_condition='';
            //拼接查询条件
            for(var i=0;i<codes.length;i++){
                if(i!=0){
                    search_condition=search_condition+','+codes[i];
                }else{
                    search_condition=codes[i];
                }
            }
            var data = await getFund(search_condition).then(res=>{
                        return res.data.data;
                    })
            //遍历data更新数据
            for(var j=0;j<data.length;j++){
                if(funds[j].code==data[j].code){
                    funds[j].dayGrowth=data[j].dayGrowth;
                    funds[j].netWorth=data[j].netWorth;
                }
            }
            array_def.funds=funds;
            array.value.funds=funds;
            storage.funds=JSON.stringify(array_def);

        }
        
        //新增基金信息
        const addFund= async()=>{
            var storage=window.localStorage;
            var fund={
                name:'',
                code:'',
                cost:0,
                dayGrowth:'',
                expectGrowth:0,
                amount:0,
                netWorth:'',
                earnings:0,
                date:''
            }
            var funds_def=funds_def=JSON.parse(storage.funds).funds;
            var flag=true;
            if(v.value==''){
                proxy.$notify({ type: 'warning', message: '输入为空！' });
            }else{
                //避免重复存放
                for(var i=0;i<funds_def.length;i++){
                    if(v.value==funds_def[i].code){
                        flag=false;
                        proxy.$notify({ type: 'warning', message: '请勿添加重复基金'+v.value });
                        break;
                    }
                }
                if(flag){
                    var data= await getFund(v.value).then(res=>{
                        console.log(res.data.data);
                        return res.data.data;
                    })
                    if(data.length!=0){
                        proxy.$notify({ type: 'success', message: '基金'+v.value +"查询成功！"});
                        fund.name=data[0].name;
                        fund.code=data[0].code;
                        fund.dayGrowth=data[0].dayGrowth;
                        fund.netWorth=data[0].netWorth;
                        fund.date=data[0].expectWorthDate;
                        fund.expectGrowth=data[0].expectGrowth;
                        array.value.funds.push(fund);
                        array.value.code.push(data[0].code);
                        storage.funds=JSON.stringify(array.value); 
                    }else{
                        proxy.$notify({ type: 'warning', message: '基金'+v.value +"代码错误！"});
                    }
                    
                }
                
                v.value='';
                
            }
        }

        //修改基金信息
        const editFund=()=>{
            
        }
        //提交持仓金额
        const commitAmount=()=>{
            var id=pop_content.value.id;
            var storage=window.localStorage;
            array.value.funds[id].amount=pop_content.value.amount;
            storage.funds=JSON.stringify(array.value);
            console.log(array.value.funds[id]);
            Toast('更新成功');
        }

        onBeforeMount(()=>{
            var storage=window.localStorage;
            if(storage.getItem("funds")==null){
                array.value={
                    code:[],
                    funds:[]
                }
                storage.funds=JSON.stringify(array.value);
            }else{
                array.value=JSON.parse(storage.funds);
            }
            
        })

        // 删除基金信息
        const deleteFund = (index) => {
                return new Promise(() => {
                    Dialog.confirm({
                        title: '确定删除吗？',
                    })
                    .then(() => {
                        // on confirm
                        var storage=window.localStorage;
                        array.value.code.splice(index,1);
                        array.value.funds.splice(index,1);
                        storage.funds=JSON.stringify(array.value);
                        console.log("确认删除"+index);
                    })
                    .catch(()=>{
                        // on cancel
                        console.log("取消删除"+index);
                    });
                });
            
        };

        return { 
            v,
            show,
            showPopup,
            array,
            flag,
            addFund,
            editFund,
            deleteFund,
            pop_content,
            loading,
            onRefresh,
            earn,
            commitAmount,
            take
            };
    },
})
</script>
<style scoped>
    .search{
        margin:10px;
    }
    .content{
        padding: 30px;
    }
    .possesions{
        padding: 5px;
        background-color: #D8D8D8;
    }
</style>