<template>
    <div class="columns">
        <div class="column"></div>
        <div class="column is-half">
            <h1 class="title mt-5">Login to your account</h1>
            <div v-if="incorrect" class="field">
                <div class="notification is-danger mt-5">
                    Incorrect username or password.
                </div>
            </div>
            <form @submit.prevent="submit">
                <div class="field mt-5">
                    <label for="username" class="label">Username</label>
                    <div class="control has-icons-left">
                        <input
                            v-model="username"
                            id="username"
                            type="text"
                            class="input"
                        />
                        <span class="icon is-small is-left">
                            <em class="fa fa-user"></em>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <label for="password" class="label">Password</label>
                    <div class="control has-icons-left">
                        <input
                            v-model="password"
                            id="password"
                            type="password"
                            class="input"
                        />
                        <span class="icon is-small is-left">
                            <em class="fa fa-key"></em>
                        </span>
                    </div>
                </div>
                <div class="field">
                    <p>
                        Don't have an account?
                        <router-link :to="{ name: 'register' }"
                            >Register one here!</router-link
                        >
                    </p>
                    <div class="control">
                        <button class="button is-link mt-3">Login</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="column"></div>
    </div>
</template>

<script>
import { login } from "@/services/api"
import { useStore } from "vuex"
import { useRouter } from "vue-router"
import { ref } from "vue"

export default {
    setup() {
        const store = useStore()
        const router = useRouter()

        const username = ref("")
        const password = ref("")
        const incorrect = ref(false)

        const submit = async () => {
            let token = await login(username.value, password.value)
            if (token) {
                store.commit("SET_TOKEN", token)
                router.push({ name: "home" })
            } else {
                incorrect.value = true
            }
        }

        return { username, password, incorrect, submit }
    },
    beforeMount() {
        const store = useStore()
        if (store.state.token) {
            this.$router.push({ name: "login" })
        }
    },
}
</script>
