<template>
  <div class="col-md-4">
    <div class="card">
      <!-- 이미지 삽입 -->
      <img :src="post.productUrl" class="card-img-top" alt="...">
      <div class="card-body">
        <!-- 제목, 가격, 인원수, 마감일 출력 -->
        <h5 class="card-title">{{ post.title }}</h5>
        <p class="card-text">가격: {{ post.price }}</p>
        <p class="card-text">인원수: {{ post.headCount }}</p>
        <p class="card-text">마감일: {{ post.deadLine }}</p>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    post: {
      type: Object,
      required: true
    }
  }
};
</script>

<style scoped>
.card-img-top {
  border-radius: 50%;
  background: white;
  width: 150px;
  height: 150px;
  padding: 10px;
  object-fit: cover;
  border: 5px solid white;
}
</style>
