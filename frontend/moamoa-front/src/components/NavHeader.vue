<template>
  <div class="container">
    <div class="row align-items-center">
      <div class="col-md-4 col-12 text-center">
        <a href="/">
          <img src="@/assets/moamoa_logo_org.png" alt="로고 이미지" class="logo">
        </a>
      </div>
      <div class="col-md-5 col-12 my-md-0 my-3 mt-md-4 text-center mx-auto">
        <div class="input-group">
          <!-- 검색 입력 폼 -->
          <input type="search" v-model="searchTerm" class="form-control" placeholder="해시태그를 입력하세요">
          <button @click="search" class="btn btn-custom" type="button"><i class="bi bi-search"></i></button>
        </div>
      </div>
      <div class="col-md-3 col-12 text-md-end text-start mt-3">
        <template v-if="accessToken">
          <button type="button" class="btn btn-mypage me-md-2" @click="toMyPage">My Page</button>
          <button type="button" class="btn btn-logout" @click="logout">Logout</button>
        </template>
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
  try {
    if (!searchTerm.value) {
      alert('검색어를 입력해주세요.');
      return;
    }
    
    // 검색어를 백엔드로 보내고 해당 경로로 이동
    const response = await axios.get(`http://localhost:8080/api/post/listhashtag/0?hashtag=${searchTerm.value}`);
    
    // 검색 결과가 있는 경우에만 이동
    if (response.data.length > 0) {
      console.log(response.data);
      // Query string을 사용하여 데이터 전달
      router.push({ name: 'search', query: { q: searchTerm.value } });
    } else {
      alert('검색 결과가 없습니다.');
    }
  } catch (error) {
    console.error('Error while searching:', error);
    alert('검색 중에 오류가 발생했습니다.');
  }
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
  color: #498C74; /* 텍스트 색상을 회원가입 버튼과 같은 색으로 변경 */ } .btn-mypage { background-color: #498C74; border-color: #f4f4f4; } .btn-logout { background-color: #f4f4f4; border-color: #f4f4f4; color: #498C74; } nav svg { width: 2rem; height: 2rem; } .icon-text { display: block; margin-top: 0.5rem } /* 아이콘 크기 조정 */ .bi { font-size: 2rem; /* 아이콘 크기를 조정 */ } /* 상단 부분의 여백 조절 크기를 위함. */ .search-bar, .login-buttons { margin-top: 2rem; /* 위쪽 여백을 추가하여 아래로 내림 */ } /* 추가한 로고 이미지 스타일 */ .logo { width: 200px; /* 로고 이미지의 너비를 조절합니다. */ 
  height: auto; /* 높이 자동 조정 */ 
} 
.btn-custom {
  background-color: transparent; /* 버튼의 배경색을 투명하게 설정 */
  border: none; /* 테두리 제거 */
}

.btn-custom i {
  color: #6c757d; /* 아이콘 색상 지정 */
}

.btn-custom:hover i {
  color: #495057; /* 마우스 호버시 아이콘 색상 변경 */
}
</style>
