import { mount } from "@vue/test-utils";
import { api } from "@/services/api";
import Login from "@/views/Login";

jest.mock('@/services/api')

describe('Login', () => {
    beforeEach(() => {
        jest.clearAllMocks()
    })

    /*it('moves to logged in view if api succeeds', async () => {
        api.mockResolvedValueOnce({ loginStatus: "Success" })
        const wrapper = mount(Login)

        await wrapper.vm.handleLogin({ username: "user", password: "pass"})

        expect(api).toHaveBeenCalledTimes(1)
    }) TODO: How do I check if the router routes? */

    it('displays register message if login fails', async () => {
        api.mockResolvedValueOnce({ loginStatus: "Fail" })
        const wrapper = mount(Login)

        await wrapper.vm.handleLogin({ username: "user", password: "pass"})

        expect(api).toHaveBeenCalledTimes(1)
        expect(wrapper.html()).toContain("Don't have an account?")
    })
})
