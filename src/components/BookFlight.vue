<template>
    <div>{{reservation}}
        <form v-on:submit.prevent = 'bookReservation'>
            Passenger Name: <input type='text' v-model='reservation.name'/><br>
            Select Flight:<br>

          <select  multiple v-model='reservation.flights'>  
            <option v-for="flight in flights" v-bind:key="flight.flightId" v-bind:value="flight.flightId">
              {{flight.flightId}}--{{flight.scheduledDeparture}}-{{flight.scheduledArrival}}-{{flight.departureAirport}}--{{flight.arrivalAirport}}
            </option>
          </select>
        
          <button>Submit</button>
        </form>
    </div>
</template>

<script>
import AirlineService from '../services/AirlineService.js';

export default {
    data() {
        return {
            flights: [],
            reservation : {
                name: '',
                flights: []
            }
        }
    },
    created() {
      AirlineService.getAllFlights().then(
      (response) => {
        this.flights = response.data;
      }
    )
    },
    methods: {
        bookReservation() {
            AirlineService.bookFlight(this.reservation).then(
                (response) => {
                    if(response.status === 200) {
                        window.alert("Reservation Made!");
                        this.$router.push("/");
                    }
                }
            )
        }
    }
}
</script>

<style>

</style>