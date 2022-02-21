import { shallowMount } from '@vue/test-utils'
import RegisterComponent from "@/components/RegisterComponent";

describe('RegisterComponent', () => {
    it('displays labels', () => {
        const wrapper = shallowMount(RegisterComponent)

        const html = wrapper.html()
        expect(html).toContain("Username:")
        expect(html).toContain("Password:")
        expect(html).toContain("Name:")
        expect(html).toContain("Email:")
    })

    it('displays button', () => {
        const wrapper = shallowMount(RegisterComponent)

        expect(wrapper.html()).toContain("<button")
    })

    it('emits register event with correct payload', () => {
        const wrapper = shallowMount(RegisterComponent)
        const credentials = { username: "user", password: "pass", name: "name", email: "e@mail.com" }

        wrapper.find('[data-testid="userField"]').setValue(credentials.username)
        wrapper.find('[data-testid="passField"]').setValue(credentials.password)
        wrapper.find('[data-testid="nameField"]').setValue(credentials.name)
        wrapper.find('[data-testid="emailField"]').setValue(credentials.email)
        wrapper.trigger('submit')

        const calls = wrapper.emitted('register')
        expect(calls).toHaveLength(1)
        expect(calls[0][0]).toMatchObject(credentials)
    })
})
