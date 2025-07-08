<script setup lang="ts">
  import {ref} from "vue";


  const arr = ref<number[]>([2, 3, 4])

  const clicked = async (newArr: number[]) => {
    newArr[2] += 1
  }

  const clicked2 = async () => {
    console.log(arr)
  }
</script>

<template>
  <div>
    this is DashBoard !!!
  </div>
  <div>
    <el-button @click="clicked(arr)">我是按钮</el-button>
    <el-button @click="clicked2">我是按钮</el-button>
  </div>
</template>

<style scoped lang="scss">

</style>