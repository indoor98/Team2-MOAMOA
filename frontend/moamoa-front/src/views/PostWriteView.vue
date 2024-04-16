<template>
    <header>
        <NavHeader />
    </header>
    <div>
        <PostWrite @submit="redirectToHomeView" /> <!-- PostWrite 컴포넌트에 submit 이벤트를 수신하여 redirectToHomeView 메서드 호출 -->
    </div>
</template>

<script setup>
import NavHeader from "@/components/NavHeader.vue";
import PostWrite from '@/components/PostWrite.vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const redirectToHomeView = () => {
    router.push({ name: 'home' }); // 작성 완료 후 HomeView로 이동
}
</script>

<style scoped>
header {
    background-color: #f7efe4;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}
</style>
