<template>
  <div class="left-column">
    <div class="event-description">{{ title }}</div>
    <div class="hashtags">
      <button v-for="tag in hashtags" :key="tag.id">
        {{ tag.hashtag }}
      </button>
    </div>
    <div>나눔 장소 : {{ receivePlace }} </div>
    <img :src="postImg" alt="Event Image" class="event-image">
  </div>
</template>

<script>
export default {
  name: 'PostLeft',
  props: ['title', 'hashtags', 'receivePlace', 'postImg']
}

</script>

<style scoped>
.left-column {
  flex: 3;
  display: flex;
  flex-direction: column;
  padding: 20px;
}
.event-description {
  font-size: 2em;
  margin-bottom: 8px;
}
.hashtags button {
  border: none;
  padding: 4px 8px;
  margin-right: 8px;
  cursor: pointer;
  background-color: snow;
}
.img-style img {
  width: 550px;
  height: auto;
  margin: 16px;
  border-radius: 4px;
}
</style>