import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'
//import store from '@/store'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/Manage.vue'),
    redirect: "/home",
    children: [
      { path: 'home', name: '首页', component: () => import('../views/Home.vue')},
      { path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
      { path: 'consult', name: '咨询', component: () => import('../views/Consult.vue')},
      { path: 'trans', name: '转换', component: () => import('../views/Trans.vue')},
      { path: 'analysis', name: '综合分析', component: () => import('../views/Analysis.vue')},
      { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
      { path: 'file', name: '文件管理', component: () => import('../views/File.vue')},
      { path: 'password', name: '密码修改', component: () => import('../views/Password.vue')},
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/front/Front.vue'),
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('../views/front/Home.vue'),
      },
      {
        path: 'consult',
        name: 'Consult',
        component: () => import('../views/front/Consult.vue'),
      },
      {
        path: 'analysis',
        name: 'analysis',
        component: () => import('../views/front/Analysis.vue'),
      }
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to,from,next) => {//路由守卫
  localStorage.setItem("currentPathName",to.name)//设置当前路由名称
  //console.log(from)
  next()//放行
})

export default router
