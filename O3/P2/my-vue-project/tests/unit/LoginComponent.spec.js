import { shallowMount } from '@vue/test-utils'
import LoginComponent from '@/components/LoginComponent.vue'
import RegisterComponent from "@/components/RegisterComponent";

describe('LoginComponent', () => {
  it('displays labels', () => {
    const wrapper = shallowMount(LoginComponent)

    const html = wrapper.html()
    expect(html).toContain("Username:")
    expect(html).toContain("Password:")
  })

  it('displays button', () => {
    const wrapper = shallowMount(RegisterComponent)

    expect(wrapper.html()).toContain("<button")
  })

  it('emits api event with correct payload', () => {
    const wrapper = shallowMount(LoginComponent)
    const credentials = { username: "user", password: "pass" }

    wrapper.find('[data-testid="userField"]').setValue(credentials.username)
    wrapper.find('[data-testid="passField"]').setValue(credentials.password)
    wrapper.trigger('submit')

    const calls = wrapper.emitted('login')
    expect(calls).toHaveLength(1)
    expect(calls[0][0]).toMatchObject(credentials)
  })
})
