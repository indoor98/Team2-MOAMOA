<script setup>
import NavHeader from "@/components/NavHeader.vue";
import axios from "axios";
import {ref} from "vue";
import {defineComponent} from "vue";

const writeList = ref([]);  // 빈 배열로 초기화

const getWriteList = () => {
  try {
    const accessToken = localStorage.getItem('accessToken'); // localStorage에서 accessToken 가져오기
    axios.get("http://localhost:8080/api/mypage/postlist", {
      headers: {
        Authorization: `Bearer ${accessToken}` // 헤더에 accessToken 추가
      }
    }).then(response => {
      writeList.value = response.data;
      console.log(writeList.value);
      // console.log(response.data); // 서버로부터 받은 데이터를 콘솔에 출력
    }).catch(error => {
      console.error(error); // 오류 발생 시 콘솔에 오류 메시지 출력
    });
  } catch (error) {
    console.error(error);
  }
};

getWriteList();
</script>



<template>
  <header>
    <NavHeader />
  </header>

  <div class="list-container">
    <div class="list-header">
      <div>번호</div>
      <div>게시글 제목</div>
      <div>모집 인원</div>
      <div>마감일자</div>
    </div>

    <div class="list-item" v-for="(item, index) in writeList" :key="index">
      <router-link :to="`/post/${item.postId}`">
        <div class="number">{{ index + 1 }}</div>
      </router-link>
      <a :href="item.productUrl" target="_blank" class="title">{{ item.title }}</a>
      <div class="head-count">{{ item.headCount }}</div>
      <div class="deadline">{{item.deadLine}}</div>

    </div>
  </div>

</template>

<style scoped>
header {
  background-color: #f7efe4;
  color: white;
}
.search-box-container {
  position: relative;
  flex-grow: 1;
  margin: 0 20px;
}
.search-box {
  width: 100%;
  height: 40px;
  padding: 10px 40px 10px 20px;
  box-sizing: border-box;
  border: none;
  border-radius: 20px;
}
.search-icon {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
}
.list-container {
  margin-top: 20px;
  padding: 0 10%;
}
.list-header, .list-item {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}
.list-header div, .list-item div, .list-item a {
  flex: 1; /* 모든 항목이 컨테이너의 공간을 균등하게 차지하도록 설정 */
  text-align: center;
}
.number, .head-count, .deadline {
  min-width: 50px; /* 최소 너비를 설정하여 내용에 따라 너비가 변하지 않도록 함 */
}
.title {
  flex: 3; /* 제목 부분을 다른 칸들보다 넓게 설정 */
}
</style>