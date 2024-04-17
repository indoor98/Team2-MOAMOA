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
    <div class="container mt-3">
      <button @click="goToWritePage" class="btn btn-lg btn-custom">상품 등록</button>
    </div>
    <div class="container">
      <div class="row g-4 mt-5">
        <PostCard v-for="(post, index) in posts" :key="index" :post="post" />
      </div>
    </div>
    <div class="container mt-3 pagination">
      <button @click="fetchPosts(currentPage - 1)" :disabled="currentPage <= 0" class="btn btn-lg btn-custom">이전</button>
      <span>{{ currentPage + 1 }}</span>
      <button @click="fetchPosts(currentPage + 1)" class="btn btn-lg btn-custom">다음</button>
    </div>
    <div class="container mt-5"></div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import NavHeader from "@/components/NavHeader.vue";
import PostCard from "@/components/PostCard.vue";

const router = useRouter();
const posts = ref([]);
const currentPage = ref(0);

onMounted(() => {
  // Initial fetch
  fetchPosts(0);
});

const fetchPosts = async (page) => {
  currentPage.value = page;
  try {
    // Fetch posts based on page number
    const response = await axios.get(`http://localhost:8080/api/post/listhashtag/${page}?hashtag=${router.currentRoute.value.query.q}`);
    posts.value = response.data;
    console.log(posts.value);
  } catch (error) {
    console.error("Error fetching posts:", error);
  }
};

// Function to navigate to the write page
const goToWritePage = () => {
  router.push({ name: "postwrite" });
};
</script>

<style scoped>
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
