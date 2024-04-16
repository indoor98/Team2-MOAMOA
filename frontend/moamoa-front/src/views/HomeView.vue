<template>
  <header>
    <NavHeader />
  </header>

  <div class="container mt-5">
    <div class="row g-4">
      <PostCard v-for="(post, index) in posts" :key="index" :post="post" />
    </div>
  </div>
</template>

<script>
import NavHeader from "@/components/NavHeader.vue";
import PostCard from "@/components/PostCard.vue"; // PostCard.vue 컴포넌트 추가
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

export default {
  components: { NavHeader, PostCard }, // PostCard.vue 컴포넌트 등록
  data() {
    return {
      posts: []
    };
  },
  created() {
    this.fetchPosts();
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get("/api/list/{pageno}");
        this.posts = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async toLogin() {
      try {
        this.$router.push({
          name: "login",
        });
      } catch (error) {
        console.error(error);
      }
    },
    async toSignup() {
      try {
        this.$router.push({
          name: "signup",
        });
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
header {
  background-color: #f7efe4;
  color: white;
}
</style>
