<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from "axios";

const router = useRouter()

// accessToken 담기
const accessToken = localStorage.getItem("accessToken");
// 댓글 목록을 담을 ref
const comments = ref([]);
// 에러 메시지를 나타낼 ref
const error = ref(null);
// 새로운 댓글을 담을 ref
const newComment = ref('');
// 현재 유저의 게시글 참여 유형을 담을 ref : 0 -> 참여 x  1 -> 게시글 작성자 2 -> 작성자는 아니지만 참여 중
const userType = ref(-1);

const isAttend = () => {
  try {
    const postId = window.location.pathname.split('/').pop();
    const response = axios.get(`http://localhost:8080/api/post/post/attend/${postId}`,{
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }})
        .then( (value) => {
          userType.value = value.data;
          console.log(userType.value);
        });

  } catch (error) {
    console.log(error);
  }
}
isAttend();


// 댓글을 불러오는 함수
const loadComments = async () => {
  try {
    // 게시글의 postid 가져오기
    const postId = window.location.pathname.split('/').pop();
    // API 요청 보내고 댓글 목록 받아오기
    const response = await fetch(`http://localhost:8080/api/comment/${postId}/list`, {
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }
    });
    const data = await response.json();
    // 받아온 댓글 목록을 ref에 저장
    comments.value = data;
  } catch (error) {
    // 에러가 발생하면 에러 ref 업데이트
    error.value = error.message || '댓글을 불러오는 중 에러가 발생했습니다.';
  }
};

const submitComment = async () => {
  try {
    const postId = window.location.pathname.split('/').pop();
    const response = await axios.post(`http://localhost:8080/api/comment/${postId}/write`, {
      content: newComment.value
    }, {
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }
    });
    newComment.value = '';
    await loadComments(); // 새 댓글을 등록한 후에 댓글 목록을 다시 불러옴
  } catch (error) {
    console.log(error);
  }
}

onMounted(loadComments);

</script>

<template>
  <div class="comments-container">
    <h4 class="comment-header">댓글</h4>
    <!-- 댓글 목록 표시 -->
    <div v-if="comments.length > 0" class="comments-list">
<!--      <ul>-->
      <div>
        <li v-for="(comment, index) in comments" :key="index" class="comment-item">
          <span class="comment-nickname"> {{ comment.nickname }} </span>
          <span class="comment-date"> {{ comment.createDate.slice(0, 10) + ' ' + comment.createDate.slice(11, 16)}} <br/></span>
          <span class="comment-content"> {{ comment.comment }} </span>
        </li>
      </div>
<!--      </ul>-->
    </div>

    <!-- 댓글이 없을 때 표시 -->
    <div v-else>
      <p>댓글이 없습니다.</p>
    </div>
  </div>

  <div class="comment-info">
      <span>
        다른 사람에게 불쾌감을 주는 욕설, 혐오, 비하의 표현이나 타인의 권리를 침해하는 내용은 주의해주세요.
      </span>
  </div>

  <div class="bottom-column">
    <div v-if="userType >= 1">
    <div class="comment-section">
      <textarea v-model="newComment" placeholder="댓글을 입력하세요"></textarea>
      <button class="comment-button" @click.prevent="submitComment">등록</button>
    </div>
    </div>
    <div v-else>
    </div>
  </div>
</template>

<style scoped>
.bottom-column {
  //padding: 20px;
  margin-top: 20px;
  padding-left: 20px;
  padding-right: 20px;
}

li {
  list-style: none;
}

.comment-section {
  display: flex;
  flex-direction: column;
}

.comment-section textarea {
  width: 100%;
  margin-bottom: 8px;
  border: 1px solid #ccc;
  //border-radius: 4px;
  resize: none;
  height: 90px;
}

.comment-section .comment-button {
  padding: 10px 20px;
  background-color: #458971;
  color: white;
  border: none;
  cursor: pointer;
  align-self: flex-end;
}

.comments-container {
  display: flex;
  background-color: rgba(245,237,226,0.58);
  border: 1px solid #e3e3e3;
  flex-direction: column;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

.comment-header{
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 20px;
}

.comments-list {
  margin-bottom: 40px;
}

.comment-item {
  padding: 10px;
  background-color: white;
  border: 1px solid #e3e3e3;
}

.comment-content {
  margin-top: 50px;
  padding-left: 10px;
  margin-left: 76px;
}

.comment-nickname {
  font-weight: bold;
  margin-left: 20px;
}

.comment-date {
  margin-left: 40px;
  font-size: 0.8em;
  color: #888;
}

.no-comments {
  color: #888;
}

.comment-info {
  display: flex;
  background-color: rgba(245,237,226,0.58);
  border: 1px solid #e3e3e3;
  flex-direction: column;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  height: 60px;
  justify-content: center;
  align-items: center;
}

.comment-input textarea {
  width: 100%;
  margin-bottom: 10px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

.comment-button {
  padding: 10px 20px;
  background-color: #458971;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.comment-button:hover {
  background-color: #356e5c;
}

textarea {
  padding-top: 5px;
  padding-left: 10px;
}

</style>
