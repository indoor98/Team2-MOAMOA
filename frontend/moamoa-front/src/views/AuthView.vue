// 컴포지션 api 사용
<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const image = ref('')

//작동 중
const readInputFile = (e) => {
  const file = e.target.files[0]
  if (!file.type.match("image/.*")) {
    alert("이미지 확장자만 업로드 가능합니다.")
    return
  }

  const reader = new FileReader()
  reader.onload = (e) => {
    image.value = e.target.result; // 데이터 URL을 image 변수에 저장합니다.
    console.log(image.value); // 데이터 URL을 확인합니다.
  };
  reader.readAsDataURL(file); // 파일을 읽고 데이터 URL을 생성합니다.
}

// 작동 오류 -> post 오류
const handleSubmit = async () => {
  try {
    const formData = new FormData()
    formData.append('image', image.value)

    const response = await axios.post("/api/auth/school_auth", formData)
    console.log(response.data)
    localStorage.setItem('accessToken', response.data.accessToken)
    localStorage.setItem('refreshToken', response.data.refreshToken)
    router.push({ name: "auth" })
  } catch (error) {
    console.error(error)
  }
}
</script>


<template>
  <div class="body">
    <div>
      <h2 style="color: white; font-weight: bold; text-align: center;">MOA<br/>MOA</h2>
      <div class="authup-container">
        <h2>Auth</h2>
        <form class="auth-form" @submit.prevent="handleSubmit">
          <input type="file" @change="readInputFile" ref="imageUpload"/>
          <div id="imagePreview">
            <img v-if="image" :src="image" />
          </div>
          <br>
          <button type="submit">제출</button>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
  background-color: #f2f2f2;
}
.authup-container {
  text-align: center;
  width: 300px;
  padding: 20px;
  border-radius: 10px; /* 모서리 둥근 사각형 */
  background-color: #D9D9D9;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}
.authup-container input {
  width: calc(100% - 16px);
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 5px; /* 입력란의 모서리 둥글게 */
  border: 1px solid #ccc;
}
.authup-container button {
  width: 100%;
  padding: 8px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.auth-form{
  background-color: #D9D9D9;
}
.body{
  background-color: #498C74;
}
#imagePreview{
  width : 100%;
  height: 110px;
  background-color : #EFF3FD;
  border : 1px solid skyblue;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;

}



</style>