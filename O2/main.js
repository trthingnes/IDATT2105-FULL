const app = Vue.createApp({
    data() {
        return {
            currNumber: "0",
            prevNumber: null,
            operator: null,
            log: [],
        }
    },
    methods: {
        press(value) {
            // If input is NaN, handle the button function.
            if(isNaN(value) && value != ".") {
                this.handle(value)
                return
            }

            // If number is 0, replace it with number typed.
            if(this.currNumber == "0" || isNaN(this.currNumber)) {
                this.currNumber = ""
            }

            this.currNumber += value
        },
        handle(value) {
            switch(value) {
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
                    if(!this.currNumber) {
                        this.currNumber = "0"
                    }
                    break
                }
                case "=": {
                    this.calculate()
                    break
                }
                case "+": case "−": case "×": case "÷": {
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
        calculate() {
            switch(this.operator) {
                case "+": {
                    let result = parseFloat(this.prevNumber) + parseFloat(this.currNumber)
                    this.log.unshift(this.prevNumber + " + " + this.currNumber + " = " + result)
                    this.currNumber = "= " + result
                    break
                }
                case "−": {
                    let result = parseFloat(this.prevNumber) - parseFloat(this.currNumber)
                    this.log.unshift(this.prevNumber + " − " + this.currNumber + " = " + result)
                    this.currNumber = "= " + result
                    break
                }
                case "×": {
                    let result = parseFloat(this.prevNumber) * parseFloat(this.currNumber)
                    this.log.unshift(this.prevNumber + " × " + this.currNumber + " = " + result)
                    this.currNumber = "= " + result
                    break
                }
                case "÷": {
                    let result = parseFloat(this.prevNumber) / parseFloat(this.currNumber)
                    this.log.unshift(this.prevNumber + " ÷ " + this.currNumber + " = " + result)
                    this.currNumber = "= " + result
                    break
                }
                default: {
                    console.warn("Unrecognized option")
                    break
                }
            }
        },
    }
})