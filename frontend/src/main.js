import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from './router'
import vuetify from './plugins/vuetify'
import store from './store'
import { sync } from 'vuex-router-sync'
// import customDialog from './views/dialog/CustomDialog.vue'

// Vue.component('CustomDialog', customDialog)
Vue.config.productionTip = false
Vue.prototype.$http = axios

sync(store, router)

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
