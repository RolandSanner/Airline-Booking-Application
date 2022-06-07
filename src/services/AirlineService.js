import axios from 'axios';

export default {

    getAllFlights() {
        return axios.get("/flights")
    }, 

    getAFlight(id) {
        return axios.get(`/flight/${id}`)
    },

    bookFlight(bookingInfo) {
        return axios.post(`/reservation`, bookingInfo);
    }
}