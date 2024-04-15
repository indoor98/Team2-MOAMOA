<template>
    <header>
        <NavHeader />
    </header>
    <div>
        <PostWrite/>
    </div>
</template>

<script setup>
import NavHeader from "@/components/NavHeader.vue";
import PostWrite from '@/components/PostWrite.vue'
</script>

<style scoped>
header {
    background-color: #f7efe4;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}

</style>
