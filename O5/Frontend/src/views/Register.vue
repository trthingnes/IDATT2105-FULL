<template>
    <div class="columns">
        <div class="column"></div>
        <div class="column is-half">
            <h1 class="title mt-5">Register an account</h1>
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
                    <div class="control">
                        <p>
                            Already have an account?
                            <router-link :to="{ name: 'login' }"
                                >Login here!</router-link
                            >
                        </p>
                        <button class="button is-link mt-3">Register</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="column"></div>
    </div>
</template>

<script>
import { register, login } from "@/services/api"
import { useStore } from "vuex"
import { useRouter } from "vue-router"
import { ref } from "vue"

export default {
    setup() {
        const store = useStore()
        const router = useRouter()

        const username = ref("")
        const password = ref("")

        const submit = async () => {
            let status = await register(username.value, password.value)
            if (status) {
                let token = await login(username.value, password.value)

                if (token) {
                    store.commit("SET_TOKEN", token)
                    router.push({ name: "home" })
                }
            }
        }

        return { username, password, submit }
    },
}
</script>
