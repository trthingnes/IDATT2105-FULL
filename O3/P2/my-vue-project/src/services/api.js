import axios from "axios";

export function api(request) {
    const loginResponse = axios.post("http://localhost:8085/demo/login", request);
    console.log(loginResponse)

    return loginResponse.then((resolvedResult) => {
        return resolvedResult.data;
    });
}
