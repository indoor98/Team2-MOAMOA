<script setup>
import {ref, onMounted, watch} from 'vue'
import {useRouter} from 'vue-router'
import axios from "axios";

const post = ref({
  "id": 0,
  "title": "string",
  "price": 0,
  "headCount": 100,
  "joinedUsersCount": 0,
  "deadLine": "2024-04-17T11:56:05.061Z",
  "receivePlace": "string",
  "productUrl": "string",
  "hashtagList": [
    {
      "hashtag": "string"
    }
  ],
  "joined": true
});
const isPull = ref(0);
const accessToken = localStorage.getItem("accessToken");
const userType = ref(-1);
const router = useRouter();

if (post.value.headCount === post.value.joinedUsersCount) {
  isPull.value = 1;
  console.log(isPull.value);
} else {
  isPull.value = 0;
  console.log(isPull.value);
}

const isAttend = () => {
  try {
    const postId = window.location.pathname.split('/').pop();
    const response = axios.get(`http://localhost:8080/api/post/post/attend/${postId}`, {
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }
    })
        .then((value) => {
          userType.value = value.data;
          console.log("usertype : ", userType.value);
        });

  } catch (error) {
    console.log(error);
  }
}
// const isAttend = async () => {
//   try {
//     const postId = window.location.pathname.split('/').pop();
//     const response = await axios.get(`http://localhost:8080/api/post/post/attend/${postId}`, {
//       headers: {
//         'Authorization': `Bearer ${accessToken}`
//       }
//     });
//     if (response && response.data) {
//       userType.value = response.data;
//       console.log("usertype : ",userType.value);
//     }
//   } catch (error) {
//     console.log(error);
//   }
// }
const loadPost = async () => {
  try {
    // 게시글 정보 불러오기
    const postId = window.location.pathname.split('/').pop();
    // API 요청 보내기
    const response = await fetch(`http://localhost:8080/api/post/${postId}`, {
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }
    });
    const data = await response.json();
    post.value = data;

  } catch (error) {
    console.log(error);
  }
}

const joinPost = async () => {
  try {
    // 게시글 정보 불러오기
    const postId = window.location.pathname.split('/').pop();
    // API 요청 보내기
    const response = await axios.post(`http://localhost:8080/api/post/join/${postId}`,
        {
          postno: postId
        },
        {
          headers: {
            'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
          }
        });
    const data = await response;
    console.log(post.value);

  } catch (error) {
    console.log(error);
  }
}

const cancelJoin = async () => {
  try {
    // 게시글 정보 불러오기
    const postId = window.location.pathname.split('/').pop();
    // API 요청 보내기
    const response = await axios.put(`http://localhost:8080/api/post/cancel/${postId}`,
        {
          postno: postId.value
        },
        {
          headers: {
            'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
          }
        });
    const data = await response;
    console.log(post.value);

  } catch (error) {
    console.log(error);
  }
}

const updateJoin = async () => {
  try {
    console.log('게시글 수정 폼으로 이동');
    // this.$router.push({ name: 'postUpdate', params: { postno: this.postno } });
    router.push({name: 'postUpdate'});
  } catch (error) {
    console.error("Error cancelling the join: ", error);
  }
};
const deletePost = async () => {
  try {
    const postId = window.location.pathname.split('/').pop();
    console.log('게시글 수정 폼으로 이동');
    // this.$router.push({ name: 'postUpdate', params: { postno: this.postno } });
    const response = await axios.delete(`http://localhost:8080/api/post/${postId}`, {
      headers: {
        'Authorization': `Bearer ${accessToken}` // accessToken을 Bearer 토큰으로 사용하여 Authorization 헤더에 담음
      }
    });
    router.push({name: 'home'});
  } catch (error) {
    console.error("Error cancelling the join: ", error);
  }
};

onMounted(async () => {
  await loadPost();
  await isAttend();
});


</script>

