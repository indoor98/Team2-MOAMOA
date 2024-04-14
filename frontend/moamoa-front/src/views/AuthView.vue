// 컴포지션 api 사용
<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import $ from 'jquery';


const router = useRouter()

const readInputFile = (e) => {

  // 미리보기 기능구현
  $('#imagePreview').empty();
  var files = e.target.files;
  var fileArr = Array.prototype.slice.call(files);
  console.log(fileArr);
  fileArr.forEach(function(f){
    if(!f.type.match("image/.*")){
      alert("이미지 확장자만 업로드 가능합니다.");
      return;
    };

    var reader = new FileReader();
    reader.onload = function(e){
      var html = `<img src=${e.target.result} />`;
      $('#imagePreview').append(html);
    };
    reader.readAsDataURL(f);
  })

}

const handleSubmit = async () => {
  try {
    const formData = new FormData();
    formData.append('image', file); // 'file'는 이미지 파일 객체입니다.

    const response = await axios.post("http://localhost:8080/api/auth/school_auth", {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    console.log(response.data)
    accessToken.value = response.data.accessToken
    refreshToken.value = response.data.refreshToken

    localStorage.setItem('accessToken', accessToken.value)
    localStorage.setItem('refreshToken', refreshToken.value)

    router.push({ name: "home" })
  } catch (error) {
    console.error(error)
  }
}
</script>


<template>
  <div class = "body">
    <div>
      <h2 style = "color: white; font-weight: bold; text-align: center;">MOA<br/>MOA</h2>
      <div class="authup-container">
        <h2>Auth</h2>
        <form action="#" class = "auth-form">
          <input type = "file" @change = "readInputFile" ref = "imageUpload"/>
          <div id = "image">
            <img id = "img" />
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
#image{
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