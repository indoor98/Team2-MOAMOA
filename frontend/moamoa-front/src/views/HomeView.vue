<script>
;
import {useRouter} from "vue-router";
const router = useRouter();
export default {
  data() {
    return {
      sample:''
    }
  },
  methods: {
    async toLogin() {
      try {
        this.$router.push({
          name: "login",
        });
      } catch(error) {
        // 요청 실패 처리
        console.error(error);
      }
    },

    async toSignup() {
      try {
        this.$router.push({
          name: "signup",
        });
      } catch(error) {
        // 요청 실패 처리
        console.error(error);
      }
    }
  }
}

</script>

<template>

  <header>
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-12 text-start">
          <a class="navbar-brand" href="#">MOA<br>MOA</a>
        </div>
        <div class="col-md-5 col-12 my-md-0 my-3 mt-3">
          <input type="search" class="form-control" placeholder="Search...">
        </div>
        <div class="col-md-3 col-12 text-md-end text-start mt-3">
          <button type="button" class="btn btn-signup me-md-2" @click="toSignup">SignUp</button>
          <button type="button" class="btn btn-login" @click="toLogin">Login</button>
        </div>
      </div>
    </div>
  </header>

  <nav class="container mt-5 d-flex justify-content-around">
    <div class="text-center">
      <button class="btn" onclick="window.location.href='YOUR_FOOD_PAGE_URL';">
        <i class="bi bi-cup-straw"></i>
        <span class="icon-text">Food</span>
      </button>
    </div>
    <div class="text-center">
      <button class="btn" onclick="window.location.href='YOUR_MUSIC_PAGE_URL';">
        <i class="bi bi-headphones"></i>
        <span class="icon-text">Music</span>
      </button>
    </div>
    <div class="text-center">
      <button class="btn" onclick="window.location.href='YOUR_ADD_PAGE_URL';">
        <i class="bi bi-plus-circle-fill"></i>
        <span class="icon-text">Add</span>
      </button>
    </div>

    <div class="text-center">
      <button class="btn" onclick="window.location.href='YOUR_INSTAGRAM_PAGE_URL';">
        <i class="fab fa-instagram fa-3x"></i>
        <span class="icon-text">Instagram</span>
      </button>
    </div>


    <!-- <div class="text-center">
        <button class="btn" onclick="window.location.href='YOUR_INSTAGRAM_PAGE_URL';">
            <img src="img\free-icon-instagram-174855.png" alt="Instagram" style="width: 24px; height: 24px;">
            <span class="icon-text">Instagram</span>
        </button>
    </div> -->

    <div class="text-center">
      <button class="btn" onclick="window.location.href='YOUR_POINTER_PAGE_URL';">
        <i class="bi bi-cursor-fill"></i>
        <span class="icon-text">Pointer</span>
      </button>
    </div>
  </nav>

  <!-- 게시글을 위한 부분 -->
  <div class="container mt-5">
    <div class="row g-4">
      <!-- 여기에 게시글 반복 시작 -->
      <div class="col-md-4">
        <div class="card">
          <img src="../img/coupang_logo.png" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">제목</h5>
            <p class="card-text">게시글의 내용 미리보기 추가</p>
          </div>
        </div>
      </div>
      <!-- 게시글 반복 끝 -->
      <!-- 반복 블록을 필요한 만큼 복사하여 추가 -->
    </div>
  </div>
</template>

<style scoped>

header {
  background-color: #498C74;
  color: white;
}
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
nav svg {
  width: 2rem;
  height: 2rem;
}
.icon-text {
  display: block;
  margin-top: 0.5rem;
}
/* 아이콘 크기 조정 */
.bi {
  font-size: 2rem; /* 아이콘 크기를 조정 */
}
/* 상단 부분의 여백 조절 크기를 위함. */
.search-bar, .login-buttons {
  margin-top: 2rem; /* 위쪽 여백을 추가하여 아래로 내림 */
}

/* 게시글 스타일 조정을 위한 css 추가 */
.card-img-top {
  border-radius: 50%; /* 이미지를 원형으로.. */
  background: white; /* 배경 색상 */
  width: 150px; /* 이미지 크기 */
  height: 150px; /* 이미지 높이 */
  padding: 10px; /* 안쪽 여백 */
  object-fit: cover; /* 이미지가 태그를 꽉 채우도록 */
  border: 5px solid white; /* 흰색 테두리 */
}
</style>