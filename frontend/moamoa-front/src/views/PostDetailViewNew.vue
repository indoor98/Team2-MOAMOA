<script setup>
import NavHeader from "@/components/NavHeader.vue";
import PostCommentNew from "@/components/postdetailnew/PostCommentNew.vue";
import PostRightNew from "@/components/postdetailnew/PostRightNew.vue";

</script>

<template>
  <header>
    <NavHeader/>
  </header>

  <div class = "right-component">
    <PostRightNew/>
  </div>

  <div class="comment-component">
    <PostCommentNew/>
  </div>

</template>

<style scoped>

</style>