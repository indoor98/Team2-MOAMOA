<script setup>

import { useRouter } from 'vue-router'
import axios from "axios";

const router = useRouter()

const accessToken = localStorage.getItem('accessToken')

const toLogin = () => {
  router.push({ name: 'login' })
}

const toSignup = () => {
  router.push({ name: 'signup' })
}

const toMyPage = () => {
  router.push({ name: 'mypage' })
}

const logout = () => {

  const accessToken = localStorage.getItem('accessToken');
  console.log(accessToken);

  const response = axios.get("http://localhost:8080/api/auth/logout", {
    headers: {
      Authorization: `Bearer ${accessToken}`
    }
  });

  console.log(response);
  // 로그아웃 후 로그인 페이지로 이동
  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  router.push({ name: 'login' })
}

</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-12 text-start">
        <a class="navbar-brand" href="/">MOA<br>MOA</a>
      </div>
      <div class="col-md-5 col-12 my-md-0 my-3 mt-md-4 text-center mx-auto">
        <input type="search" class="form-control" placeholder="Search...">
      </div>
      <div class="col-md-3 col-12 text-md-end text-start mt-3">
        <!-- accessToken이 있는 경우 -->
        <template v-if="accessToken">
          <button type="button" class="btn btn-mypage me-md-2" @click="toMyPage">My Page</button>
          <button type="button" class="btn btn-logout" @click="logout">Logout</button>
        </template>
        <!-- accessToken이 없는 경우 -->
        <template v-else>
          <button type="button" class="btn btn-signup me-md-2" @click="toSignup">SignUp</button>
          <button type="button" class="btn btn-login" @click="toLogin">Login</button>
        </template>

      </div>
    </div>
  </div>
</template>

<style scoped>
header .container {
  padding: 10px 0;
}

.navbar-brand {
  font-size: 1.7rem; /* Adjust the size of the brand/logo */
}
.search-bar input[type="search"] {
  max-width: 500px; /* Adjust the width of the search bar */
}
.btn-signup {
  background-color: #498C74;
  border-color: #f4f4f4;
}
.btn-login {
  background-color: #f4f4f4;
  border-color: #f4f4f4;
  color: #498C74;
}
.btn-mypage {
  background-color: #498C74;
  border-color: #f4f4f4;
}
.btn-logout {
  background-color: #f4f4f4;
  border-color: #f4f4f4;
  color: #498C74;
}
nav svg {
  width: 2rem;
  height: 2rem;
}

.icon-text {
  display: block;
  margin-top: 0.5rem
}
/* 아이콘 크기 조정 */
.bi {
  font-size: 2rem; /* 아이콘 크기를 조정 */
}
/* 상단 부분의 여백 조절 크기를 위함. */
.search-bar, .login-buttons {
  margin-top: 2rem; /* 위쪽 여백을 추가하여 아래로 내림 */
}
</style>
