import {useRouter} from "vue-router";
import {ref} from "vue";
import {useUserStore} from "@/stores/UserStorage";

export function useAuth() {
    const userStore = useUserStore()
    const router = useRouter()
    const loading = ref<boolean>(false)

    const login = async (username: string, password: string) => {
        loading.value = true
        try {
            await userStore.login({username, password})
            await userStore.getUserInfo()

            return true
        } catch (error){
            console.log(error)
            return false
        } finally {
            loading.value = false
        }
    }

    const logout = async () => {
        loading.value = true
        try {

        } catch (error){
            console.log(error)
            return false
        } finally {
            loading.value = false
        }
    }

    return {
        user: userStore,
        loading,
        isAuthenticated: userStore.isAuthenticated,
        login,
        logout
    }
}