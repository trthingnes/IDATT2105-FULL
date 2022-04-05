import axios from "axios"

const API_URL = "http://localhost:8888"

export function register(username, password) {
    return axios
        .post(`${API_URL}/register`, {
            username: username,
            password: password,
        })
        .then((result) => {
            return result.data
        })
        .catch((reason) => {
            console.warn(reason)
        })
}

export function login(username, password) {
    return axios
        .post(`${API_URL}/token`, {
            username: username,
            password: password,
        })
        .then((result) => {
            return result.data
        })
        .catch((reason) => {
            console.warn(reason)
        })
}

export function addCalculation(token, firstNumber, secondNumber, operator) {
    return axios
        .post(
            `${API_URL}/calculation`,
            {
                first: firstNumber,
                second: secondNumber,
                operator: ["+", "−", "×", "÷"].indexOf(operator),
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        )
        .then((result) => {
            return result.data["result"]
        })
        .catch((reason) => {
            console.warn(reason)
        })
}

export function getCalculations(token) {
    return axios
        .get(`${API_URL}/calculation`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then((result) => {
            return result.data
        })
        .catch((reason) => {
            console.warn(reason)
        })
}
