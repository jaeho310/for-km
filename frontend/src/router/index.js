import Router from 'vue-router'
import Home from '@/views/Home'
import Login from '@/views/Login'
import Vue from 'vue'

Vue.use(Router)
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: "Home",
      component: Home
    }
    ,{
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
