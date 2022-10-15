<template>
  <div class="dashboard">
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
      token: "",
      cedula: "",
      password: "",
      isAuthenticated : false,
      error: false,
      errorMsg: "",
    }
  },
})
</script>


<style>

</style>
