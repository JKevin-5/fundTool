import { createRouter, createWebHistory } from 'vue-router'
const routes = [
  {
    path: '',
    redirect: '/login'
  },{
    path: '/login',
    name: 'Login',
    component: ()=>import('../pages/login/Login.vue')
  },{
    path: '/fund',
    name: 'Fund',
    component: ()=> import('../pages/funds/fundPage.vue')
  },{
    path: '/home',
    name: 'Home',
    component: ()=> import('../pages/home/homePage.vue'),
    children:[
      {
        path: '',
        redirect: '/home/funds'
      },
      {
        path: 'funds',
        meta: {
          keepAlive: true //设置页面是否需要使用缓存
        },
        component: ()=>import('../pages/funds/fundPage.vue')
      },{
        path: 'fundHis',
        meta: {
          keepAlive: true
        },
        component: ()=>import('../pages/funds/fundDetailPage.vue')
      },{
        path: 'possesions',
        meta:{
          keepAlive: true
        },
        component: ()=>import('../pages/possession/possesionPage.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
