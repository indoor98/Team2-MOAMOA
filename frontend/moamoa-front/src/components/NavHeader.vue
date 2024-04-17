<template>
  <div class="container">
    <div class="row align-items-center"> <!-- align-items-center 추가 -->
      <div class="col-md-4 col-12 text-center"> <!-- text-start에서 text-center로 변경 -->
        <a href="/">
          <img src="@/assets/moamoa_logo_org.png" alt="로고 이미지" class="logo"> <!-- 클래스 추가 -->
        </a>
      </div>
      <div class="col-md-5 col-12 my-md-0 my-3 mt-md-4 text-center mx-auto">
        <!-- 검색 입력 폼 -->
        <input type="search" v-model="searchTerm" @input="search" class="form-control" placeholder="Search...">
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

<script setup>
import { useRouter } from 'vue-router'
import axios from "axios";
import { ref } from 'vue'

const router = useRouter()

const accessToken = localStorage.getItem('accessToken')
const searchTerm = ref('');

const toLogin = () => {
  router.push({ name: 'login' })
}

const toSignup = () => {
  router.push({ name: 'signup' })
}

const toMyPage = () => {
  router.push({ name: 'mypage' })
}

const logout = async () => {
  const accessToken = localStorage.getItem('accessToken');

  const response = await axios.get("http://localhost:8080/api/auth/logout", {
    headers: {
      Authorization: `Bearer ${accessToken}`
    }
  });

  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  router.push({ name: 'login' })
}

// 검색 메서드
const search = async () => {
  // 검색어를 홈 뷰로 전달
  router.push({ name: 'home', query: { searchTerm: searchTerm.value } });
};
</script>


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
/* 회원가입 버튼의 텍스트 색상을 흰색으로 변경 */
.btn-signup {
  background-color: #498C74;
  border-color: #498C74; /* 회원가입 버튼과 같은 색의 테두리 추가 */
  color: #fff; /* 텍스트 색상을 흰색으로 변경 */
}

/* 로그인 버튼의 테두리와 텍스트 색상 변경 */
.btn-login {
  background-color: #f4f4f4;
  border-color: #498C74; /* 회원가입 버튼과 같은 색의 테두리 추가 */
  color: #498C74; /* 텍스트 색상을 회원가입 버튼과 같은 색으로 변경 */
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

/* 추가한 로고 이미지 스타일 */
.logo {
  width: 200px; /* 로고 이미지의 너비를 조절합니다. */
  height: auto; /* 높이 자동 조정 */
}
</style>
