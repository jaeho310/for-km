import Router from 'vue-router'
import Home from '@/views/Home'
import Login from '@/views/Login'
import Product from '@/views/Product'
import Join from '@/views/Join'
import OrderList from '@/views/OrderList'
import Vue from 'vue'

Vue.use(Router)
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      memberName: "Home",
      component: Home
    },
    {
      path: '/login',
      memberName: 'Login',
      component: Login
    },
    {
     path: '/product',
     memberName: 'Product',
     component: Product
    },
    {
      path: '/join',
      memberName: 'Join',
      component: Join
    },
    {
      path: '/order-list',
      memberName: 'OrderList',
      component: OrderList
    },
  ]
})
