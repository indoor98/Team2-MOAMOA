<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const email = ref('')
const password = ref('')
const accessToken = ref('')
const refreshToken = ref('')
const router = useRouter()

const handleSubmit = async () => {
  try {
    const response = await axios.post("http://localhost:8080/api/auth/signin", {
      email: email.value,
      password: password.value
    })
    console.log(response.data)
    accessToken.value = response.data.accessToken
    refreshToken.value = response.data.refreshToken

    localStorage.setItem('accessToken', accessToken.value)
    localStorage.setItem('refreshToken', refreshToken.value)

    router.push({ name: "home" })
  } catch (error) {
    console.error(error)
  }
}
</script>


<template>
  <div class="login-container">
    <h2 style = "color: white; font-weight: bold; padding-top:20px;">MOA<br/>MOA</h2>
    <form class = "login-form" @submit.prevent="handleSubmit">
      <input type="text" v-model="email" placeholder="아이디 입력">
      <br>
      <input type="password" v-model="password" placeholder="비밀번호 입력">
      <br>
      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<style scoped>
.login-container {
  text-align: center;
  width: 100dvw;
  height: 100dvh;
  padding: 10px;
  background-color: #498C74; /* 로그인 입력 부분의 배경색 */
}

.login-container input {
  width: 30dvw; /* 입력란 너비 */
  margin-bottom: 10px;
  padding: 8px;
}
.login-container button {
  width: 10dvw; /* 버튼 너비 */
  padding: 8px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}
.login-form{
  padding-top: 20px;
  background-color: #498C74;
}
/*
.login-form button{
  background-color: gray;
  color : black;
}
*/

</style>