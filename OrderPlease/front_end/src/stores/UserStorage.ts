import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import {login} from "@/api/EmployeeApi";

/**
 * 用户状态存储
 */
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
        userInfo: Cookies.get(COOKIE_KEYS.USER_INFO) ? JSON.parse(Cookies.get(COOKIE_KEYS.USER_INFO) as string) : null,
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
            try {
                const username = params.username.trim()

                const { data } = await login({ username, password: params.password })

                if (String(data.code) !== '1') {
                    throw new Error(data.msg || '登录失败')
                }

                this.token = data.data.token
                this.username = data.data.userName
                this.userInfo = data.data

                this.updateCookies()

                return data
            } catch (error: any) {
                console.error('登录失败:', error.message)
                throw error
            }
        },

        async getUserInfo() {
            if (!this.token) {
                throw new Error('未登录或 token 无效')
            }
            if (!this.userInfo) {
                throw new Error('用户信息未找到，请重新登录')
            }

            return this.userInfo
        },

        async logout() {

        },

        clearAuthData() {
            this.token = ''
            this.userInfo = null
            this.storeId = ''
            this.username = ''

            Object.values(COOKIE_KEYS).forEach(key => {
                Cookies.remove(key)
            })
        },

        updateCookies(){
            // 设置安全选项 (生产环境下启用 secure 标志)
            const cookieOptions = {
                expires: 7, // 7天有效期
                secure: import.meta.env.PROD, // 生产环境启用 secure
                sameSite: 'strict' as const
            }
            if (this.token) {
                Cookies.set(COOKIE_KEYS.TOKEN, this.token, cookieOptions)
            }
            if (this.storeId) {
                Cookies.set(COOKIE_KEYS.STORE_ID, this.storeId, cookieOptions)
            }
            if (this.userInfo) {
                Cookies.set(COOKIE_KEYS.USER_INFO, JSON.stringify(this.userInfo), cookieOptions)
            }
            if (this.username) {
                Cookies.set(COOKIE_KEYS.USERNAME, this.username, cookieOptions)
            }
        },
        changeStore(data: {storeId: string; token: string}){
            this.storeId = data.storeId
            this.token = data.token
            this.updateCookies()
        }
    }
})