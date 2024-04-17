import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('../views/SignupView.vue')
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: () => import('../views/MypageView.vue')
    },
    {
      path: '/mypage/change/pwd',
      name: 'mypagePasswordChange',
      component: () => import('../views/MypagePasswordChangeView.vue')
    },{
      path: '/mypage/change/nickname',
      name: 'mypageNicknameChange',
      component: () => import('../views/MypageNicknameChangeView.vue')
    },
      // 게시글 상세페이지
    {
      path: '/post/:postno',
      name: 'postno',
      component: () => import('../views/PostDetailView.vue')
    },
    // 게시글 상세페이지 New
    {
      path: '/postnew/:postno',
      name: 'postnoNew',
      component: () => import('../views/PostDetailViewNew.vue')
    },
    // 게시글 작성페이지
    {
      path: '/post',
      name: 'postwrite',
      component: () => import('../views/PostWriteView.vue')
    },
    {
      path: '/auth',
      name: 'auth',
      component: () => import('../views/AuthView.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('../views/AdminView.vue')
    },
    { // 내가 작성한 게시글 목록
      path: '/mypost',
      name: 'mypost',
      component: () => import('../views/MyPostView.vue')
    },
    { // 내가 참석한 게시글 목록
      path: '/joinpost',
      name: 'joinpost',
      component: () => import('../views/JoinPostView.vue')
    }

  ]
})

export default router
