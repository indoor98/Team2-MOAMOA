<script setup>

</script>

<template>
<<<<<<< Updated upstream

=======
  <div class = "body">

  <div class="header">
    <div class="logo">MOA<br/>MOA</div>
    <div class="search-box-container">
      <input type="text" class="search-box" placeholder="검색">
      <div class="search-icon">&#128269;</div> <!-- 돋보기 아이콘 (HTML Entity 사용) -->
    </div>
  </div>

  <div class="list-container">
    <div class="list-header">
      <div>번호</div>
      <div>작성자명</div>
      <div>이미지</div>
    </div>
    <!-- 리스트 아이템 예시 -->
    <div class="list-item">
      <div class="number">1</div>
      <div class="author-name">작성자명</div>
      <div><img src="../views/floral.jpg" alt="이미지"></div>
    </div>
    <!-- 추가 리스트 아이템은 여기에 -->
  </div>

  </div>
>>>>>>> Stashed changes
</template>

<style scoped>

<<<<<<< Updated upstream
=======
.body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}
.header {
  background-color: #498C74;
  padding: 20px 10%;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
}
.search-box-container {
  position: relative;
  flex-grow: 1;
  margin: 0 20px;
}
.search-box {
  width: 100%;
  height: 40px;
  padding: 10px 40px 10px 20px;
  box-sizing: border-box;
  border: none;
  border-radius: 20px;
}
.search-icon {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
}
.list-container {
  margin-top: 20px;
  padding: 0 10%;
}
.list-header {
  display: flex;
  padding: 10px;
  border-bottom: 2px solid #498C74;
  font-weight: bold;
}
.list-header div, .list-item div {
  flex: 1;
  text-align: center;

}
.list-item {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}
.list-item img {
  width: 50px;
  height: 50px;
}
>>>>>>> Stashed changes
</style>