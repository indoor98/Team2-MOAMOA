<template>
  <div class="body">
    <div class="header">
      <img src="@/assets/moamoa_logo_org.png" alt="로고 이미지" class="logo">
    </div>

    <div class="list-container">
      <div class="list-header">
        <div>작성자명</div>
        <div>학교명</div>
        <div>이미지</div>
        <div>승인</div>
      </div>
      <div v-if="str" class="list-item"></div>
      <div class="list-item" v-for="item in str" :key="item.id" v-show="!item.auth_yn">
        <div class="author-name">{{ item.nickname }}</div>
        <div class="school">{{ item.school }}</div>
        <div class="img">
          <img :src="getFileName(item.photoUrl)" alt="이미지">
        </div>
        <div class="approve-button">
          <button @click="approveRequest(item.id)">승인</button>
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

  methods: {
    // 파일 경로에서 파일 이름만 추출하는 메서드
    getFileName(filePath) {
      if (!filePath) return '';
      const fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
      console.log(fileName);
      const filename = `/auth-images/${fileName}`;
      return filename;
    }
  },
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
      const accessToken = localStorage.getItem('accessToken');
      axios.put(
          `http://localhost:8080/api/admin/authlist/${id}`,
          null,
          {
            params: {
              approve: true
            },
            headers: {
              Authorization: `Bearer ${accessToken}`
            }
          }
      )
          .then((res) => {
            // 요청이 승인되었을 때의 로직 추가
            console.log("승인 완료");
            str.value.find(item => item.id === id).auth_yn = true;
          })
          .catch((err) => {
            console.log("에러 발생");
            console.error(err);
          });
    };

    // 승인되지 않은 아이템 필터링
    const filteredItems = ref([]);
    const filterItems = () => {
      if (str.value) {
        filteredItems.value = str.value.filter(item => !item.auth_yn);
      }
    };

    const mounted = () => {
      filterItems();
    };



    return {str, approveRequest, mounted, filteredItems}; // 컴포넌트 템플릿에서 사용할 변수 및 메서드 반환
  }
};
</script>

<style scoped>
.body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

.logo {
  width: 200px; /* 로고 이미지의 너비를 조절합니다. */
  height: auto; /* 높이 자동 조정 */
}

.header {
  background-color: #f7efe4;
  display: flex;
  justify-content: center; /* 수평 가운데 정렬 */
  align-items: center; /* 수직 가운데 정렬 */
}

.list-container {
  margin-top: 20px;
  padding: 0 10%;
}

.list-header {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  padding: 10px;
  border-bottom: 2px solid #019b63;
  font-weight: bold;
}

.list-header div, .list-item div {
  text-align: center;
  padding: 10px;
}

.list-item {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.list-item img {
  width: 50px;
  height: 50px;
  justify-self: center; /* 이미지를 가운데 정렬 */
}

.list-item button {
  width: 50px;
  height: 30px;
  justify-self: center; /* 버튼을 가운데 정렬 */
  margin-top: 10px; /* 버튼 위 간격 추가 */
  background-color: #fdbcbc; /* 버튼의 배경색을 지정 */
  color: white; /* 텍스트 색상을 흰색으로 지정 */
  border: none; /* 테두리 제거 */
}
</style>