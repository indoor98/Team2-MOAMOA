<template>
    <body>
        <br/>
    <div>
        <form @submit.prevent="submitPost" class="post-form">
        <label for="title">상품 이름:</label>
        <input type="text" id="title" v-model="post.title" required>

        <label for="price">가격:</label>
        <input type="number" id="price" v-model="post.price" required>

        <label for="headCount">참여 인원:</label>
        <input type="number" id="headCount" v-model="post.headCount" required>

        <label for="deadLine">마감 일자:</label>
        <input type="datetime-local" id="deadLine" v-model="post.deadLine" required>

        <label for="receivePlace">수령 장소:</label>
        <input type="text" id="receivePlace" v-model="post.receivePlace" required>

        <label for="productUrl">제품 URL:</label>
        <input type="url" id="productUrl" v-model="post.productUrl" required>

        <!-- 해시태그 입력 -->
        <label for="hashtagList">해시태그:</label>
        <input type="text" id="hashtagList" v-model="hashtags" required>
        <p class="hint">여러 개의 해시태그를 입력할 경우 쉼표(,)로 구분해주세요.</p>

        <button type="submit">작성 완료</button>
        </form>
    </div>
        
    </body>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'

const post = ref({
    title: '',
    price: null,
    headCount: null,
    deadLine: '',
    receivePlace: '',
    productUrl: '',
    hashtagList: []
})

const hashtags = ref('');

const submitPost = async () => {
    try {
        const accessToken = localStorage.getItem('accessToken')
        const response = await axios.post(
        'http://localhost:8080/api/post',
        {
            title: post.value.title,
            price: post.value.price,
            headCount: post.value.headCount,
            deadLine: post.value.deadLine,
            receivePlace: post.value.receivePlace,
            productUrl: post.value.productUrl,
            hashtagList: parseHashtags(hashtags.value)
        },
        {
            headers: {
            Authorization: `Bearer ${accessToken}`
            }
        }
        )
        console.log('게시글 작성 완료:', response.data)
    } catch (error) {
        console.error('게시글 작성 에러:', error)
    }
    }

    // 해시태그를 쉼표(,)로 분할하여 배열로 반환하는 함수
    const parseHashtags = (hashtagsStr) => {
    return hashtagsStr.split(',').map(tag => {
        return { tag };
    });
    }
    </script>

<style scoped>
body {
  background-color: #f7efe4; /* 전체 페이지의 배경색 변경 */
}

.post-form {
    display: flex;
    flex-direction: column;
    max-width: 400px;
    margin: auto;
}

.post-form label {
    margin-bottom: 5px;
}

.post-form input, .post-form textarea {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
}

.post-form button {
    padding: 10px 20px;
    background-color: #019b63;
    color: white;
    border: none;
    cursor: pointer;
}

.post-form button:hover {
  background-color: #ffd557;
}

.post-form .hint {
  font-size: 0.8rem;
  margin-top: 5px;
}
</style>
