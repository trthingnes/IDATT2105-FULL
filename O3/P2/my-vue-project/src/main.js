import { createApp } from 'vue'
import store from './store'
import router from './router'
import app from './App.vue'

createApp(app).use(router).use(store).mount('#app')
