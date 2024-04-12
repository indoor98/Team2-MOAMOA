<script setup>
import { ref } from "vue";
import axios from "axios";

const str = ref("");

// axios로 Spring Boot에 GET request를 보냄
axios
    .get("/api/post/1")
    .then((res) => {
      console.log(res.data);
      console.log(res.data);
      str.value = res.data;
    })
    .catch((err) => {
      console.error(err);
    });
</script>

<template>
  <div class="about">
    <h1>{{ str }}</h1>
  </div>
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>