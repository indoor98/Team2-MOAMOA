<script setup>
import NavHeader from "@/components/NavHeader.vue";
import MypageUserInfo from "@/components/MypageUserInfo.vue";
import MyPageUserContents from "@/components/MyPageUserContents.vue";

</script>

<template>
  <header>
    <NavHeader />
  </header>
  <div class="container">
    <div class="content">
      <div class="left-content">
        <MypageUserInfo />
      </div>
      <div class="right-content">
        <MyPageUserContents />
      </div>

    </div>
  </div>

</template>

<style scoped>
header {
  background-color: #498C74;
  color: white;
}

.content {
  display: flex;
  width: 100%;
  flex-grow: 1; /* 부모 높이에 맞게 확장 */
  height:150%;
}

.left-content {
  flex: 30%;
  margin-top: 5dvh;
  background-color: white;
  text-align: center;
  height: 100%; /* 부모 요소의 높이를 상속받아 확장 */
  justify-content: center;
}


.right-content {
  flex: 70%;
  background-color: white;
  height: 80%; /* 부모 요소의 높이를 상속받아 확장 */
  border: black 1px;
  margin:40px;
  text-align:center;
  justify-content:center;
}
</style>