  <template>
    <form @submit.prevent="loginClicked">
      <div id="loginContainer">
        <div id="username">
          <label id="usernameLabel">Username:</label>
          <textarea v-model="username" data-testid="userField"></textarea>
        </div>
        <div id="password">
          <label id="passwordLabel">Password: </label>
          <textarea v-model="password" data-testid="passField"></textarea>
          <button>Login</button>
        </div>
      </div>
    </form>
 </template>


<script>
export default {
  name: 'LoginComponent',
  methods: {
    loginClicked () {
      this.$emit('login', { username: this.username, password: this.password })
    },

  },
  data() {
    return {
      username: '',
      password: '',
    }
  }

}
</script>

<style scoped>
#loginContainer {
    display: grid;
    justify-content: center;
    margin: 40px;
}

#username, #password {
  display: flex;
  flex-direction: row;
  align-items: center;
  column-gap: 20px;
}

#usernameLabel, #passwordLabel {
  width: 100px;
}
</style>
