import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import homepage from '../views/homepage/index.vue'
import personalPossesion from '../views/personalPossesion/index.vue'
import setting from '../views/setting/index.vue'
const routes = [
  {
    path: '',
    redirect: '/home'
  },{
    path: '/home',
    name: 'Home',
    component: Home,
    children:[
      {
        path: '',
        component: homepage
      },{
        path: 'personalPossesion',
        component: personalPossesion
      },{
        path: 'setting',
        component: setting
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
