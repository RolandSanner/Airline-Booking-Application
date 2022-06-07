<template>
    <div>
        {{flight}}
    </div>
</template>

<script>
import AirlineService from '../services/AirlineService.js';
export default {
    data() {
        return {
            flight: {},
            flightNum : -1
        }
    }, 
    created() {
        this.flightNum = this.$route.params.num;
        AirlineService.getAFlight(this.flightNum).then(
            (response) => {
                this.flight = response.data;
            }
        );
    }
}
</script>

<style>

</style>