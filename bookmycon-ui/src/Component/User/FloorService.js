import axios from 'axios'


const FLOOR_REST_API_URL = 'http://localhost:8080/api/floor';

class FloorService{

    getFloors(){
        return axios.get(FLOOR_REST_API_URL);
    }
}
export default new FloorService;