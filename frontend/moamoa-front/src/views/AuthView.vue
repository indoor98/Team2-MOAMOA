<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const image = ref('')

const readInputFile = (e) => {
  const file = e.target.files[0];
  const reader = new FileReader();

  reader.onload = () => {
    image.value = reader.result; // 데이터 URL을 image 변수에 저장합니다.
    console.log(image.value); // 데이터 URL을 확인합니다.
  };

  reader.readAsDataURL(file); // 파일을 읽고 데이터 URL을 생성합니다.
};

// 작동 오류 -> post 오류
const handleSubmit = async () => {
  try {
    const formData = new FormData()
    const fileInput = document.querySelector('input[type="file"]'); // 파일 입력(input) 요소를 선택합니다.
    formData.append('image', fileInput.files[0]) // 첫 번째 파일만 추가하도록 함
    // const imageUpload = ref('');
    // console.log(imageUpload);
    // const fileInput = imageUpload;    // ref를 이용해서 파일 입력(input)
    // formData.append('image', fileInput.files[0]) // 첫 번째 파일만 추가하도록 함
    // formData.append('image', image.value)
    // console.log(image.value);
    const accessToken = localStorage.getItem('accessToken')
    console.log(accessToken);
    const request = await axios.post('http://localhost:8080/api/auth/school_auth', formData, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });
    console.log(request.data)
    router.push({ name: "auth" })
  } catch (error) {
    console.error(error)
  }
}
</script>

<template>
  <div class="body">
    <div class="authup-container">
      <img src="@/assets/moamoa_logo_org.png" alt="로고 이미지" class="logo">
      <form class="auth-form" @submit.prevent="handleSubmit">
        <input type="file" @change="readInputFile"/>
        <div id="imagePreview" :style="{ backgroundImage: 'url(' + image + ')' }">
        </div>
        <br>
        <button type="submit">제출</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
.body {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column; /* 이미지 및 폼을 세로로 정렬하기 위해 추가 */
  height: 100vh; /* 화면 전체 높이 */
  //background-color: #f7efe4;
}
.authup-container {
  text-align: center;
  width: 350px; /* 폼의 너비를 조정합니다. */
  padding: 20px;
  border-radius: 10px; /* 모서리 둥근 사각형 */
  background-color: #f7efe4; /* 로그인 입력 부분의 배경색 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  margin-top: 20px; /* 이미지와 간격 조정 */
}
.authup-container input {
  width: calc(100% - 16px);
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 5px; /* 입력란의 모서리 둥글게 */
  border: 1px solid #f7efe4;

}
.authup-container button {
  width: 100%;
  padding: 8px 20px;
  background-color: #007BFF;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.auth-form{
  background-color: #ffd557;
  text-align: center;
  width: 350px; /* 폼의 너비를 조정합니다. */
  padding: 20px;
  border-radius: 10px; /* 모서리 둥근 사각형 */
  background-color: #ffd557; /* 로그인 입력 부분의 배경색 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  margin-top: 20px; /* 이미지와 간격 조정 */
}

#imagePreview{
  width : 100%;
  height: 110px;
  background-color : white;
  border : 1px solid #f7efe4;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  background-size: contain; /* 배경 이미지를 내용에 맞게 조정 */
  background-repeat: no-repeat; /* 배경 이미지 반복 제거 */
  background-position: center; /* 배경 이미지 가운데 정렬 */
}
.logo {
  width: 250px; /* 로고 이미지의 너비를 조정합니다. */
  height: auto; /* 높이 자동 조정 */
}
</style>