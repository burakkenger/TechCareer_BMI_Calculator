import axios from "axios";
import { error } from "jquery";

var request_url = "http://localhost:8080/user/api";

class UserApi {

    static GetByID = (key) => {
        const token = localStorage.getItem("token");
        try {
            return axios.get(request_url + "/search_id/" + key,);
        }
        catch (error) {
            console.log(error);
        }
    }

    static Update = (value) => {
        try {
            return axios.post(request_url + "/update", value);
        }
        catch (error) {
            console.log(error);
        }
    }
}
export default UserApi