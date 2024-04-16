<template>
  <div class="body">
    <div class="header">
      <div class="logo">MOA<br/>MOA</div>
      <div class="search-box-container">
        <input type="text" class="search-box" placeholder="검색">
        <div class="search-icon">&#128269;</div>
      </div>
    </div>

    <div class="list-container">
      <div class="list-header">
        <div>작성자명</div>
        <div>학교명</div>
        <div>이미지</div>
      </div>
      <div v-if="str" class="list-item"></div>
      <div class="list-item" v-for="item in str" :key="item.id">
        <div class="author-name">{{ item.nickname }}</div>
        <div class="school">{{ item.school }}</div>
        <div class="img">
          <img :src="item.photoUrl" alt="이미지">
          <button @click="approveRequest(item.id)">승인</button>
          <button @click="rejectRequest(item.id)">거절</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  name: 'About',
  setup() {
    const str = ref(null); // 데이터를 저장할 변수

    const accessToken = localStorage.getItem('accessToken');
    console.log(accessToken);
    // axios로 Spring Boot에 GET request를 보냄
    axios
        .get("http://localhost:8080/api/admin/authlist", {
          headers: {
            Authorization: `Bearer ${accessToken}`
          }
        })
        .then((res) => {
          // 토큰 값을 넣어서 받아온 데이터 처리
          str.value = res.data; // 데이터를 변수에 할당
          console.log(str.value);
        })
        .catch((err) => {
          console.error(err);
        });

    const approveRequest = (id) => {
      console.log(id);
      const accessToken = localStorage.getItem('accessToken');
      console.log(accessToken);
      const requestData = {
        authNo: id,
        approve: 1
      };

      axios.put(`http://localhost:8080/api/admin/authlist/${id}`,
          requestData, {
            headers: {
              Authorization: `Bearer ${accessToken}`
            }
          }
      )
          .then((res) => {
            // 요청이 승인되었을 때의 로직 추가
            console.log("승인 완료");
            console.log(requestData.authNo);
            console.log(requestData.approve);
          })
          .catch((err) => {
            console.log("에러 발생");
            console.error(err);
          });
    };

    const rejectRequest = (id) => {
      const accessToken = localStorage.getItem('accessToken');
      console.log(accessToken);
      axios.put(`http://localhost:8080/api/admin/authlist/${id}`, null, {
            headers: {
              Authorization: `Bearer ${accessToken}`
            }
          }
      )
          .then((res) => {
            // 요청이 거절되었을 때의 로직 추가
            console.log("승인 거절");
          })
          .catch((err) => {
            console.error(err);
          });
    };

    return { str, approveRequest, rejectRequest }; // 컴포넌트 템플릿에서 사용할 변수 및 메서드 반환
  }
};
</script>

<style scoped>
.body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}
.header {
  background-color: #498C74;
  padding: 20px 10%;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
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
.list-item button {
  margin-left: 10px;
}
</style>
