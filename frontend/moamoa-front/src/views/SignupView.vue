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

<template>
  <div>
    <h2 style = "color: white; font-weight: bold; text-align: center;">MOA<br/>MOA</h2>
    <div class="signup-container">
      <h2>Sign In</h2>
      <form class = "signup-form" @submit.prevent="signUp">
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
        <button type="submit">제출</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
.signup-container {
  text-align: center;
  width: 300px;
  padding: 20px;
  border-radius: 10px; /* 모서리 둥근 사각형 */
  background-color: #D9D9D9;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
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
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.signup-form{
  background-color: #D9D9D9;
}
body{
  background-color: #498C74;
}
button{
  background-color: white;
}
</style>