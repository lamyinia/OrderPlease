import axios from "axios"
import {useUserStore} from "@/stores/UserStorage";
import router from "@/router/router";

const service = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    "timeout": 600000
})

service.interceptors.request.use((config) => {
    const userStore = useUserStore()
    if (userStore.token){
        config.headers['token'] = userStore.token
    } else if (config.url != '/login'){
        window.location.href = '/login'
        return config
    }

    return config
})

service.interceptors.response.use(
    (response) => {
        // response.config.url = response.config.url.replace('/api', '')

        return response
    },
    (error) => {

        if (error.response?.status === 401) {
            const userStore = useUserStore()
            userStore.clearAuthData()
            router.push('/login')
        }

        return Promise.reject(error)
    }
)
export default service