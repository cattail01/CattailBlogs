import {createRouter, createWebHistory} from 'vue-router';
import TodoList from "@/views/todolist/TodoList.vue";
import HomePage from "@/views/homepage/HomePage.vue";

// [function] 按照如下格式创建路由
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomePage
        },


        // region learn
        {
            path: '/learn/todolist',
            name: "TodoList",
            component: TodoList

        },
        // endregion learn
    ]
})

export default router
