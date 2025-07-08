<script setup lang="ts">
import {ref} from "vue"
import {useAuth} from "@/composable/UseAuth";
import router from "@/router/router";

interface LoginForm {
  username: string;
  password: string;
}

const {user, login, loading} = useAuth()

const clicked = async () => {
  const result = await login('admin', 'a123456')
}
const clicked2 = async () => {
  router.push('/dashboard')
}

const loginForm = ref<LoginForm>({
  username: 'admin',
  password: 'a123456'
})


const handleLogin = () => {

}

</script>

<template>
  <div>
    <el-button @click="clicked">按钮1</el-button>
  </div>
  <div>
    <el-button @click="clicked2">按钮2</el-button>
  </div>
</template>

<style scoped lang="scss">

</style>