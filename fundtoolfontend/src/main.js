import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import F2 from '@antv/f2'
import Vant from 'vant'
import 'vant/lib/index.css'
import axios from "axios";

const app = createApp(App)
app.config.globalProperties.$axios = axios;
axios.defaults.baseURL='/api'
app.config.globalProperties.F2 = F2
app.use(store)
    .use(router)
    .use(Vant)
    .mount('#app')

