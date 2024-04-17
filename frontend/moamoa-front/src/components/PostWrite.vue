<template>
    <div class="container">
        <div class="form-section">
            <form @submit.prevent="submitPost" class="post-form">
                <div class="row">
                    <div class="col-25">
                        <label for="title">상품 이름:</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="title" v-model="post.title" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="price">가격:</label>
                    </div>
                    <div class="col-75">
                        <input type="number" id="price" v-model="post.price" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="headCount">참여 인원:</label>
                    </div>
                    <div class="col-75">
                        <input type="number" id="headCount" v-model="post.headCount" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="deadLine">마감 일자:</label>
                    </div>
                    <div class="col-75">
                        <input type="datetime-local" id="deadLine" v-model="post.deadLine" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="receivePlace">수령 장소:</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="receivePlace" v-model="post.receivePlace" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="productUrl">제품 URL:</label>
                    </div>
                    <div class="col-75">
                        <input type="url" id="productUrl" v-model="post.productUrl" required>
                    </div>
                </div>

                <!-- 해시태그 입력 -->
                <div class="row">
                    <div class="col-25">
                        <label for="hashtagList">해시태그:</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="hashtagList" v-model="hashtags" required>
                    </div>
                </div>

                <div class="row">
                    <p class="hint">여러 개의 해시태그를 입력할 경우 쉼표(,)로 구분해주세요.</p>
                </div>

                <div class="row">
                    <input type="submit" value="작성 완료" class="submit-button">
                </div>
            </form>
        </div>

        <div class="margin-bottom"></div>
    </div>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'
import { useRouter } from 'vue-router';

const router = useRouter();

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
        router.push({ name: 'home' }); // 작성 완료 후 HomeView로 이동
    } catch (error) {
        console.error('게시글 작성 에러:', error)
    }
}

// 해시태그를 쉼표(,)로 분할하여 배열로 반환하는 함수
const parseHashtags = (hashtagsStr) => {
    return hashtagsStr.split(',').map(hashtag => {
        return { hashtag };
    });
}
</script>

<style scoped>
.container {
    padding: 20px;
}

.header {
    margin-bottom: 20px;
}

.form-section {
    margin-bottom: 20px;
}

.margin-bottom {
    margin-bottom: 20px;
}

.row {
    display: flex;
    margin-bottom: 15px;
}

.col-25 {
    flex: 25%;
}

.col-75 {
    flex: 75%;
}

/* Clear floats after the columns */
.row::after {
    content: "";
    clear: both;
    display: table;
}

input[type=text],
input[type=number],
input[type=datetime-local],
input[type=url],
textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
}

label {
    padding: 12px 12px 12px 0;
    display: inline-block;
}

input[type=submit] {
    background-color: #04AA6D;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
}

input[type=submit]:hover {
    background-color: #45a049;
}

.hint {
    font-size: 0.8rem;
    margin-top: 5px;
}
</style>
