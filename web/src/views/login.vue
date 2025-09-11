<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center"><rocket-two-tone />&nbsp;去哪啊售票系统</h1>
      <a-form :model="loginForm" name="basic" autocomplete="off">
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="手机号"/>
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: true, message: '请输入验证码!' }]"
        >
          <a-input v-model:value="loginForm.code">
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>
</template>

<script>
import { defineComponent, reactive } from 'vue';
import { notification } from 'ant-design-vue';
import { useRouter } from 'vue-router'
import store from "@/store";

export default defineComponent({
  name: "login-view",
  setup() {
    const router = useRouter();

    const loginForm = reactive({
      mobile: '',
      code: '',
    });

    // 模拟发送验证码
    const sendCode = () => {
      if (loginForm.mobile) {
        notification.success({ description: '发送验证码成功！（模拟）' });
        loginForm.code = "8888"; // 假验证码
      } else {
        notification.error({ description: '请输入手机号！' });
      }
    };

    // 模拟登录
    const login = () => {
      if (loginForm.code === "8888") {
        notification.success({ description: '登录成功！（模拟）' });
        router.push("/welcome");
        store.commit("setMember", { mobile: loginForm.mobile });
      } else {
        notification.error({ description: '验证码错误！（模拟）' });
      }
    };

    return {
      loginForm,
      sendCode,
      login
    };
  },
});
</script>

<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}
.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>
