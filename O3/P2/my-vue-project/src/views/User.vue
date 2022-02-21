<template>
  <div class="register">
    <h1>Welcome {{ username }}</h1>
  </div>
</template>
<script>
export default {
  beforeMount() {
    if(!this.username) {
      this.$router.push({ name: 'login' })
    }
  },
  computed: {
    username() {
      return this.$store.state.username
    }
  }
}
</script>
