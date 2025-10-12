// src/main.js
import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'

import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css' // 如果你是 antd v3；v2 用 'ant-design-vue/dist/antd.css'

import * as Icons from '@ant-design/icons-vue'
import api from '@/utils/request'

const app = createApp(App)

// 注册所有 antd 图标（可选：只注册用到的更轻量）
Object.entries(Icons).forEach(([name, component]) => {
    app.component(name, component)
})

// 全局挂载 axios 实例
app.config.globalProperties.$api = api
app.provide('api', api)

app.use(Antd).use(store).use(router).mount('#app')