<template>
  <div class="right-column">
    <div class="info">
      <div class="join-box">
        <div class="head-count-item">{{ post.headCount }}명 모집 <br/></div>
        <div class="join-count-item">{{ post.joinedUsersCount }}명 참여</div>
      </div>
      <div>
        <br/>
        <div class="participation-info">
          <div class="amount-raised">
            마감 일자 : {{ post.deadLine ? post.deadLine.slice(0, 10) + ' ' + post.deadLine.slice(11, 16) : '' }}
          </div>
          <div class="info-item">
            가격 : {{ post.price }}원
          </div>
          <div class="place">
            모임 장소 : {{ post.receivePlace }}
          </div>
        </div>
      </div>
    </div>


    <div class="post-right-button-logined">
      <div v-if="accessToken" class="post-right-button-ispull">
        <div v-if="userType ==1">
          <button @click.prevent="updateJoin" class="edit-button"> 수정하기</button>
          <button @click.prevent="deletePost" class="delete-button"> 삭제하기</button>
        </div>
        <div v-else-if="userType==0">
          <div v-if="isPull==1">
            <button @click.prevent=""> 마감이요</button>
          </div>
          <div v-else>
            <button @click.prevent="joinPost" class="participate-button"> 참여 하실래요 ?</button>
          </div>
        </div>
        <div v-else>
          <button @click.prevent="cancelJoin" class="cancel-button"> 취소하기</button>
        </div>
      </div>
      <div v-else class="post-right-button-isnotpull">
        <div>
          <button> 로그인 하십쇼</button>
        </div>
      </div>
    </div>
  </div>


  <!--  <div class="right-column">-->
  <!--      <div v-if="isJoined == 0">-->
  <!--      <button @click="joinGroupPurchase" class="participate-button">참여하기</button>-->
  <!--      </div>-->
  <!--      <div v-else>-->
  <!--        <button @click="cancelJoin" class="cancel-button">참여 취소</button>-->
  <!--      </div>-->

  <!--      <button @click="updateJoin" class="update-button">게시글 수정</button>-->
  <!--  </div>-->


</template>

<style scoped>
.right-column {
  flex: 4;
  display: flex;
  flex-direction: column;
  padding: 20px;
  align-items: flex-start;
}

.edit-button, .delete-button {
  font-size: 1.5em;
  border-radius: 10px;
  cursor: pointer;
  border: none;
  margin-top: 30px;
  margin-left: 20px;
  padding: 10px 20px;
}

.edit-button {
  background-color: #488b73; /* Green */
  color: white;
}

.delete-button {
  background-color: #fbbbbb;
  color: white;
}

.join-box {
  margin-top: 150px;
  display: flex;
  flex-direction: column;
  align-content: center;
  font-weight: bold;
  text-align: center;
}

.head-count-item {
  color: #dd4c1c;
  align-content: center;
}

.join-count-item {
  color: blue;
}

.head-count-item, .join-count-item {
  font-size: 30px ;

}

.amount-raised, .info-item, .place {
  margin-top: 5px;
  font-size: 25px;
}

.info-item {
  margin-bottom: 10px;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

.info-item:last-child {
  border-bottom: none;
}

.participation-info {
  display: flex;
  flex-direction: column;
}

.post-right-button-isnotpull button {
  background-color: #008CBA;
}

/* 추가 상태 버튼 스타일링, 필요에 따라 */
/* 예: 수정하기, 삭제하기 버튼에 다른 스타일을 적용하고 싶다면 */
/*.post-right-button-logined .edit-button {
  background-color: #f0ad4e;
}

.post-right-button-logined .delete-button {
  background-color: #d9534f;
}

 */

.participate-button, .cancel-button {
  font-size: 1.5em;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
  border: none;
  width: 450px;
  margin-top: 40px;
}

.participate-button {
  background-color: #488b73; /* Green */
  color: white;

}

.cancel-button {
  background-color: #fbbbbb;
  color: white;

}

.participate-button, .cancel-button {
  font-size: 1.5em;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
}

</style>