<template>
  <h1>Contact</h1>
  <form @submit.prevent="submit">
    <section id="form">
      <div class="block">
        <label for="name">Name <span>{{ errors.name }}</span></label>
        <input v-model="name" id="name" type="text" />
      </div>

      <div class="block">
        <label for="email">E-mail <span>{{ errors.email }}</span></label>
        <input v-model="email" id="email" type="email" />
      </div>

      <div class="block">
        <label for="message">Message <span>{{ errors.message }}</span></label>
        <textarea v-model="message" id="message"></textarea>
      </div>
    </section>
    <div id="submit">
      <button :disabled="errors.name || errors.email || errors.message">
        Submit
      </button>
    </div>
  </form>
</template>

<script>
import { useField, useForm } from "vee-validate";
import { object, string } from "yup";

export default {
  setup() {
    const validationSchema = object({
      name: string().required("is required"),
      email: string().email("must be valid").required("is required"),
      message: string().required("is required"),
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

    const submit = handleSubmit((values) => {
      console.log("Submit", values);
    });

    return { name, email, message, submit, errors };
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
}

span {
  color: red;
}

#form {
  max-width: 500px;
  min-height: 120px;
  right: 0;
  left: 0;
  margin: 50px auto auto;
}

.block {
  margin-bottom: 10px;
}

#message {
  max-width: 200px;
  max-height: 50px;
}

label {
  padding: 10px;
}

input,
textarea {
  float: right;
  width: 180px;
}

#submit {
  text-align: center;
}
</style>
