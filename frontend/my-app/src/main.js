import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

//View
import Registration from './components/Registration.vue'
import Login from './components/Login.vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

Vue.use(VueRouter);
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

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
  ]
});


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
