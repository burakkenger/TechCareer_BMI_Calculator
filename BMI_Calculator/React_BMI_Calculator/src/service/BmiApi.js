import axios from "axios";

const request_url = "http://localhost:8080/bmi/api"

class BmiApi {
    static Add = (payload) => {
        try {
            return axios.post(request_url + "/add", payload);
        }
        catch (error) {
            console.log(error);
        }
    }

    static Get = () => {
        try {
            return axios.get(request_url + "/get_by_all_user");
        }
        catch (error) {
            console.log(error);
        }
    }

    static Delete = (key) => {
        try {
            return axios.delete(request_url + "/delete/" + key);
        }
        catch (error) {
            console.log(error);
        }
    }
}

export default BmiApi