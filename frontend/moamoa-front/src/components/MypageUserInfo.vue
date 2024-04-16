<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from "axios";

const router = useRouter()

const accessToken = localStorage.getItem('accessToken')
// JWT 토큰을 점으로 구분된 세 부분으로 분리합니다.
const payload = accessToken.split('.').at(1);
// base64로 인코딩된 payload를 디코딩합니다.
const decodedPayload = JSON.parse(atob(payload));

const bytes = Uint8Array.from(decodedPayload.nickname, c => c.charCodeAt(0));
const decoder = new TextDecoder('utf-8');

const nickname = ref('');

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

const toSchoolAuth = () => {
  router.push({name: 'auth'})
}

const withdrawMember = () => {
  // 사용자로부터 비밀번호를 입력받음
  var password = prompt("회원 탈퇴를 위해 비밀번호를 입력하세요:");
  const accessToken = localStorage.getItem('accessToken');
  // 사용자가 취소 버튼을 누르거나 아무것도 입력하지 않은 경우
  if (password === null || password === "") {
    alert("비밀번호를 입력해야 합니다.");
  } else {
    // 비밀번호를 서버로 전송하고 회원 탈퇴 작업 수행
    axios.post("http://localhost:8080/api/auth/withdrawal", { password }, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    })
        .then(response => {
          console.log(response.data);
          // 회원 탈퇴 성공 시 로그아웃 처리 등을 수행
          logout();
        })
        .catch(error => {
          console.error(error);
          alert("회원 탈퇴에 실패했습니다. 비밀번호를 확인해주세요.");
        });
  }
}


nickname.value = decoder.decode(bytes);
console.log(nickname.value);
</script>

<template>
  <div class="left-content">
    <div>
      <img src="/userprofile/userdefault.jpg">
    </div>
    <div>
      {{ nickname }}
    </div>
    <div>
      <button @click="withdrawMember">회원 탈퇴</button>
    </div>
    <div>
      <button>
        회원 정보 수정
      </button>
    </div>
    <template v-if="!isAuthencated">
      <button type="button" @click="toSchoolAuth">학교 인증이요ㅋ</button>
    </template>
  </div>
</template>
<style scoped>

.left-content img {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 20px; /* 이미지 아래 여백 조정 */
}

.left-content div {
  margin-bottom: 10px; /* 각 요소 사이의 간격 조정 */
}

</style>
