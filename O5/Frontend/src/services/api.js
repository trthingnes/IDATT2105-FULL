import axios from "axios";

export function calculate(firstNumber, secondNumber, operator) {
  return axios
    .post("http://localhost:8888/calculate", {
      firstNumber: firstNumber,
      secondNumber: secondNumber,
      operator: operator,
    })
    .then((result) => {
      return result.data["result"];
    })
    .catch((reason) => {
      console.warn(reason);
    });
}
