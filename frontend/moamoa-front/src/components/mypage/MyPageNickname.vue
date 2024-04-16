<template>
  <div class="password-form">
    <h2>닉네임 변경</h2>
    <form @submit.prevent="handleChangeNickname">
      <div class="form-group">
        <label for="newNickname">새로운 닉네임</label>
        <input type="text" id="newNickname" v-model="newNickname" required>
      </div>
      <button type="submit">닉네임 변경</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router'; // 추가

const newNickname = ref('');

const router = useRouter(); // 추가

const logout = async () => {
  const accessToken = localStorage.getItem('accessToken');
  console.log(accessToken);

  const response = await axios.get("http://localhost:8080/api/auth/logout", {
    headers: {
      Authorization: `Bearer ${accessToken}`
    }
  });

  console.log(response);
  // 로그아웃 후 로그인 페이지로 이동
  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  router.push({ name: 'login' })
}
const handleChangeNickname = async () => {
  // 입력한 새 비밀번호와 확인용 비밀번호가 일치하는지 확인합니다.

  const accessToken = localStorage.getItem('accessToken');

  try {
    const response = await axios.put(
        'http://localhost:8080/api/mypage/nickname',
        {
          nickname: newNickname.value,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`
          }});

    console.log(response.data);
    alert('닉네임 변경이 성공하였습니다.');
    // 비밀번호 변경 후 필요한 작업을 수행하세요.

  } catch (error) {
    console.error(error);
  }
};


</script>

<style scoped>
.password-form {
  text-align: center;
  margin-top: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
}

input {
  padding: 8px;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
