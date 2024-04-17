<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from "axios";

// accessToken 담기
const accessToken = localStorage.getItem("accessToken");
// 댓글 목록을 담을 ref
const comments = ref([]);
// 에러 메시지를 나타낼 ref
const error = ref(null);
// 새로운 댓글을 담을 ref
const newComment = ref('');
// 현재 유저의 게시글 참여 유형을 담을 ref : 0 -> 참여 x  1 -> 게시글 작성자 2 -> 작성자는 아니지만 참여 중
const userType = ref(-1);

const isAttend = () => {
  try {
    const postId = window.location.pathname.split('/').pop();
    const response = axios.get(`http://localhost:8080/api/post/post/attend/${postId}`,{
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }})
        .then( (value) => {
          userType.value = value.data;
          console.log(userType.value);
        });

  } catch (error) {
    console.log(error);
  }
}
isAttend();


// 댓글을 불러오는 함수
const loadComments = async () => {
  try {
    // 게시글의 postid 가져오기
    const postId = window.location.pathname.split('/').pop();
    // API 요청 보내고 댓글 목록 받아오기
    const response = await fetch(`http://localhost:8080/api/comment/${postId}/list`, {
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }
    });
    const data = await response.json();
    // 받아온 댓글 목록을 ref에 저장
    console.log(data);
    comments.value = data;
  } catch (error) {
    // 에러가 발생하면 에러 ref 업데이트
    error.value = error.message || '댓글을 불러오는 중 에러가 발생했습니다.';
  }
};

onMounted(loadComments);

</script>

<template>
  <div>
    <!-- 댓글 목록 표시 -->
    <div v-if="comments.length > 0">
      <h2>댓글 목록</h2>
      <ul>
        <li v-for="(comment, index) in comments" :key="index">
          {{ comment.comment }}
          {{ comment.nickname }}
          {{ comment.createDate.slice(0, 10) + ' ' + comment.createDate.slice(11, 16)}}
        </li>
      </ul>
    </div>

    <!-- 댓글이 없을 때 표시 -->
    <div v-else>
      <p>댓글이 없습니다.</p>
    </div>
  </div>

  <div class="bottom-column">
    <div v-if="userType >= 1">
    <div class="comment-section">
      <textarea v-model="newComment" placeholder="댓글"></textarea>
      <button class="comment-button" @click="submitComment">등록</button>
    </div>
    </div>
    <div v-else>
    </div>
  </div>
</template>

<style scoped>

</style>