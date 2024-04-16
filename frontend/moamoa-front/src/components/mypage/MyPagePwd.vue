<template>
  <div class="password-form">
    <h2>비밀번호 변경</h2>
    <form @submit.prevent="handleChangePassword">
      <div class="form-group">
        <label for="currentPassword">현재 비밀번호</label>
        <input type="password" id="currentPassword" v-model="currentPassword" required>
      </div>
      <div class="form-group">
        <label for="newPassword">새 비밀번호</label>
        <input type="password" id="newPassword" v-model="newPassword" required>
      </div>
      <div class="form-group">
        <label for="confirmPassword">새 비밀번호 확인</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required>
      </div>
      <button type="submit">비밀번호 변경</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router'; // 추가

const currentPassword = ref('');
const newPassword = ref('');
const confirmPassword = ref('');

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
const handleChangePassword = async () => {
  // 입력한 새 비밀번호와 확인용 비밀번호가 일치하는지 확인합니다.
  if (newPassword.value !== confirmPassword.value) {
    alert('새 비밀번호와 확인용 비밀번호가 일치하지 않습니다.');
    return;
  }

  const requestData = {
    previousPwd: currentPassword.value,
    newPwd: newPassword.value,
    newPwdCheck: confirmPassword.value
  };
  const accessToken = localStorage.getItem('accessToken');

  try {
    const response = await axios.post(
        'http://localhost:8080/api/mypage/password',
        {
          previousPwd: currentPassword.value,
          newPwd: newPassword.value,
          newPwdCheck: confirmPassword.value
        },
        {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }});

    console.log(response.data);
    alert('비밀번호가 성공적으로 변경되었습니다.');
    // 비밀번호 변경 후 필요한 작업을 수행하세요.

    await logout();
  } catch (error) {
    console.error(error);
    alert('비밀번호 변경에 실패했습니다 올바른 현재 비밀번호를 입력해주세요.');
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
