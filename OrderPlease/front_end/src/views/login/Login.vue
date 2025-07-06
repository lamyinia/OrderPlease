<script setup lang="ts">
import {ref} from "vue"
import type { ElForm } from 'element-plus';
import service from "@/utils/request";
import {useUserStore} from "@/stores/UserStorage";

interface LoginForm {
  username: string;
  password: string;
}

const clicked = () => {
  const userStore = useUserStore()
  try {
    userStore.login({username:'admin', password:'a123456'})
  } catch (error) {
    console.error('登录失败:', error)
    return false
  }
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
    <el-button @click="clicked">按钮是我</el-button>
  </div>
</template>

<style scoped lang="scss">

</style>