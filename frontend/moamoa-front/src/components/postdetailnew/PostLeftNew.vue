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
  <div class="container">
    <div class="title">제목 : {{ post.title }}</div>
    <div>
      <span class="hashtag" v-for="(hashtag, index) in post.hashtagList" :key="index">
        #{{ hashtag.hashtag }}
      </span>
    </div>
    <a :href="post.productUrl" target="_blank" class="product-link">
      <img :src="post.metaImage" class="image-preview">
      <span class="product-title">{{ post.metaTitle }}</span>
    </a>
  </div>
</template>

<style scoped>


.container {
  text-align: center; /* Center everything inside the container */
}

.title {
  font-size: 2em; /* Larger text for the title */
  margin-bottom: 0.5em; /* Space below the title */
  font-weight: bold; /* Make the title text bold */
}

.hashtag {
  font-size: 1.3em; /* Slightly larger text for hashtags */
  color: #333; /* Dark grey color for the hashtags */
  padding: 0.25em; /* Spacing inside the hashtag elements */
  margin: 0.25em; /* Space between hashtag elements */
}

.image-preview {
  max-width: 500px; /* 최대 너비 설정 */
  max-height: 500px; /* 최대 높이 설정 */
  width: auto; /* 원본 이미지 비율 유지 */
  height: auto; /* 원본 이미지 비율 유지 */
  display: block; /* 줄바꿈 방지 */
  object-fit: cover; /* 컨테이너에 맞춰 이미지를 채우되, 비율 유지 */
  margin: 0 auto; /* 이미지를 중앙으로 배치 */
  border: 10px solid #f4f4f4; /* 빨간색 테두리 */
}

.product-link {
  display: inline-block; /* Align the link inline with other elements */
  text-decoration: none; /* Removes the underline from the link */
  color: black; /* Sets the link color to black */
  margin-top: 1em; /* Space above the product link */
}

.product-title {
  display: block; /* Display the title in a block to force a new line */
  font-size: 1.2em; /* Slightly larger text for the product title */
  margin-top: 0.25em; /* Space above the product title */
}

</style>