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
    background-color: #498C74;
    color: white;
    }

</style>
