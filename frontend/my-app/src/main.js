import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

//View
import Registration from './components/Registration.vue'
import Login from './components/Login.vue'
import AccountRecovery from './components/AccountRecovery.vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.prototype.$axios = axios;
Vue.config.productionTip = false

Vue.use(VueAxios, axios)
Vue.use(VueRouter);
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Vue.axios.defaults.baseURL = 'http://localhost:3030';


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    { path: '/registration',
      name:'Registration',
      component: Registration 
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/accRecovery',
      name: 'AccountRecovery',
      component: AccountRecovery
    }
  ]
});


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
