import { mount } from "@vue/test-utils";
import Login from "@/views/Login";
import { api } from "@/services/api";
import { options } from "@/store"
import { createStore } from "vuex";

jest.mock('@/services/api')

const store = createStore(options)

describe('Login', () => {
    beforeEach(() => {
        store.commit('RESET')
        jest.clearAllMocks()
    })

    it('routes to logged in view if api succeeds', async () => {
        api.mockResolvedValueOnce({ loginStatus: "Success" })
        const routerPush = jest.fn()
        const wrapper = mount(Login, {
            global: {
                plugins: [ store ],
                mocks: {
                    $router: { push: routerPush }
                }
            }
        })

        await wrapper.vm.handleLogin({ username: "user", password: "pass"})

        expect(api).toHaveBeenCalledTimes(1)
        expect(routerPush).toHaveBeenCalledTimes(1)
    })

    it('sets the stored username if login succeeds', async () => {
        api.mockResolvedValueOnce({ loginStatus: "Success" })
        const wrapper = mount(Login, {
            global: {
                plugins: [ store ],
                mocks: {
                    $router: { push: jest.fn() }
                }
            }
        })

        const username = "user"
        await wrapper.vm.handleLogin({ username: username, password: "pass"})

        expect(api).toHaveBeenCalledTimes(1)
        expect(store.state.username).toEqual(username)
    })

    it('displays register message if login fails', async () => {
        api.mockResolvedValueOnce({ loginStatus: "Fail" })
        const wrapper = mount(Login)

        await wrapper.vm.handleLogin({ username: "user", password: "pass"})

        expect(api).toHaveBeenCalledTimes(1)
        expect(wrapper.html()).toContain("Don't have an account?")
    })
})
