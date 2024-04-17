<template>
  <div class="container" v-if="!loading">
    <NavHeader></NavHeader>
    <div class="main-content">
      <PostLeft
          :title="post.title"
          :hashtags="post.hashtags"
          :receive-place="post.receivePlace"
          :postImg="post.postImg">
      </PostLeft>
      <PostRight
          :deadline="post.deadline"
          :headCount="post.headCount"
          :joined-users-count="post.joinedUsersCount"
          :price="post.price"
          :postJoin="post.id.toString()"
          :isUserJoined="post.isUserJoined"
          @joinedOrCanceled="fetchPostData">
      </PostRight>
    </div>
    <PostComment></PostComment>
  </div>
  <div v-else>
    Loading...
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
import NavHeader from '../components/NavHeader.vue';
import PostLeft from '../components/postdetailpage/PostLeft.vue';
import PostRight from '../components/postdetailpage/PostRight.vue';
import PostComment from '../components/postdetailpage/PostComment.vue';

const post = ref({});
const loading = ref(true);
const isUserJoined = ref(false); // 참여 상태 기본값 설정
const route = useRoute();

const fetchPostData = async () => {
  const postId = route.params.postno;
  const accessToken = localStorage.getItem('accessToken');
  const headers = accessToken ? { Authorization: `Bearer ${accessToken}` } : {}; // 토큰이 있다면 헤더에 추가
  try {
    const response = await axios.get(`http://localhost:8080/api/post/${postId}`, { headers });
    post.value = response.data;
    isUserJoined.value = response.data.isUserJoined;
    loading.value = false;
  } catch (error) {
    console.error("There was an error fetching the post: ", error);
    loading.value = false;
  }
};

onMounted(fetchPostData);
</script>

<style>


body {
  font-family: 'Helvetica Neue', Arial, sans-serif;
  color: #333;
}

.main-content {
  display: flex;
  gap: 16px;
  border: 1px solid black;
  border-radius: 4px;
  overflow: hidden;
}

.container {
  display: flex;
  flex-direction: column;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}


body {
  background-color: #f4f4f4;
}


a {
  color: #007BFF;
  text-decoration: none;
}

button {
  background-color: #007BFF;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}
</style>