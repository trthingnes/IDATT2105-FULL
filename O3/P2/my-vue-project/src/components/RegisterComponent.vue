<template>
  <form @submit.prevent="registerClicked">
    <div id="registerContainer">
      <div id="username">
        <label id="usernameLabel">Username:</label>
        <textarea v-model="username" data-testid="userField"></textarea>
      </div>
      <div id="password">
        <label id="passwordLabel">Password: </label>
        <textarea v-model="password" data-testid="passField"></textarea>
      </div>
      <div id="name">
        <label id="nameLabel">Name: </label>
        <textarea v-model="name" data-testid="nameField"></textarea>
      </div>
      <div id="email">
        <label id="emailLabel">Email: </label>
        <textarea v-model="email" data-testid="emailField"></textarea>
        <button>Register</button>
      </div>
    </div>
  </form>
</template>


<script>
export default {
  name: 'RegisterComponent',
  methods: {
    registerClicked () {
      this.$emit('register', { username: this.username, password: this.password, name: this.name, email: this.email })
    },

  },
  data() {
    return {
      username: '',
      password: '',
      name: '',
      email: '',
    }
  }

}
</script>

<style scoped>
#registerContainer {
  display: grid;
  justify-content: center;
  margin: 40px;
}

#username, #password, #name, #email {
  display: flex;
  flex-direction: row;
  align-items: center;
  column-gap: 20px;
}

#usernameLabel, #passwordLabel, #emailLabel, #nameLabel {
  width: 100px;
}
</style>
