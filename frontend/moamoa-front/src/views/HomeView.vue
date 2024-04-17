<template>
  <div>
    <header>
      <NavHeader />
    </header>
    <div class="container">
      <div class="row justify-content-center mt-5">
        <img src="@/assets/home/moamoa_home_01.png" alt="로고 이미지" class="logodetail1" />
        <img src="@/assets/home/moamoa_home_02.png" alt="로고 이미지" class="logodetail2" />
        <img src="@/assets/home/moamoa_home_03.png" alt="로고 이미지" class="logodetail3" />
      </div>
    </div>
    <!-- 게시글 작성 버튼 추가 -->
    <div class="container mt-3">
      <button @click="goToWritePage" class="btn btn-lg btn-custom">상품 등록</button>
    </div>
    <div class="container">
      <div class="row g-4 mt-5">
        <PostCard v-for="(post, index) in posts" :key="index" :post="post" />
      </div>
    </div>
    <!-- 페이지네이션 버튼 추가 -->
    <div class="container mt-3 pagination">
      <button @click="fetchPosts(currentPage - 1)" :disabled="currentPage <= 0" class="btn btn-lg btn-custom">이전</button>
      <span>{{ currentPage + 1 }}</span>
      <button @click="fetchPosts(currentPage + 1)" class="btn btn-lg btn-custom">다음</button>
    </div>
    <!-- 공간을 띄우기 위한 마진 추가 -->
    <div class="container mt-5"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import NavHeader from "@/components/NavHeader.vue";
import PostCard from "@/components/PostCard.vue"; // PostCard.vue 컴포넌트 추가

const router = useRouter();
const posts = ref([]);
const currentPage = ref(0); // 현재 페이지 번호를 관리하는 ref 추가

const fetchPosts = async (page) => {
  currentPage.value = page; // 페이지 업데이트
  try {
    const response = await axios.get(`http://localhost:8080/api/post/list/${currentPage.value}`);
    posts.value = response.data; // 게시글 데이터 업데이트
  } catch (error) {
    console.error(error);
  }
};

onMounted(() => {
  fetchPosts(0); // 초기 페이지 로드
});

const goToWritePage = () => {
  // 게시글 작성 페이지로 이동하는 메서드
  router.push({ name: "postwrite" });
};
</script>

<style scoped>
header {
  background-color: #f7efe4;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}
body {
  background-color: #f7efe4; /* 전체 페이지의 배경색 변경 */
}
.container {
  text-align: center; /* 내용을 가운데 정렬 */
}
.logodetail1,
.logodetail3 {
  width: calc(25% - 20px); /* 화면 가로로 가득 차게 설정 */
  height: auto; /* 높이 자동 조정 */
  margin: 10px; /* 이미지 간격 조절 */
}
.logodetail2 {
  width: 25%; /* 로고 detail2 이미지의 너비를 설정 */
  height: auto; /* 높이 자동 조정 */
}
.btn-lg {
  font-size: 20px; /* 버튼 크기 조절 */
  padding: 10px 20px; /* 버튼 내부 여백 조절 */
}
.btn-custom {
  background-color: #fdbcbc; /* 버튼의 배경색을 지정 */
  color: white; /* 텍스트 색상을 흰색으로 지정 */
  border: none; /* 테두리 제거 */
}

header {
  background-color: #f7efe4;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
}
body {
  background-color: #f7efe4;
}
.container {
  text-align: center;
}
.logodetail1,
.logodetail3 {
  width: calc(25% - 20px);
  height: auto;
  margin: 10px;
}
.logodetail2 {
  width: 25%;
  height: auto;
}
.btn-lg {
  font-size: 20px;
  padding: 10px 20px;
}
.btn-custom {
  background-color: #fdbcbc;
  color: white;
  border: none;
}

.pagination {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}

.pagination button {
  margin: 0 40px; /* 버튼 간격 조정 */
}
</style>

