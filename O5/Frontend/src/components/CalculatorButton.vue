<template>
    <button class="calculator-button" @click="click">{{ value }}</button>
</template>

<script>
export default {
    props: {
        value: {
            type: String,
            required: true,
        },
    },
    methods: {
        click() {
            this.$emit("press", this.value)
        },
    },
}
</script>

<style scoped>
.calculator-button {
    background-color: var(--button-color);
    border-radius: 50%;
    border: none;
    height: var(--button-size);
    width: var(--button-size);
    font-size: 1em;
    transition: 0.2s ease-in-out;
}

.calculator-button:hover {
    background-color: var(--button-hover-color);
}
</style>
