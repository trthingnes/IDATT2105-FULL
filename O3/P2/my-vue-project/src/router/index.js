import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

const routes = [
  {
    path: '/',
    redirect: { name: 'login' }
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '@/views/Register.vue')
  },
  {
    path: '/user',
    name: 'user',
    component: () => import(/* webpackChunkName: "user" */ '@/views/User.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
