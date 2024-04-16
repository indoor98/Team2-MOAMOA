<template>
  <div class="container" v-if="!loading">
    <NavHeader></NavHeader>
    <div class="main-content">
      <PostLeft
          :title="post.title"
          :hashtags="post.hashtag"
          :receive-place="post.receivePlace"
          :postImg="post.postImg">
      </PostLeft>
      <PostRight
          :deadline="post.deadline"
          :headCount="post.headCount"
          :joined-users-count="post.joinedUsersCount"
          :price="post.price"
          :postJoin="post.id">
      </PostRight>
    </div>
    <PostComment></PostComment>
  </div>
  <div v-else>
    Loading...
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
      loading: true,
    }
  },
  mounted() {
    this.fetchPost()
  },
  methods: {
    async fetchPost() {
      ///////////
      try {
        const postId = this.$route.params.postno;
        const response = await axios.get(`http://localhost:8080/api/post/${postId}`);
        this.post = response.data;
        this.loading = false;
      } catch (error) {
        console.error("There was an error fetching the post: ", error);
        this.loading = false;
      }
      ///////////////
      // const postId = this.$route.params.postno;
      // const accessToken = localStorage.getItem('accessToken');
      // axios.get(`http://localhost:8080/api/post/${postId}`, {
      //   headers: {
      //     'Authorization': `Bearer ${accessToken}`
      //   }
      // })
      //     .then(response => {
      //       console.log('API Response:', response.data);
      //       this.post = response.data;
      //     })
      //     .catch(error => {
      //       console.error("There was an error fetching the post: ", error);
      //     });
    }
  }
}
</script>

<style>
header {
  background-color: #f7efe4;
  color: white;
}

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