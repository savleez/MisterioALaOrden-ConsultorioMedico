<template>
  <div class="dashboard" >
    <h1>This is the dashboard</h1>
  </div>
</template>


<script>
export default ({
  name: 'dashboard',
  components: {

  },
  data() {
    return {
      authToken: "",
      error: false,
      errorMsg: "",
    }
  },
  computed: {
    isAuthenticated() {
      return sessionStorage.getItem('isAuthenticated');
    }
  },
  methods: {
    checkIsAuthenticated(){
      if(!this.isAuthenticated){
        this.$router.push('/')
      }
    }
  },
  mounted() {
    this.checkIsAuthenticated();
  },
})
</script>


<style>

</style>
