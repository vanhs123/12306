import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';

import * as Icons from '@ant-design/icons-vue'
import app from "@/App.vue";


createApp(App).use(Antd).use(store).use(router).mount('#app')

const icons = Icons;
for(const icon of icons){
    app.component(icon.name, icon)
}
