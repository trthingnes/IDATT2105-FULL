<template>
    <Calculator />
</template>

<script>
import Calculator from "@/components/Calculator"

export default {
    beforeMount() {
        if (!this.$store.state.token) {
            this.$router.push({ name: "login" })
        }
    },
    components: {
        Calculator,
    },
}
</script>

<style scoped>
body {
    background-color: whitesmoke;
}

h1 {
    text-align: center;
}
</style>
