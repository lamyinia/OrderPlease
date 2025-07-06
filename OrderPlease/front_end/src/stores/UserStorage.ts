import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import {login} from "@/api/EmployeeApi";

interface UserInfo {
    id: number
    userName: string
    name: string
    avatar: string
    introduction: string
    roles: string[]
    storeId: string
    [key: string]: any // 允许扩展属性
}
interface LoginParams {
    username: string
    password: string
}
interface UserState {
    token: string
    userInfo: UserInfo | null
    storeId: string
    username: string
}
const COOKIE_KEYS = {
    TOKEN: 'token',
    STORE_ID: 'storeId',
    USER_INFO: 'userInfo',
    USERNAME: 'username'
}

export const useUserStore = defineStore('user', {
    state: (): UserState => ({
        token: Cookies.get(COOKIE_KEYS.TOKEN) || '',
        userInfo: Cookies.get(COOKIE_KEYS.USER_INFO)
            ? JSON.parse(Cookies.get(COOKIE_KEYS.USER_INFO) as string)
            : null,
        storeId: Cookies.get(COOKIE_KEYS.STORE_ID) || '',
        username: Cookies.get(COOKIE_KEYS.USERNAME) || ''
    }),
    getters: {
        isAuthenticated: (state) => !!state.token,
        roles: (state) => state.userInfo?.roles || [],
        avatar: (state) => state.userInfo?.avatar || '',
        introduction: (state) => state.userInfo?.introduction || '',
        fullName: (state) => state.userInfo?.name || '',
    },
    actions: {
        async login(params: LoginParams){
            const username = params.username.trim()

            const { data } = await login({ username, password: params.password })

            if (String(data.code) !== '1'){
                throw new Error(data.msg || '登录失败')
            }


            this.token = data.data.token
            this.userInfo = data.data as UserInfo
            this.storeId = data.data.storeId || ''
            this.username = username
            return data
        }
    }
})