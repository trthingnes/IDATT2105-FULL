import { createRouter, createWebHistory } from "vue-router"
import Home from "../views/Home.vue"

const routes = [
    {
        path: "/",
        name: "home",
        component: Home,
    },
    {
        path: "/contact",
        name: "contact",
        // route level code-splitting
        // this generates a separate chunk (contact.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "contact" */ "../views/Contact.vue"),
    },
    {
        path: "/login",
        name: "login",
        component: () =>
            import(/* webpackChunkName: "login" */ "../views/Login.vue"),
    },
    {
        path: "/register",
        name: "register",
        component: () =>
            import(/* webpackChunkName: "register" */ "../views/Register.vue"),
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router
