import { createStore } from 'vuex'

export const options = {
  state: {
    username: "",
  },
  mutations: {
    SET_USER(state, username) {
      state.username = username
    },
    RESET(state) {
      state.username = ""
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
}

export default createStore(options)
