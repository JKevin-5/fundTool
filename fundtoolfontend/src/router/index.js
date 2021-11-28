import { createRouter, createWebHistory } from 'vue-router'
const routes = [
  {
    path: '',
    redirect: '/home'
  },{
    path: '/login',
    name: 'Login',
    component: ()=>import('../views/Login.vue')
  },{
    path: '/home',
    name: 'Home',
    component: ()=>import('../views/Home.vue'),
    children:[
      {
        path: '',
        name: 'Homepage',
        meta: {
          keepAlive: false //设置页面是否需要使用缓存
        },
        component: ()=> import('../views/homepage/index.vue')
      },{
        path: 'personalPossesion',
        name: 'PersonalPossesion',
        meta: {
          keepAlive: true //设置页面是否需要使用缓存
        },
        component: ()=> import('../views/personalPossesion/index.vue')
      },{
        path: 'setting',
        meta: {
          keepAlive: false //设置页面是否需要使用缓存
        },
        component: ()=>import('../views/setting/index.vue')
      }
    ]
  },{
    path: '/fund',
    name: 'Fund',
    component: ()=> import('../views/fundPage/index.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
