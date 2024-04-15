<template>
  <div class="container">
    <!-- 이미지 삽입 -->
    <img src="@/assets/moamoa_logo.png" alt="로고 이미지" class="logo">
    <div class="signup-container">
      <form class="signup-form" @submit.prevent="signUp">
        <img src="@/assets/moamoa_logo_txt.png" alt="로고 글자" class="logo_txt">
        <input type="text" v-model="nickname" placeholder="닉네임">
        <br>
        <input type="email" v-model="email" placeholder="이메일">
        <br>
        <input type="password" v-model="password" placeholder="비밀번호">
        <br>
        <input type="tel" v-model="phone" placeholder="전화번호">
        <br>
        <input type="text" v-model="school" placeholder="학교">
        <br>
        <input type="text" v-model="address" placeholder="기숙사 위치">
        <br>
        <button type="submit">가입하기</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      nickname: '',
      email: '',
      password: '',
      address: '',
      school: '',
      phone: '',
    }
  },
  methods: {
    async signUp() {
      try {
        const response = await axios.post("http://localhost:8080/api/auth/signup", {
          nickname: this.nickname,
          email: this.email,
          password: this.password,
          address: this.address,
          school: this.school,
          phone: this.phone,
        });
        // POST 요청에 대한 응답 처리
        console.log(response.data);

        // 회원가입 성공 시 로그인 페이지로 이동
        this.$router.push({
          name: "login",
        });

      } catch (error) {
        // 요청 실패 처리
        console.error(error);
      }
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column; /* 이미지 및 폼을 세로로 정렬하기 위해 추가 */
  height: 100vh; /* 화면 전체 높이 */
}

.signup-container {
  text-align: center;
  width: 350px; /* 폼의 너비를 조정합니다. */
  padding: 20px;
  border-radius: 10px; /* 모서리 둥근 사각형 */
  background-color: #ffd557;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  margin-top: 20px; /* 이미지와 간격 조정 */
}
.signup-container input {
  width: calc(100% - 16px);
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 5px; /* 입력란의 모서리 둥글게 */
  border: 1px solid #ccc;
}
.signup-container button {
  width: 100%;
  padding: 8px 20px;
  background-color: #019b63;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.signup-form{
  background-color: #ffd557;
}

/* 로고 이미지 스타일 */
.logo {
  width: 250px; /* 로고 이미지의 너비를 조정합니다. */
  height: auto; /* 높이 자동 조정 */
}

/* 로고 텍스트 스타일 */
.logo_txt {
  width: 200px; /* 로고 텍스트의 너비를 조정합니다. */
  height: auto; /* 높이 자동 조정 */
}
</style>
