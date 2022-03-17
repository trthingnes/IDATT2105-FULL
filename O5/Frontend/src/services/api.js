import axios from "axios"

export function calculate(firstNumber, secondNumber, operator) {
  return axios
    .post("http://localhost:8888/calculate", {
      first: firstNumber,
      second: secondNumber,
      operator: ["+", "−", "×", "÷"].indexOf(operator),
    })
    .then((result) => {
      return result.data["result"]
    })
    .catch((reason) => {
      console.warn(reason)
    })
}
