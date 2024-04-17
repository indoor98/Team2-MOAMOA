<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from "axios";

const loadComments = async () => {
  try {
    // 게시글 정보 불러오기
    const postId = window.location.pathname.split('/').pop();

    // API 요청 보내기
    const response = await axios.get()


  } catch (error) {
    console.log(error);
  }
}

</script>

<template>
<button>
  드가자
</button>
</template>

<style scoped>

</style>