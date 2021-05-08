import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Vuelidate from 'vuelidate'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'


//View
import Registration from './components/Registration.vue'
import Login from './components/Login.vue'
import AccountRecovery from './components/AccountRecovery.vue'
import Home from './components/Home.vue'
import ResetPassword from './components/ResetPassword.vue'
import AllUsers from './components/AllUsers'
import Profile from './components/Profile'
import Search from './components/Search'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.prototype.$axios = axios;
Vue.config.productionTip = false

Vue.use(VueAxios, axios);
Vue.use(Vuelidate);
Vue.use(VueRouter);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

Vue.axios.defaults.baseURL = 'https://localhost:3030';


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
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/reset_password/:jwt',
      name: 'ResetPassword',
      component: ResetPassword
    },
    {
      path: '/allUsers',
      name: 'AllUsers',
      component: AllUsers
    },
    {
      path: '/profile/:username',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/search/:name',
      name: 'Search',
      component: Search,
    }
  ]
});


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
