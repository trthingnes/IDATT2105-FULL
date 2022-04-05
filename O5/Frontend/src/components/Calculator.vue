<template>
    <article id="calculator">
        <h1 class="number-display">{{ currNumber }}</h1>
        <section id="operator-group" class="button-group">
            <CalculatorButton :value="'CA'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'C'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'+'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'−'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'×'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'÷'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'='" @press="press"></CalculatorButton>
        </section>
        <section id="number-group" class="button-group">
            <CalculatorButton :value="'1'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'2'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'3'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'4'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'5'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'6'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'7'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'8'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'9'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'0'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'.'" @press="press"></CalculatorButton>
            <CalculatorButton :value="'←'" @press="press"></CalculatorButton>
        </section>
        <section id="log">
            <h5 v-for="entry in log" :key="log.indexOf(entry)">{{ entry }}</h5>
        </section>
    </article>
</template>

<script>
import CalculatorButton from "@/components/CalculatorButton.vue"
import { addCalculation, getCalculations } from "@/services/api"

export default {
    components: {
        CalculatorButton,
    },
    data() {
        return {
            currNumber: "0",
            prevNumber: null,
            operator: null,
            log: [],
        }
    },
    mounted() {
        getCalculations(this.$store.state.token).then((data) => {
            this.log = data.map(convert)
        })

        function convert(data) {
            let operator = ""
            switch (data.operator) {
                case "ADD": {
                    operator = "+"
                    break
                }
                case "SUBTRACT": {
                    operator = "-"
                    break
                }
                case "MULTIPLY": {
                    operator = "×"
                    break
                }
                case "DIVIDE": {
                    operator = "÷"
                    break
                }
            }

            return `${data.first} ${operator} ${data.second} = ${data.result}`
        }
    },
    methods: {
        press(value) {
            // If input is NaN, handle the button function.
            if (isNaN(value) && value !== ".") {
                this.handle(value)
                return
            }

            // If number is 0, replace it with number typed.
            if (this.currNumber === "0" || isNaN(this.currNumber)) {
                this.currNumber = ""
            }

            this.currNumber += value
        },
        handle(value) {
            switch (value) {
                case "C": {
                    this.currNumber = "0"
                    break
                }
                case "CA": {
                    this.prevNumber = null
                    this.currNumber = "0"
                    break
                }
                case "←": {
                    this.currNumber = this.currNumber.slice(0, -1)
                    if (!this.currNumber) {
                        this.currNumber = "0"
                    }
                    break
                }
                case "=": {
                    this.equals()
                    break
                }
                case "+":
                case "−":
                case "×":
                case "÷": {
                    this.prevNumber = this.currNumber
                    this.currNumber = value
                    this.operator = value
                    break
                }
                default: {
                    console.warn("Unrecognized option")
                    break
                }
            }
        },
        async equals() {
            let result = await addCalculation(
                this.$store.state.token,
                this.prevNumber,
                this.currNumber,
                this.operator
            )
            if (isNaN(result)) {
                this.currNumber = "Invalid"
                return
            }
            this.log.unshift(
                `${this.prevNumber} ${this.operator} ${this.currNumber} = ${result}`
            )
        },
    },
}
</script>

<style>
:root {
    --button-size: 60px;
    --border-round: 10px;
    --calculator-color: #ccc;
    --display-color: #777;
    --button-color: whitesmoke;
    --button-hover-color: #dde;
}
</style>

<style scoped>
#calculator {
    background-color: var(--calculator-color);
    border-radius: var(--border-round);
    max-width: 300px;
    height: fit-content;
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    margin: auto;
}

#calculator * {
    padding: 10px 20px;
}

.number-display {
    background-color: var(--display-color);
    border-radius: var(--border-round);
    color: white;
    margin: 10px;
}

.button-group {
    display: grid;
    grid-auto-flow: row;
    grid-template-columns: repeat(auto-fit, minmax(var(--button-size), 1fr));
    grid-template-rows: auto repeat(auto-fit, minmax(var(--button-size), 1fr));
    gap: 5px;
}

#log {
    overflow-x: scroll;
    white-space: nowrap;
    padding: 15px 5px;
    margin: 0 15px;
}

#log * {
    background-color: var(--button-color);
    width: fit-content;
    display: inline;
    padding: 5px;
    margin: 0 5px 0 5px;
    max-height: 1em;
}
</style>
