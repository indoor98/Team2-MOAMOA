<script setup>
import NavHeader from "@/components/NavHeader.vue";
import axios from "axios";
import {ref} from "vue";
import {defineComponent} from "vue";

const joinList = ref([]);  // 빈 배열로 초기화

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
</script>



<template>
  <header>
    <NavHeader />
  </header>

  <div class="list-container">
    <div class="list-header">
      <div class="header-item">번호</div>
      <div class="header-item">게시글 제목</div>
      <div class="header-item">모집 인원</div>
      <div class="header-item">마감일자</div>
    </div>

    <div class="list-item" v-for="(item, index) in joinList" :key="index">
      <div class="item-content number">{{ index + 1 }}</div>
      <div class="item-content title-block">
        <router-link :to="`/post/${item.postId}`" class="title-link">
          <div>{{ item.title }}</div>
        </router-link>
        <a :href="item.productUrl" target="_blank" class="image-link">
          <img :src="item.metaImage" alt="미리보기 이미지" class="preview-image">
        </a>
      </div>
      <div class="item-content head-count">{{ item.headCount }}</div>
      <div class="item-content deadline">{{ item.deadLine }}</div>
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
.preview-image {
  width: 100%; /* 이미지 너비를 컨테이너에 맞춤 */
  max-width: 150px; /* 최대 너비 제한 */
  height: auto; /* 높이는 자동으로 설정하여 비율 유지 */
  object-fit: cover; /* 컨테이너를 채우면서 비율 유지 */
  border-radius: 8px; /* 이미지에 둥근 모서리 추가 (선택적) */
}
</style>