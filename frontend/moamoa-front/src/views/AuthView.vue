<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const image = ref('')

const readInputFile = (e) => {
  const file = e.target.files[0]
  // if (!file.type.match("image/.*")) {
  //   alert("이미지 확장자만 업로드 가능합니다.")
  //   return
  // }

  const reader = new FileReader()
  // reader.onload = (e) => {
  //   image.value = e.target.result; // 데이터 URL을 image 변수에 저장합니다.
  //   //console.log(image.value); // 데이터 URL을 확인합니다.
  // };
  reader.readAsDataURL(file); // 파일을 읽고 데이터 URL을 생성합니다.
  return new Promise((resolve, reject) => {
    reader.onloadend = () => {
      resolve(reader.result);
    };
    reader.onerror = reject;
    reader.readAsDataURL(file);
  });
}

// 작동 오류 -> post 오류
const handleSubmit = async () => {
  try {
    const formData = new FormData()
    const base64String = await readInputFile(image);
    formData.append(
        'image', base64String
    );
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
    <div>
      <h2 style="color: white; font-weight: bold; text-align: center;">MOA<br/>MOA</h2>
      <div class="authup-container">
        <h2>Auth</h2>
        <form class="auth-form" @submit.prevent="handleSubmit">
          <input type="file" @change="readInputFile"/>
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