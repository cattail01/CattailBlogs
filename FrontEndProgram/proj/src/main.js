import { createApp } from 'vue'
import App from './app/App.vue'
import router from './router/index.js'

// [process] 通过指定vue页面创建vue应用，该vue页面为应用的主页面
const app = createApp(App)

// [process] 指定该应用使用的router
app.use(router)

// [process] 指定该应用渲染的位置
app.mount('#app')
