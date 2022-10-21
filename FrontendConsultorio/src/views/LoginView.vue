<template>
  <div class="container-fluid">
    <div class="row d-flex justify-content-center align-items-center">
      <div class="col-md-9 col-lg-6 col-xl-5 d-flex justify-content-center">
        <img src="../assets/logo.png" class="img-fluid" alt="Logo consultorio" style="max-width: 400px;">
      </div>
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <form @submit.prevent="login">
          <!-- Consultorio medico -->
          <div class="divider d-flex align-items-center my-4">
            <p class="text-center fs-3 fw-bold mx-3 mb-0">Consultorio Médico</p>
          </div>
          <!-- Cedula input -->
          <div class="form-outline mb-3">
            <input v-model="cedula" type="text" id="cedula" class="form-control form-control-lg"
              placeholder="Ingrese su cédula" />
            <label class="form-label" for="cedula">Cédula</label>
          </div>
          <!-- Password input -->
          <div class="form-outline mb-2">
            <input v-model="password" type="password" id="password" class="form-control form-control-lg"
              placeholder="Ingrese su contraseña" />
            <label class="form-label" for="password">Contraseña</label>
          </div>
          <!-- Crear cuenta -->
          <div class="d-flex justify-content-between align-items-center">
              <a href="http://localhost:5173/register" class="text-body">¿No tienes una cuenta?</a>
          </div>
          <!-- Boton ingresar -->
          <div class="text-center text-lg-start mt-4 pt-2">
            <button type="submit" class="btn btn-primary btn-lg">Ingresar</button>
          </div>
        </form>
      </div>
    </div>
    <!-- footer -->

  </div>
</template>

<script>
export default ({
  name: 'login',
  components: {

  },
  data() {
    return {
      authToken: "",
      cedula: "",
      password: "",
      error: false,
      errorMsg: "",
    }
  },
  computed: {
    isAuthenticated(){
      return sessionStorage.getItem("isAuthenticated");
    }
  },
  methods: {
    getAuthToken() {

      if (this.isAuthenticated){
        this.$router.push('/dashboard')
        return
      }

      let options = {
        method: 'POST',
        body: JSON.stringify({ id: 1 }),
        headers: {
        'Content-Type': 'application/json'
        }
      };

      fetch('http://132.145.192.40:8080/consultorio/api/auth', options)
        .then( response => response.json())
        .then( (data) => {
          this.authToken = data.access;
          sessionStorage.setItem('authToken', this.authToken)
        })
        .catch( (error) => {
          this.error = true;
          this.errorMsg = error;
          console.log(error)
          throw error;
        })
    },
    login() {
      let userData = {
        cedula: this.cedula,
        password: this.password
      }

      if (userData.cedula.length == 0 || userData.password.length == 0 ){
        alert("Ingrese datos válidos.");
        return
      }

      this.cedula = "";
      this.password = "";

      let urlUsuarios = "http://132.145.192.40:8080/consultorio/api/usuarios/";

      let options = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      };

      fetch(urlUsuarios, options)
      .then( response => response.json())
      .then( (data) => {
        let usuarioCorrecto = data.filter( (element) => {
          return element.cedula === userData.cedula && element.password === userData.password;
        })[0]
        if (usuarioCorrecto){
          sessionStorage.setItem('isAuthenticated', true);
          sessionStorage.setItem('loggedUserCedula', usuarioCorrecto.cedula)
          sessionStorage.setItem('loggedUserId', usuarioCorrecto.id)
          this.$router.push('/dashboard')
        } else {
          alert("Credenciales no válidas")
        }
      })
      .catch( (error) => {
        console.log(error)
      })
      
    },
  },
  mounted () {
    this.getAuthToken()
  }
})
</script>

<style>
/* .container-fluid{
    background-color:#e9f1f7;
    margin: 0px;
  } */

.divider:after,
.divider:before {
  content: "";
  flex: 1;
  height: 1px;
  background: #000;
}

.btn-primary {
  padding-left: 2.5rem;
  padding-right: 2.5rem;
  background-color: #2274a5;
  border: #2274a5 solid 1px;
}



.h-custom {
  height: calc(100% - 73px);
}
</style>