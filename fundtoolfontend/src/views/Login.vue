<template>
  <div class="main">
    <n-divider>Screen 响应式 登录界面</n-divider>
    <n-grid cols="6" item-responsive responsive="screen">
      <n-grid-item  span="0 m:1 l:2">
        <div class="green">
          m 以下:2不显示<br />
          m 到 l:占据空间 1<br />
          l 以上:占据空间 2
        </div>
      </n-grid-item>
      <n-grid-item span="6 m:4 l:2">
        <div class="light-green">
          <n-card title="登录" hoverable>
            <n-form :model="model" ref="formRef" :rules="rules">
              <n-form-item path="username" label="用户名">
                <n-input v-model:value="model.username" @keydown.enter.prevent />
              </n-form-item>
              <n-form-item path="password" label="密码">
                <n-input v-model:value="model.password" @keydown.enter.prevent type="password"/>
              </n-form-item>
              <n-form-item>
                <n-button @click="login" attr-type="button">登录</n-button>
              </n-form-item>
            </n-form>
          </n-card>
        </div>
      </n-grid-item>
      <n-grid-item span="0 m:1 l:2">
        <div class="green">
          m 以下:2不显示<br />
          m 到 l:占据空间 1<br />
          l 以上:占据空间 2
        </div>
      </n-grid-item>
    </n-grid>
  </div>
</template>
<script>
import {defineComponent, ref} from 'vue'
import {getToken} from '@/network/system'
import {setTokenLocalstorage} from '@/util/userInfoUtil'
import { useRouter} from 'vue-router';
import { useMessage } from 'naive-ui';
export default defineComponent({
  setup() {
    const formRef = ref(null);
    const rPasswordFormItemRef = ref(null);
    const modelRef = ref({
      username:null,
      password:null
    });
    const router = useRouter();
    const message = useMessage();
    const token = ref(null);
    return {
      formRef,
      model: modelRef,
      token,
      rules: {
        username: [
          {
            required: true,
            validator (rule, value) {
              if (!value) {
                return new Error('需要用户名')
              }
              return true
            },
            trigger: ['input', 'blur']
          }
        ],
        password: [
          {
            required: true,
            validator (rule, value) {
              if(!value) {
                return new Error('需要密码')
              }
              return true
            },
            trigger: ['input', 'blur']
          }
        ]
      },
      handlePasswordInput () {
        if (modelRef.value.reenteredPassword) {
          rPasswordFormItemRef.value.validate({ trigger: 'password-input' })
        }
      },
      login () {
        getToken(modelRef.value.username,modelRef.value.password).then(res=>{
          if(res.data.state){
            setTokenLocalstorage(res.data.token);
            message.info(res.data.msg);
            console.log(window.localStorage.token);
            router.push('/home');
          }else{
            message.warning(res.data.msg);
          } 
        }).catch(err=>{
          message.error(err);
        })
      }
    }
  },
});
</script>
<style lang="scss" scoped>
.light-green {
  height: 980px;
  background-color: rgba(0, 128, 0, 0.12);
  display: flex;
  align-items: center;
  justify-content: center;
}
.green {
  height: 980px;
  background-color: rgba(0, 128, 0, 0.24);
  display: flex;
  align-items: center;
  justify-content: center;
}
.n-card {
  max-width: 400px;
  padding: 20px;
  height: 360px;
}
</style>