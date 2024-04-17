<script setup>
import NavHeader from "@/components/NavHeader.vue";
import PostCommentNew from "@/components/postdetailnew/PostCommentNew.vue";
import PostRightTH from "@/components/postdetailnew/PostRightTH.vue";
</script>

<template>
  <header>
    <NavHeader/>
  </header>

  <div class="postdetail-content">
    <div class="left-contentt">

    </div>
    <div class="right-content">
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

.postdetail-content {
  display: flex;
}
</style>