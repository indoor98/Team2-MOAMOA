<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from "axios";

const post = ref('');

const accessToken = localStorage.getItem("accessToken");
const isJoined = ref(-1);
const router = useRouter();



// const isAttend = () => {
//   try {
//     const postId = window.location.pathname.split('/').pop();
//     const response = axios.get(`http://localhost:8080/api/post/post/attend/${postId}`,{
//       headers: {
//         'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
//       }})
//         .then( (value) => {
//           isJoined.value = value.data;
//           console.log(isJoined.value);
//         });
//
//   } catch (error) {
//     console.log(error);
//   }
// }
// isAttend();

const loadPost = async () => {
  try {
    // 게시글 정보 불러오기
    const postId = window.location.pathname.split('/').pop();
    // API 요청 보내기
    const response = await fetch(`http://localhost:8080/api/post/${postId}`,{
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }});
    const data = await response.json();
    post.value = data;
    console.log(post.value);

  } catch (error) {
    console.log(error);
  }
}

onMounted(loadPost);

</script>

<template>

  <div>
    제목 : {{ post.title }}
  </div>
  <span v-for="(hashtag, index) in post.hashtagList" :key="index" >
    #{{ hashtag.hashtag }} <space>  </space>
  </span>
  <div>
    <a :href="post.productUrl" target="_blank">
      <img :src= "post.metaImage" class="image-preview">
      <br>
      <span>{{post.metaTitle}}</span>
    </a>

  </div>

  
</template>

<style scoped>
.image-preview {
  max-width: 500px; /* 최대 너비 설정 */
  max-height: 500px; /* 최대 높이 설정 */
  width: auto; /* 원본 이미지 비율 유지 */
  height: auto; /* 원본 이미지 비율 유지 */
  display: block; /* 줄바꿈 방지 */
  object-fit: cover; /* 컨테이너에 맞춰 이미지를 채우되, 비율 유지 */
}

</style>