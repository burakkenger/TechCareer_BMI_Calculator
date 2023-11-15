import axios from "axios";

const request_url = "http://localhost:8080/auth/api/"

class AuthenticationApi {

    static Login = (payload) => {
        return axios.post(request_url + "login", payload)
    }

    static Register = (payload) => {
        return axios.post(request_url + "register", payload);
    }
}

export default AuthenticationApi