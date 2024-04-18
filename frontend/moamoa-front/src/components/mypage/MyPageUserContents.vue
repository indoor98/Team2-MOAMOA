<script setup>
import axios from "axios";
import {ref} from "vue";

const writeList = ref('');
const joinList = ref('');

const getWriteList = () => {
  try {
    const accessToken = localStorage.getItem('accessToken'); // localStorage에서 accessToken 가져오기
    axios.get("http://localhost:8080/api/mypage/postlist", {
      headers: {
        Authorization: `Bearer ${accessToken}` // 헤더에 accessToken 추가
      }
    }).then(response => {
      writeList.value = response.data;
      console.log(response.data); // 서버로부터 받은 데이터를 콘솔에 출력
    }).catch(error => {
      console.error(error); // 오류 발생 시 콘솔에 오류 메시지 출력
    });
  } catch (error) {
    console.error(error);
  }
};
const getJoinList = () => {
  try {
    const accessToken = localStorage.getItem('accessToken'); // localStorage에서 accessToken 가져오기
    axios.get("http://localhost:8080/api/mypage/joinlist", {
      headers: {
        Authorization: `Bearer ${accessToken}` // 헤더에 accessToken 추가
      }
    }).then(response => {
      joinList.value = response.data;
      console.log(response.data); // 서버로부터 받은 데이터를 콘솔에 출력
    }).catch(error => {
      console.error(error); // 오류 발생 시 콘솔에 오류 메시지 출력
    });
  } catch (error) {
    console.error(error);
  }
};

getJoinList();
getWriteList();
console.log(writeList.value);
</script>
<template>
  <div class="right-content">
    <div class="right-content-div">
      <router-link to="/joinpost" class="section-title">참여중인 공동구매</router-link>
      <div class="post-list" v-if="joinList.length > 0">
        <div class="card" v-for="(post, index) in joinList.slice(0, 5)" :key="post.postId">
          <div class="card-body">
            <router-link :to="`/post/${post.postId}`" class="card-title" >{{ post.title }}</router-link>
          </div>
        </div>
      </div>
      <div v-else class="no-content-msg">
        참여 중인 공동구매가 없습니다.
      </div>
    </div>
    <div class="right-content-div">
      <router-link to="/mypost" class="section-title">작성한 게시글 목록</router-link>
      <div class="post-list" v-if="writeList.length > 0">
        <div class="card" v-for="(post, index) in writeList.slice(0, 5)" :key="post.postId">
          <div class="card-body">
            <router-link :to="`/post/${post.postId}`" class="card-title" >{{ post.title }}</router-link>
<!--            <a class="card-title" href="/post/{{post.id}}">{{ post.title }}</a>-->
          </div>
        </div>
      </div>
      <div v-else class="no-content-msg">
        작성한 게시글이 없습니다.
      </div>
    </div>
  </div>
</template>

<style scoped>

.right-content-div {
  border: solid 1px #dee2e6;
  margin: 20px;
  padding: 20px;
  border-radius: 10px;
  background-color: #f7efe4;
}

.section-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #343a40;
  text-decoration: none;
}

.post-list {
  margin-top: 10px;
}

.card {
  width: 100%;
  border: 1px solid #ced4da;
  border-radius: 5px;
  margin-bottom: 10px;
  background-color: #fff;
}

.card-body {
  display: flex;
  font-size: 18px;
  justify-content: center;
  align-items: center;
  padding: 10px;
}

.card-title {
  text-align: center;
  margin: 0;
  color: #212529;
}

.no-content-msg {
  text-align: center;
  font-style: italic;
  color: #6c757d;
}
</style>
