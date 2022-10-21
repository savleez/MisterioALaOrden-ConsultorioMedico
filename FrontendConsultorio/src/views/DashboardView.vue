<template>
  <div class="container-fluid">
    <h1>This is the dashboard</h1>
    <div class="row d-flex justify-content-center align-items-center">
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <form @submit.prevent="updatePacient">
          <!-- Cedula input -->
          <div class="form-outline mb-3">
            <input v-model="cedulaPaciente" type="text" id="cedula" class="form-control form-control-lg"
              placeholder="Ingrese su cédula" readonly />
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
          <!-- <div class="form-outline mb-2">
            <input v-model="password" type="text" id="password" class="form-control form-control-lg"
              placeholder="Ingrese su contraseña" />
            <label class="form-label" for="password">Contraseña</label>
          </div> -->
          <!-- Boton ingresar -->
          <div class="text-center text-lg-start mt-4 pt-2">
            <button type="submit" class="btn btn-primary btn-lg">Actualizar</button>
          </div>
          <div class="text-center text-lg-start mt-4 pt-2">
            <button type="button" class="btn btn-danger btn-lg" @click="deletePatient">Eliminar cuenta</button>
          </div>
          <div class="text-center text-lg-start mt-4 pt-2">
            <button type="button" class="btn btn-secondary btn-lg" @click="closeSesion">Cerrar sesión</button>
          </div>
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
      idPaciente: "",
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
    loggedUserId() {
      return sessionStorage.getItem('loggedUserId');
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

      let pacientUrl = "http://132.145.192.40:8080/consultorio/api/pacientes/";

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
          let usuarioCorrecto = data.filter(element => element.cedulaPaciente == this.loggedUserCedula)[0]
          let varDate = new Date(usuarioCorrecto.edadPaciente);
          this.cedulaPaciente = usuarioCorrecto.cedulaPaciente;
          this.nombrePaciente = usuarioCorrecto.nombrePaciente;
          this.apellidoPaciente = usuarioCorrecto.apellidoPaciente;
          this.edadPaciente = varDate.toISOString().substring(0, 10);
          this.correoPaciente = usuarioCorrecto.correoPaciente;
          this.telefonoPaciente = usuarioCorrecto.telefonoPaciente;
          this.password = usuarioCorrecto.usuario.password;
          this.idPaciente = usuarioCorrecto.idPaciente;
        })
    },
    updatePacient() {
      if (this.cedulaPaciente.length === 0 || this.nombrePaciente.length === 0
        || this.apellidoPaciente.length === 0 || this.edadPaciente.length === 0
        || this.correoPaciente.length === 0 || this.telefonoPaciente.length === 0) {
        alert("Todos los campos son obligatorios.")
        return
      }

      let newPacient = {
        idPaciente: parseInt(this.idPaciente),
        cedulaPaciente: this.cedulaPaciente,
        nombrePaciente: this.nombrePaciente,
        apellidoPaciente: this.apellidoPaciente,
        correoPaciente: this.correoPaciente,
        telefonoPaciente: this.telefonoPaciente,
        edadPaciente: this.edadPaciente,
        usuario: {
          id: parseInt(this.loggedUserId)
        }
      }

      let pacientUrl = "http://132.145.192.40:8080/consultorio/api/pacientes/" + this.idPaciente;

      let userOptions = {
        method: 'PUT',
        body: JSON.stringify(newPacient),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      };

      fetch(pacientUrl, userOptions)
        .then(response => {
          if (!response.status == 200) {
            return Promise.reject("Usuario no actualizado")
          }
          return response.json()
        })
        .then(data => {
          // console.log(data)
          alert("Perfil actualizado")
        })
        .catch((error) => {
          alert("Usuario no actualizado, por favor intente otra vez.")
          console.log(error)
          return
        })
    },
    deletePatient() {
      let confirmacion = confirm('¿Está seguro de que desea eliminar su cuenta? Recuerde que no podrá recuperarla')
      if (confirmacion) {

        let pacientUrl = "http://132.145.192.40:8080/consultorio/api/pacientes/" + this.idPaciente;

        let patientOptions = {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.authToken
          }
        }

        fetch(pacientUrl, patientOptions)
          .then(response => {
            if (!response.status == 200) {
              return Promise.reject("Usuario no actualizado")
            }
            return
          })
          .then(() => {
            console.log('Paciente eliminado')
            let userUrl = "http://132.145.192.40:8080/consultorio/api/usuarios/" + this.loggedUserId;

            let userOptions = {
              method: 'DELETE',
              headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.authToken
              }
            }

            fetch(userUrl, userOptions)
              .then(response => {
                if (response.status == 200) {
                  alert('Su cuenta fue eliminada')
                  sessionStorage.removeItem('isAuthenticated')
                  sessionStorage.removeItem('loggedUserCedula')
                  sessionStorage.removeItem('loggedUserId')
                  this.$router.push('/')
                } else { alert('El proceso no pudo completarse') }
              })
          })
          .catch(error => console.log(error))
      }
    },
    closeSession() {
      sessionStorage.removeItem('isAuthenticated')
      sessionStorage.removeItem('loggedUserCedula')
      sessionStorage.removeItem('loggedUserId')
      this.$router.push('/')
    }
  },
  mounted() {
    this.updateInfo()
  },
})
</script>


<style>

</style>
