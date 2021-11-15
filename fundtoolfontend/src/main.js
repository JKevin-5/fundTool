import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import F2 from '@antv/f2'
import Vant from 'vant'
import 'vant/lib/index.css'
import axios from "axios";
import '@/assets/design.scss'
import naive from 'naive-ui'

const app = createApp(App)
app.config.globalProperties.$axios = axios;
app.config.globalProperties.F2 = F2
app.use(store)
    .use(router)
    .use(Vant)
    .use(naive)
    .mount('#app')

