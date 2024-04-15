<template>
    <header>
      <NavHeader />
    </header>

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
          :price="post.price">
      </PostRight>
    <PostComment></PostComment>
  </div>
</template>

<script>
import NavHeader from '../components/NavHeader.vue'
import PostLeft from '../components/postdetailpage/PostLeft.vue'
import PostRight from '../components/postdetailpage/PostRight.vue'
import PostComment from '../components/postdetailpage/PostComment.vue'
import axios from 'axios'

export default {
  name: 'PostDetailView',
  components: {
    NavHeader,
    PostLeft,
    PostRight,
    PostComment
  },
  data() {
    return {
      post: {},
    }
  },
  mounted() {
    this.fetchPost();
  },
  methods: {
    fetchPost() {
      const postId = this.$route.params.postno;
      const accessToken = localStorage.getItem('accessToken');
      axios.get(`http://localhost:8080/api/post/${postId}`, {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      })
          .then(response => {
            console.log('API Response:', response.data);  // API 응답 로그 출력
            this.post = response.data;
          })
          .catch(error => {
            console.error("There was an error fetching the post: ", error);
          });
    }
  }
}
</script>

<style>
header {
  background-color: #498C74;
  color: white;
}

body {
  font-family: 'Helvetica Neue', Arial, sans-serif;
  color: #333; /* 기본 텍스트 색상 */
}

/* 전체 페이지 컨테이너 스타일 */
.container {
  max-width: 1200px;
  margin: 0 auto; /* 중앙 정렬 */
  padding: 20px;
  box-shadow: 0 0 10px rgba(0,0,0,0.1); /* 상자 그림자 효과 */
}

/* 배경색 설정 */
body {
  background-color: #f4f4f4; /* 밝은 회색 배경 */
}

/* 링크 스타일 */
a {
  color: #007BFF; /* 링크 색상 */
  text-decoration: none; /* 밑줄 제거 */
}

/* 버튼 기본 스타일 */
button {
  background-color: #007BFF; /* 버튼 색상 */
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3; /* 호버 시 버튼 색상 변경 */
}
</style>