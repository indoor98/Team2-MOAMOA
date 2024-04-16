<template>
  <div class="col-md-4">
    <div class="card">
      <img :src="post.productUrl" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">{{ post.title }}</h5>
        <p class="card-text">{{ post.content }}</p>
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
