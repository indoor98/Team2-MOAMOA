<script>
import NavHeader from "@/components/NavHeader.vue";
import {useRouter} from "vue-router";
const router = useRouter();
export default {
  components: {NavHeader},
  data() {
    return {
      sample:''
    }
  },
  methods: {
    async toLogin() {
      try {
        this.$router.push({
          name: "login",
        });
      } catch(error) {
        // 요청 실패 처리
        console.error(error);
      }
    },

    async toSignup() {
      try {
        this.$router.push({
          name: "signup",
        });
      } catch(error) {
        // 요청 실패 처리
        console.error(error);
      }
    }
  }
}

</script>

<template>
  <header>
    <NavHeader />
  </header>

  <!-- 게시글을 위한 부분 -->
  <div class="container mt-5">
    <div class="row g-4">
      <!-- 여기에 게시글 반복 시작 -->
      <div class="col-md-4">
        <div class="card">
          <img src="../img/coupang_logo.png" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">제목</h5>
            <p class="card-text">게시글의 내용 미리보기 추가</p>
          </div>
        </div>
      </div>
      <!-- 게시글 반복 끝 -->
      <!-- 반복 블록을 필요한 만큼 복사하여 추가 -->
    </div>
  </div>
</template>

<style scoped>

header {
  background-color: #498C74;
  color: white;
}
/* 게시글 스타일 조정을 위한 css 추가 */
.card-img-top {
  border-radius: 50%; /* 이미지를 원형으로.. */
  background: white; /* 배경 색상 */
  width: 150px; /* 이미지 크기 */
  height: 150px; /* 이미지 높이 */
  padding: 10px; /* 안쪽 여백 */
  object-fit: cover; /* 이미지가 태그를 꽉 채우도록 */
  border: 5px solid white; /* 흰색 테두리 */
}
</style>