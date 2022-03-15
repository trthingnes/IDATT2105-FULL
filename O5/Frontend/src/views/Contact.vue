<template>
  <div class="container content mt-6">
    <div class="has-text-centered">
      <h1>Want to reach out to us?</h1>
      <strong>Fill out the form below!</strong>
    </div>
    <div v-if="!submitted" class="columns">
      <div class="column"></div>
      <div class="column is-half">
        <form @submit.prevent="submit">
          <div class="field">
            <label for="name" class="label">Name</label>
            <div class="control has-icons-left">
              <input
                v-model="name"
                id="name"
                type="text"
                class="input"
                :class="{ 'is-danger': errors.name }"
              />
              <span class="icon is-small is-left">
                <em class="fa fa-user"></em>
              </span>
            </div>
            <p v-if="errors.name" class="help is-danger">
              {{ errors.name.replace(/^\w/, (c) => c.toUpperCase()) }}
            </p>
          </div>
          <div class="field">
            <label for="email" class="label">E-mail</label>
            <div class="control has-icons-left">
              <input
                v-model="email"
                id="email"
                type="email"
                class="input"
                :class="{ 'is-danger': errors.email }"
              />
              <span class="icon is-small is-left">
                <em class="fa fa-at"></em>
              </span>
            </div>
            <p v-if="errors.email" class="help is-danger">
              {{ errors.email.replace(/^\w/, (c) => c.toUpperCase()) }}
            </p>
          </div>
          <div class="field">
            <label for="message" class="label">Message</label>
            <div class="control has-icons-left">
              <textarea
                v-model="message"
                id="message"
                class="input"
                :class="{ 'is-danger': errors.message }"
              />
              <span class="icon is-small is-left">
                <em class="fa fa-envelope"></em>
              </span>
            </div>
            <p v-if="errors.message" class="help is-danger">
              {{ errors.message.replace(/^\w/, (c) => c.toUpperCase()) }}
            </p>
          </div>
          <div class="field">
            <div class="control">
              <button
                :disabled="errors.name || errors.email || errors.message"
                :class="{ 'is-loading': submitting }"
                class="button is-link mt-3"
              >
                Submit
              </button>
            </div>
          </div>
        </form>
      </div>
      <div class="column"></div>
    </div>
    <div v-if="submitted" class="mt-6">
      <div class="has-text-centered">
        <h1 class="fa fa-check has-text-success"></h1>
        <h2 class="mt-0">Success! Your message has been sent!</h2>
      </div>
    </div>
  </div>
</template>

<script>
import { useField, useForm } from "vee-validate";
import { object, string } from "yup";
import { ref } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const validationSchema = object({
      name: string().required(),
      email: string().email().required(),
      message: string().required(),
    });
    const { handleSubmit, errors } = useForm({
      validationSchema,
      initialValues: {
        name: "",
        email: "",
        message: "",
      },
    });
    const { value: name } = useField("name");
    const { value: email } = useField("email");
    const { value: message } = useField("message");

    const store = useStore();
    name.value = store.state.name;
    email.value = store.state.email;

    const submitting = ref(false);
    const submitted = ref(false);

    const submit = handleSubmit((values) => {
      // Save name and email to vuex.
      store.commit("SET_NAME", name);
      store.commit("SET_EMAIL", email);

      console.log("Submit", values);
      submitting.value = true;
      setTimeout(() => {
        submitted.value = true;
      }, 1500);
    });

    return { name, email, message, submit, submitting, submitted, errors };
  },
};
</script>
