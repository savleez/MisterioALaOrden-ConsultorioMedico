<template>
  <div class="container-fluid">
    <h1>This is the dashboard</h1>
    <div class="row d-flex justify-content-center align-items-center">
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <form>
          <!-- Cedula input -->
          <div class="form-outline mb-3">
            <input v-model="cedulaPaciente" type="text" id="cedula" class="form-control form-control-lg"
              placeholder="Ingrese su cédula" />
            <label class="form-label" for="cedula">Cédula</label>
          </div>
          <div class="form-outline mb-3">
            <input v-model="nombrePaciente" type="text" id="nombres" class="form-control form-control-lg"
              placeholder="Ingrese sus nombres" />
            <label class="form-label" for="nombres">Nombres</label>
          </div>
          <div class="form-outline mb-3">
            <input v-model="apellidoPaciente" type="text" id="apellidos" class="form-control form-control-lg"
              placeholder="Ingrese sus apellidos" />
            <label class="form-label" for="apellidos">Apellidos</label>
          </div>
          <div class="form-outline mb-3">
            <input v-model="edadPaciente" type="date" id="edad" class="form-control form-control-lg"
              placeholder="Ingrese su edad" />
            <label class="form-label" for="edad">Edad</label>
          </div>
          <div class="form-outline mb-3">
            <input v-model="correoPaciente" type="email" id="email" class="form-control form-control-lg"
              placeholder="Ingrese su correo electrónico" />
            <label class="form-label" for="email">Correo electrónico</label>
          </div>
          <div class="form-outline mb-3">
            <input v-model="telefonoPaciente" type="text" id="telefono" class="form-control form-control-lg"
              placeholder="Ingrese su teléfono" />
            <label class="form-label" for="telefono">Teléfono</label>
          </div>
          <!-- Password input -->
          <div class="form-outline mb-2">
            <input v-model="password" type="text" id="password" class="form-control form-control-lg"
              placeholder="Ingrese su contraseña" />
            <label class="form-label" for="password">Contraseña</label>
          </div>
          <!-- Boton ingresar -->
          <!-- <div class="text-center text-lg-start mt-4 pt-2">
            <button type="submit" class="btn btn-primary btn-lg">Registrarse</button>
          </div> -->
        </form>
      </div>
    </div>
    <!-- footer -->

  </div>
</template>


<script>
export default ({
  name: 'dashboard',
  components: {

  },
  data() {
    return {
      cedulaPaciente: "",
      nombrePaciente: "",
      apellidoPaciente: "",
      edadPaciente: "",
      correoPaciente: "",
      telefonoPaciente: "",
      password: "",
      error: false,
      errorMsg: "",
    }
  },
  computed: {
    isAuthenticated() {
      return sessionStorage.getItem('isAuthenticated');
    },
    loggedUserCedula() {
      return sessionStorage.getItem('loggedUserCedula');
    },
    authToken() {
      return sessionStorage.getItem('authToken');
    },
  },
  methods: {
    checkIsAuthenticated() {
      if (!this.isAuthenticated) {
        this.$router.push('/')
      }
    },
    updateInfo() {
      this.checkIsAuthenticated();

      let pacientUrl = "http://localhost:8080/api/pacientes/";

      let userOptions = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      };

      fetch(pacientUrl, userOptions)
        .then(response => {
          if (!response.status == 200) {
            return Promise.reject("Usuarios no encontrados")
          }
          return response.json()
        })
        .then(data => {
          let usuarioCorrecto = data.filter( element => element.cedulaPaciente == this.loggedUserCedula)[0]
          let varDate = new Date(usuarioCorrecto.edadPaciente);
          this.cedulaPaciente = usuarioCorrecto.cedulaPaciente;
          this.nombrePaciente = usuarioCorrecto.nombrePaciente;
          this.apellidoPaciente = usuarioCorrecto.apellidoPaciente;
          this.edadPaciente = varDate.toISOString().substring(0, 10);
          this.correoPaciente = usuarioCorrecto.correoPaciente;
          this.telefonoPaciente = usuarioCorrecto.telefonoPaciente;
          this.password = usuarioCorrecto.usuario.password
        })
    }
  },
  mounted() {
    this.updateInfo()
  },
})
</script>


<style>

</style>
