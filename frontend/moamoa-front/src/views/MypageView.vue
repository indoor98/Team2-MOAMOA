<script setup>
import NavHeader from "@/components/NavHeader.vue";
</script>

<template>
  <header>
    <NavHeader />
  </header>

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
</style>