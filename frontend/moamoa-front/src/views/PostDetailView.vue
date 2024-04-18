<script setup>
import NavHeader from "@/components/NavHeader.vue";
import PostCommentNew from "@/components/postdetailnew/PostCommentNew.vue";
import PostRightTH from "@/components/postdetailnew/PostRightTH.vue";
import PostLeftNew from "@/components/postdetailnew/PostLeftNew.vue";
</script>

<template>
  <header>
    <NavHeader/>
  </header>

  <div class="postdetail-component">
    <div class = "left-component">
      <PostLeftNew/>
    </div>
    <div class = "right-component">
      <PostRightTH/>
    </div>
  </div>
  <div class="comment-component">
    <PostCommentNew/>
  </div>
</template>

<style scoped>
header {
  background-color: #f7efe4;
  color: white;
}

.postdetail-component {
  display: flex;
  flex-direction: row;
  justify-content: center;
  max-height: 2000px;
}

.left-component,
.right-component {
  margin: 20px;
}
</style>