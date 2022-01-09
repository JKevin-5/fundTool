<template>
    <!-- 新增持仓信息form组件 -->
    <n-form :model="model" ref="formRef" :rules="rules">
        <n-form-item path="fundCode" label="选择基金">
            <n-select
                v-model:value="model.fundCode"
                filterable
                placeholder="搜索基金"
                :options="options"
                :loading="loading"
                clearable
                remote
                @search="getOptionList"
                @keydown.enter.prevent
            />
        </n-form-item>
        <n-form-item path="possessionWorth" label="持有成本">
            <n-input v-model:value="model.possessionWorth" @keydown.enter.prevent />
        </n-form-item>
        <n-form-item path="possessionNum" label="持有份额">
            <n-input v-model:value="model.possessionNum" @keydown.enter.prevent />
        </n-form-item>
        <n-row :gutter="[0, 24]">
            <n-col :span="24">
                <div style="display: flex; justify-content: flex-end;">
                <n-button
                    @click="commit"
                    :disabled="model.fundCode === null && model.possessionWorth===null&& model.possessionNum===null"
                    round
                    type="primary"
                >
                    确定
                </n-button>
                <n-button
                    round
                    @click="closeModal"
                    style="margin-left: 10px;"
                    >
                    取消
                </n-button>
                </div>
                <pre>{{model}}</pre>
            </n-col>
        </n-row>
    </n-form>
</template>
<script>
import { defineComponent,ref } from 'vue'
import {addPossession} from '@/service/possession'
import {getFundList} from '@/service/fund'
import { getUserNo } from '@/utils/userInfoUtil'
import { useMessage } from "naive-ui"
export default defineComponent({
    setup(prop,{emit}) {
        const modelRef = ref({
            fundCode: null,
            userNo: getUserNo(),
            possessionWorth: null,
            possessionNum: null
        })
        const closeModal =()=>{
            emit('closeModal',false)
        }
        const optionsRef = ref([])
        const loadingRef = ref(false)
        window.$message = useMessage()
        return {
            model: modelRef,
            closeModal,
            add:()=>{
                addPossession(modelRef).then(res=>{
                    console.log(res)
                })
            },
            options: optionsRef,
            loading: loadingRef,
            searchInfo:ref(null),
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
            // 提交个人持仓信息
            commit:()=>{
                addPossession(modelRef.value).then(res=>{
                    if(res.data.status=='200'){
                        closeModal()
                        window.$message.info(res.data.msg)
                        return
                    }
                    window.$message.warning(res.data.msg)
                    
                }).catch(err=>{
                    console.log(err)
                })
            }
        }
    },

})
</script>
