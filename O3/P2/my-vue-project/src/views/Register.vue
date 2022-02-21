<template>
  <div class="register">
    <h1>Register an account</h1>
    <RegisterComponent />
    <span>Already have an account? Login <router-link :to="{ name: 'login' }">here</router-link>!</span>
  </div>
</template>
<script>
import RegisterComponent from "@/components/RegisterComponent.vue";

export default {
  name: 'Register',
  components: {
    RegisterComponent
  },
}
</script>
