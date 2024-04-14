<template>
  <div class="right-column">
    <div class="date-counter">{{ deadlineDay }}</div>
    <div class="participation-info">
      <div class="participants">{{ headCount }}명 참여</div>
      <div class="amount-raised">{{ joinUser }}명 달성</div>
      <div>{{ price }}원</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PostRight',
  props: ['deadline', 'headCount', 'joinUser', 'price'],

  // 데드라인 일수로 변환
  computed: {
    deadlineDay() {
      // deadline 값이 있는지 확인
      if (!this.deadline) {
        return '데이터 로딩 중...'; // 데이터가 아직 로드되지 않았음
      }
      // deadline을 Date 객체로 변환
      const deadlineDate = new Date(this.deadline);
      // 유효한 날짜인지 확인
      if (isNaN(deadlineDate.getTime())) {
        return '날짜 형식 오류'; // 유효하지 않은 날짜 데이터 처리
      }
      // 현재 날짜 객체 생성
      const now = new Date();
      // 남은 시간(밀리초) 계산
      const diffTime = deadlineDate - now;
      // 남은 일수 계산
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      // 결과 반환
      return diffDays >= 0 ? diffDays : '기한 만료'; // 과거의 날짜는 '기한 만료' 처리
    }
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
.participation-info .participants,
.participation-info .amount-raised {
  margin-top: 10px;
  margin-bottom: 8px;
}
.participants {
  color: #5d9883;
  font-weight: bold;
  font-size: 30px;
}
.amount-raised {
  font-weight: bold;
  font-size: 30px;
}
.participate-button {
  background-color: #458971;
  height: 60px;
  width: 180px;
  color: white;
  font-size: 2em;
  border-radius: 15px;
  box-shadow: 123px gray;
  border: none;
  cursor: pointer;
  margin-top: 30px;
  margin-bottom: 16px;
}
.social-buttons {
  display: flex;
  margin-bottom: 16px;
}
.btn-svg {
  border: 1px solid black;
  background: none;
  padding: 4px;
  cursor: pointer;
  margin-right: 8px;
}
.btn-svg img {
  width: 30px;
  height: 30px;
  border: none;
}
</style>