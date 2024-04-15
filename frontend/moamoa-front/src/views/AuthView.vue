<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
<<<<<<< Updated upstream
const image = ref('')

const readInputFile = (e) => {
  const file = e.target.files[0];
  const reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onloadend = () => {
    image.value = reader.result;
    return image.value;
  };
=======
const imageInput = ref('')

const readInputFile = async () => {
  const file = imageInput.value.files[0]
  const reader = new FileReader()
  reader.readAsDataURL(file)
  return new Promise((resolve, reject) => {
    reader.onloadend = () => {
      console.log(reader.result);
      resolve(reader.result);
    };
    reader.onerror = reject;
  });
>>>>>>> Stashed changes
}

const handleSubmit = async () => {
  try {
<<<<<<< Updated upstream
    const formData = new FormData();
    // 이미지 파일을 base64 문자열로 변환하여 FormData에 추가
    const base64String = await readInputFile();
    formData.append('image', base64String);

    const accessToken = localStorage.getItem('accessToken');
    const headers = {
      headers: {
        'Content-Type': 'multipart/form-data',
        'Authorization': `Bearer ${accessToken}`
      }
    };

    const response = await axios.post('http://localhost:8080/api/auth/school_auth', formData, headers);
    console.log(response.data);
    router.push({ name: 'auth' });
=======
    const formData = new FormData()
    const base64String = await readInputFile()
    formData.append('image', base64String);
    console.log("제출 후 " , formData.get('image'));
    const accessToken = localStorage.getItem('accessToken')
    console.log(accessToken);
    const headers = {
      headers: {
        'Content-Type': 'multipart/form-data',
        'Authorization': `Bearer ${accessToken}`
      }
    };
    const response = await axios.post('http://localhost:8080/api/auth/school_auth', formData, headers);

    console.log(response.data)
    router.push({ name: "auth" })
>>>>>>> Stashed changes
  } catch (error) {
    console.error(error);
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
<<<<<<< Updated upstream
          <input type="file" @change="readInputFile"/>
=======
          <input type="file" ref="imageInput" @change="readInputFile"/>
>>>>>>> Stashed changes
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
