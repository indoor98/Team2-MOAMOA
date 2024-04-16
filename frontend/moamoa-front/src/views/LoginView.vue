<template>
  <div class="container">
    <!-- 이미지 삽입 -->
    <img src="@/assets/moamoa_logo_org.png" alt="로고 이미지" class="logo">
    <div class="login-container">
      <form class="login-form" @submit.prevent="handleSubmit">
        <input type="text" v-model="email" placeholder="아이디 입력">
        <br>
        <input type="password" v-model="password" placeholder="비밀번호 입력">
        <br>
        <button type="submit">로그인</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const email = ref('')
const password = ref('')
const accessToken = ref('')
const refreshToken = ref('')
const router = useRouter()

// 페이지가 로드될 때 마다 확인하는 함수
onMounted( () => {
  //로컬 스토리지에서 accessToken 존재하는지 확인
  accessToken.value = localStorage.getItem('accessToken');

  // accessToken이 존재할 경우 리다이렉트
  if (accessToken.value) {
    router.push({ name: 'home' })
  }
})

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

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column; /* 이미지 및 폼을 세로로 정렬하기 위해 추가 */
  height: 100vh; /* 화면 전체 높이 */
}

.login-container {
  text-align: center;
  width: 350px; /* 폼의 너비를 조정합니다. */
  padding: 20px;
  border-radius: 10px; /* 모서리 둥근 사각형 */
  background-color: #ffd557; /* 로그인 입력 부분의 배경색 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  margin-top: 20px; /* 이미지와 간격 조정 */
}
.login-container input {
  width: calc(100% - 16px);
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 5px; /* 입력란의 모서리 둥글게 */
  border: 1px solid #ccc;
}
.login-container button {
  width: 100%;
  padding: 8px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.login-form {
  background-color: #ffd557; /* 로그인 입력 부분의 배경색 */
}

/* 로고 이미지 스타일 */
.logo {
  width: 250px; /* 로고 이미지의 너비를 조정합니다. */
  height: auto; /* 높이 자동 조정 */
}
</style>