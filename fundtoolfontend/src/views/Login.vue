<template>
  <div class="main">
    <n-grid x-gap="12" :cols="3">
      <n-gi>
        <div class="light-green"></div>
      </n-gi>
      <n-gi>
        <div class="green">
            <n-card title="登录" style="padding-bottom:100px;">
              <template #cover>
                <n-space justify="space-around" size="48" style="padding:100px;">
                  <n-avatar
                    round
                    :size="48"
                    src="https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg"
                  />
                </n-space>
              </template>
              <n-form>
                <n-form-item>
                  <n-input
                    placeholder="用户名/邮箱"
                    v-model="userName"
                    />
                </n-form-item>
                <n-form-item>
                  <n-input
                    type="password"
                    show-password-on="mousedown"
                    placeholder="密码"
                    :maxlength="8"
                    v-model="userPassWord"
                  />
                </n-form-item>
                <n-form-item>
                  <n-button @click="handleValidateClick" attr-type="button">登录</n-button>
                  <n-button text style="margin-left:20px;">注册</n-button>
                </n-form-item>
              </n-form>
            </n-card>
        </div>
      </n-gi>
      <n-gi>
        <div class="light-green"></div>
      </n-gi>
    </n-grid>
  </div>
</template>
<script>
import {ref} from 'vue'
export default {
  setup() {
    const userName = ref('');
    const userPassWord = ref('');
    return {
      userName,
      userPassWord
    };
  },
};
</script>
<style lang="scss" scoped>
.main{
  height: 100%;
  background-color: rgba(17, 17, 20);
}
.light-green {
  height: 600px;
  background-color: rgba(0, 128, 0, 0.12);
}
.green {
  height: 600px;
  background-color: rgba(0, 128, 0, 0.24);
}
</style>