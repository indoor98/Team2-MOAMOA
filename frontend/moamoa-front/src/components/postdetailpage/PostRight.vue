<template>
  <div class="right-column">
    <div class="date-counter">{{ deadlineDay }}</div>
    <div class="participation-info">
      <div class="participants">{{ headCount }}명 참여</div>
      <div class="amount-raised">{{ joinedUsersCount }}명 달성</div>
      <button v-if="!isJoined" @click="joinGroupPurchase" class="participate-button">참여하기</button>
      <button v-if="isJoined" @click="cancelJoin" class="cancel-button">참여 취소</button>
      <div>{{ price }}원</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PostRight',
  props: ['deadline', 'headCount', 'joinedUsersCount', 'price', 'postJoin'],

  data() {
    return {
      isJoined: false,
      joining: false,
    };
  },
  methods: {
    joinGroupPurchase() {
      if (!this.postJoin) {
        console.error("postJoin (post.id) is undefined.");
        return;
      }
      this.isJoined = true;
      const accessToken = localStorage.getItem('accessToken');
      axios.post(`http://localhost:8080/api/post/join/${this.postJoin}`, {}, {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      }).then(response => {
        this.joinedUsersCount++;
        this.$emit('updateJoinedCount', this.joinedUsersCount);
      }).catch(error => {
        console.error("Error joining the group: ", error);
        this.isJoined = false;
      });
    },
    cancelJoin() {
      if (!this.postJoin) {
        console.error("postJoin (post.id) is undefined.");
        return;
      }
      const accessToken = localStorage.getItem('accessToken');
      axios.put(`http://localhost:8080/api/post/cancel/${this.postJoin}`, {}, {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      }).then(response => {
        this.isJoined = false;
        this.joinedUsersCount--;
        this.$emit('updateJoinedCount', this.joinedUsersCount);
      }).catch(error => {
        console.error("Error cancelling the join: ", error);
      });
    }
  }

  // 데드라인 일수로 변환
  // computed: {
  //   deadlineDay() {
  //     if (!this.deadline) {
  //       return '데이터 로딩 중...'; // 데이터가 아직 로드되지 않았음
  //     }
  //     const deadlineDate = new Date(this.deadline);
  //     if (isNaN(deadlineDate.getTime())) {
  //       return '날짜 형식 오류'; // 유효하지 않은 날짜 데이터 처리
  //     }
  //     const now = new Date();
  //     const diffTime = deadlineDate - now;
  //     const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  //     return diffDays >= 0 ? diffDays : '기한 만료'; // 과거의 날짜는 '기한 만료' 처리
  //   }
  // },
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