import { createStore } from 'vuex'

export default createStore({
  state: {
    username: null,
  },
  mutations: {
    SET_USER(state, username) {
      state.username = username
    }
  },
  actions: {
    login(context, data) {
      console.info(`Logging in as ${ data.username }.`);
      context.commit("SET_USER", data.username);
    },
    logout(context) {
      console.info("Logging out.");
      context.commit("SET_USER", null);
    },
  },
  modules: {
  }
})
