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

</script>
<template>
  <div class="right-content">
    <div class="right-content-div">
      <router-link to="/joinpost">참여중인 공동구매(클릭 시 전체보기로 이동)</router-link>
      <!--      <a href="">참여중인 공동구매(클릭 시 전체보기로 이동)</a>-->
      <div v-if="joinList.length > 0">
        <div class="card" v-for="(post, index) in joinList.slice(0, 5)" :key="post.id">
          <div class="card-body">
            <a class="card-title" href="">{{ post.title }}</a>
          </div>
        </div>
      </div>
      <div v-else>
        참여 중인 공동구매가 없습니다.
      </div>
    </div>
    <div class="right-content-div">
      <router-link to="/mypost">작성한 게시글 목록(클릭 시 전체보기로 이동)</router-link>
      <!--      <a href="">작성한 게시글 목록(클릭 시 전체보기로 이동)</a>-->
      <div v-if="writeList.length > 0">
        <div class="card" v-for="(post, index) in writeList.slice(0, 5)" :key="post.id">
          <div class="card-body">
            <a class="card-title" href="">{{ post.title }}</a>
          </div>
        </div>
      </div>
      <div v-else>
        작성한 게시글이 없습니다.
      </div>
    </div>
  </div>
</template>

<style scoped>

.right-content-div {
  border: solid 1px;
  margin: 50px;
  height:30%;
}


.card {
  width: 100%;
  border: 1px solid #ced4da;
  margin: 0px;
}

.card-body {
  display: flex;
  font-size: 18px;
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
}

.card-title {
  text-align: center; /* 제목 가운데 정렬 */
  margin: 0; /* 제목 상하 여백 제거 */
}

a {
  text-decoration: none;
  color: inherit;
}
</style>