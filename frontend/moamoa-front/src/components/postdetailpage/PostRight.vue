<template>
  <div class="right-column">
    <div class="date-counter">{{ new Date(deadline).toLocaleDateString() }}</div>
    <div class="participation-info">
      <div class="participants">{{ headCount }}명 참여</div>
      <div class="amount-raised">{{ joinedUsersCount }}명 달성</div>
      <button v-if="!accessToken || !isJoined" @click="joinGroupPurchase" class="participate-button">참여하기</button>
      <button v-else @click="cancelJoin" class="cancel-button">참여 취소</button>
      <div>{{ price }}원</div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  name: 'PostRight',
  props: {
    deadline: String,
    headCount: Number,
    joinedUsersCount: Number,
    price: Number,
    postJoin: String,
    isUserJoined: Boolean
  },
  setup(props, { emit }) {
    const accessToken = ref(localStorage.getItem('accessToken'));
    const isJoined = ref(props.isUserJoined);

    const joinGroupPurchase = async () => {
      if (!accessToken.value) {
        alert("로그인을 진행해주세요.");
        return;
      }
      try {
        await axios.post(`http://localhost:8080/api/post/join/${props.postJoin}`, {}, {
          headers: { 'Authorization': `Bearer ${accessToken.value}` }
        });
        isJoined.value = true;
        emit('joinedOrCanceled');
      } catch (error) {
        console.error("Error joining the group: ", error);
        isJoined.value = false;
      }
    };

    const cancelJoin = async () => {
      if (!accessToken.value) {
        alert("로그인을 진행해주세요.");
        return;
      }
      try {
        await axios.put(`http://localhost:8080/api/post/cancel/${props.postJoin}`, {}, {
          headers: { 'Authorization': `Bearer ${accessToken.value}` }
        });
        isJoined.value = false;
        emit('joinedOrCanceled');
      } catch (error) {
        console.error("Error cancelling the join: ", error);
      }
    };

    return { joinGroupPurchase, cancelJoin, isJoined, accessToken };
  }
}
</script>

<style scoped>
.right-column {
  flex: 4;
  display: flex;
  flex-direction: column;
  padding: 20px;
  align-items: flex-start;
}

.date-counter {
  letter-spacing: 10px;
  color: red;
  font-size: 3em;
  font-weight: bold;
  padding-left: 400px;
  margin-top: 16px;
  margin-bottom: 16px;
}

.participation-info {
  display: flex;
  flex-direction: column;
}

.participants, .amount-raised {
  margin-top: 10px;
  margin-bottom: 8px;
  font-weight: bold;
  font-size: 30px;
}

.participate-button, .cancel-button {
  font-size: 1.5em;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
}

.participate-button {
  background-color: #458971; /* Green */
  color: white;
  font-size: 1.5em;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
}

.cancel-button {
  background-color: #ff6347; /* Tomato */
  color: white;
  font-size: 1.5em;
  padding: 10px 20px;
  border-radius: 10px;
  cursor: pointer;
}
</style>