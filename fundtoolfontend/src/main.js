import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import F2 from '@antv/f2'
import Vant from 'vant'
import 'vant/lib/index.css'
import axios from "axios";
import naive from 'naive-ui'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App,router)
app.config.globalProperties.$axios = axios;
app.config.globalProperties.F2 = F2
app.use(store)
    .use(router)
    .use(Vant)
    .use(naive)
    .use(ElementPlus)
    .mount('#app')

