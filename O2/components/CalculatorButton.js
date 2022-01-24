app.component("calculator-button", {
    props: {
        value: {
            type: String,
            required: true,
        },
    },
    template: /*html*/
    `<button class="calculator-button" @click="click">{{ value }}</button>`,
    methods: {
        click() {
            this.$emit("press", this.value)
        },
    },
})