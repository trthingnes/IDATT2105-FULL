<template>
  <div class="login">
    <h1>Login to your account</h1>
    <LoginComponent @login="handleLogin" />
    <span v-if="registerVisible">Don't have an account? Register <router-link :to="{ name: 'register' }">here</router-link>!</span>
  </div>
</template>

<script>
import LoginComponent from '@/components/LoginComponent.vue'
import { api } from '@/services/api.js'

export default {
  name: 'Login',
  components: {
    LoginComponent
  },
  data() {
    return {
      registerVisible: false,
    }
  },
  methods: {
    async handleLogin({ username, password }) {
      let response = await api({ username: username, password: password })

      if (response.loginStatus === "Success") {
        this.$store.dispatch('login', { username: username })
        this.$router.push({ name: 'user' })
      } else {
        this.registerVisible = true;
      }
    }
  }
}
</script>
