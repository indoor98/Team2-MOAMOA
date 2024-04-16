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
      <div>이미지</div>
      <div>참여 인원</div>
    </div>
    <!-- 리스트 아이템 예시 -->
    <div class="list-item" v-for="(item, index) in writeList" :key="index">
      <div class="number">{{ index + 1 }}</div>
      <div class="title">{{ item.title }}</div>
      <div class="url">{{ item.productUrl }}</div> <!-- 수정 예정 -->
      <div class="head-count">{{ item.headCount }}</div>
    </div>

    <!-- 추가 리스트 아이템은 여기에 -->
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
.list-header {
  display: flex;
  padding: 10px;
  border-bottom: 2px solid #498C74;
  font-weight: bold;
}
.list-header div, .list-item div {
  flex: 1;
  text-align: center;

}
.list-item {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}
.list-item img {
  width: 50px;
  height: 50px;
}
</style>