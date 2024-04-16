<template>
  <div class="left-content">
    <!-- 기존 코드 -->
    <div v-if="modalVisible" class="modal-mask">
      <!-- 새 비밀번호 모달 -->
      <NewPasswordModal v-if="isChangePasswordModalVisible" @closeModal="isChangePasswordModalVisible = false" />
      <!-- 회원 정보 수정 모달 -->
      <div v-else class="modal-wrapper">
        <!-- 기존 모달 내용 -->
        <!-- 모달 내용에 새 비밀번호 모달 트리거 추가 -->
        <div class="modal-body">
          <button @click="openChangePasswordModal" class="modal-btn">비밀번호 재설정</button>
          <button @click="changeNickname" class="modal-btn">닉네임 변경</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import NewPasswordModal from './NewPasswordModal.vue' // 새 비밀번호 모달 컴포넌트 불러오기

const modalVisible = ref(false)
const isChangePasswordModalVisible = ref(false)

const openChangePasswordModal = () => {
  isChangePasswordModalVisible.value = true
}

const closeModal = () => {
  modalVisible.value = false
}

const changeNickname = () => {
  // 닉네임 변경 로직 구현
  console.log('닉네임 변경');
  closeModal();
}

</script>