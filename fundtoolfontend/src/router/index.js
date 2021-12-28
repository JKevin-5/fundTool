import { createRouter, createWebHistory } from 'vue-router'
const routes = [
  {
    path: '',
    redirect: '/login'
  },{
    path: '/login',
    name: 'Login',
    component: ()=>import('../views/Login.vue')
  },{
    path: '/home1',
    name: 'Home1',
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
      }
    ]
  },{
    path: '/fund',
    name: 'Fund',
    component: ()=> import('../views/fundPage/fundPage.vue')
  },{
    path: '/home',
    name: 'Home',
    component: ()=> import('../views/HomePage.vue'),
    children:[
      {
        path: 'funds',
        meta: {
          keepAlive: true //设置页面是否需要使用缓存
        },
        component: ()=>import('../views/fundPage/fundPage.vue')
      },{
        path: 'fundHis',
        meta: {
          keepAlive: true
        },
        component: ()=>import('../views/fundPage/fundDetailPage.vue')
      }
    ]
  },{
    path: '/test',
    component: ()=> import('../views/CollectionPage.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
