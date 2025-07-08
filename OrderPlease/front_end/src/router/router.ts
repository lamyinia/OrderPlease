import {createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw} from "vue-router";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/",
        name: "默认路径",
        redirect: "/login"
    },
    {
        path: "/login",
        name: "登录",
        component: () => import('@/views/login/Login.vue')
    },
    {
        path: "/dashboard",
        name: "桌面",
        component: () => import('@/views/dashbord/DashBoard.vue')
    },
    {
        path: "/404",
        name: "发生错误",
        component: () => import('@/views/Static404.vue')
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
