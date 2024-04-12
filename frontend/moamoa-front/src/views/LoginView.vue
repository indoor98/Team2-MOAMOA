<script>
import axios from 'axios';
import { useRouter } from "vue-router";

// Vue Router의 인스턴스를 가져옴
const router = useRouter();

export default {
  data() {
    return {
      email: '',
      password: '',
      accessToken: '', // 엑세스 토큰을 저장할 변수
      refreshToken: '' // 리프레시 토큰을 저장할 변수
    };
  },
  methods: {
    async handleSubmit() {
      try {
        const response = await axios.post("http://localhost:8080/api/auth/signin", {
          email: this.email,
          password: this.password
        });
        // POST 요청에 대한 응답 처리
        console.log(response.data);
        // 응답에서 엑세스 토큰과 리프레시 토큰을 추출하여 변수에 저장
        this.accessToken = response.data.accessToken;
        this.refreshToken = response.data.refreshToken;

        // 저장된 토큰을 로컬 스토리지나 쿠키 등에 저장할 수도 있습니다.
        // 이 예시에서는 간단히 데이터 변수에 저장합니다.
        localStorage.setItem('accessToken', this.accessToken);
        localStorage.setItem('refreshToken', this.refreshToken);

        // 로그인 성공 시 홈 페이지로 이동
        this.$router.push({
          name: "home",
        });

      } catch (error) {
        // 요청 실패 처리
        console.error(error);
      }
    }
  }
};
</script>

<template>
  <div class="login-container">
    <h2 style = "color: white; font-weight: bold; padding-top:20px;">MOA<br/>MOA</h2>
    <form class = "login-form" @submit.prevent="handleSubmit">
      <input type="text" v-model="email" placeholder="아이디 입력">
      <br>
      <input type="password" v-model="password" placeholder="비밀번호 입력">
      <br>
      <button type="submit">로그인</button>
    </form>
  </div>
</template>

<style scoped>
.login-container {
  text-align: center;
  width: 100dvw;
  height: 100dvh;
  padding: 10px;
  background-color: #498C74; /* 로그인 입력 부분의 배경색 */
}

.login-container input {
  width: 30dvw; /* 입력란 너비 */
  margin-bottom: 10px;
  padding: 8px;
}
.login-container button {
  width: 10dvw; /* 버튼 너비 */
  padding: 8px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}
.login-form{
  padding-top: 20px;
  background-color: #498C74;
}
/*
.login-form button{
  background-color: gray;
  color : black;
}
*/

</style>