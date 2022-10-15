<template>
  <div class="container-fluid vh-100">
    <!-- <login-view isAuthenticated=false @isAuthenticated="updateIsAuthenticated" /> -->
    <router-view/>
  </div>
</template>


<script>
import LoginView from './views/LoginView.vue'
export default ({
  name: 'home',
  components: {
    LoginView
},
  data() {
    return {
      isAuthenticated : false
    }
  },
  methods: {
    redirectToLogin(){
      if (!this.isAuthenticated){
        this.$router.push('/login')
      }
    },
    updateIsAuthenticated(value){
      this.isAuthenticated = value
    }
  },
  mounted() {
    this.redirectToLogin()
  },
})
</script>


<style scoped>

</style>
