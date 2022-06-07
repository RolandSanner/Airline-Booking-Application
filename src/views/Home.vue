<template>
  <div class="home">
    <h1>Welcome to the Airline Booking System</h1>
    
    <table>
        <tr v-for='flight in flights' v-bind:key='flight.flightId'>
            <td><router-link v-bind:to="{name:'flight-info', params: {num: flight.flightId}}">{{flight.flightId}}</router-link></td>
            <td>{{flight.departureAirport}}</td>
            <td>{{flight.arrivalAirport}}</td>
        </tr>
    </table>

  </div>
</template>

<script>
import AirlineService from '../services/AirlineService.js';
export default {
  name: "home",
  data() {
    return {
      flights: []
    }
  },
  created() {
    AirlineService.getAllFlights().then(
      (response) => {
        this.flights = response.data;
      }
    );
  }
};
</script>
