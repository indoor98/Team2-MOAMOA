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
console.log(decodedPayload.sub.split('@').at(0));

const userEmail = ref('');
userEmail.value = decodedPayload.sub.split('@').at(0);


</script>

<template>
  <div class="left-content">
    <div>
      <img src="/userprofile/userdefault.jpg">
    </div>
    <div>
      {{ userEmail }}
    </div>
    <div>
      <button>
        회원 탈퇴
      </button>
    </div >
    <div>
      <button>
        회원 정보 수정
      </button>
    </div>
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
