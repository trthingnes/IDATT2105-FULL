import { createStore } from "vuex"

export default createStore({
    state: {
        name: "",
        email: "",
        token: "",
    },
    mutations: {
        SET_NAME(state, name) {
            state.name = name
        },
        SET_EMAIL(state, email) {
            state.email = email
        },
        SET_TOKEN(state, token) {
            state.token = token
        },
    },
    actions: {},
    modules: {},
})
