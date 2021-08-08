import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import F2 from '@antv/f2';


createApp(App).use(F2);
createApp(App).use(store).use(router).mount('#app')
