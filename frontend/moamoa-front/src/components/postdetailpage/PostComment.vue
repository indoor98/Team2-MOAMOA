<template>
  <div class="bottom-column">
    <div class="comment-section">
      <textarea placeholder="댓글"></textarea>
      <button class="comment-button">등록</button>
    </div>
  </div>
</template>

<style scoped>
.bottom-column {
  padding: 20px;
  margin-top: 20px;
}

.comment-section {
  display: flex;
  flex-direction: column;
}

.comment-section textarea {
  width: 100%;
  margin-bottom: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

.comment-section .comment-button {
  padding: 10px 20px;
  background-color: #458971;
  color: white;
  border: none;
  cursor: pointer;
  align-self: flex-end;
}
</style>