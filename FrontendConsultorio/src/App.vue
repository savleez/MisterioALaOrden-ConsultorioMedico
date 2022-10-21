<template>
  <div class="container-fluid">
    <!-- LOGIN -->
    <div class="row d-flex justify-content-center align-items-center" v-if="!isAuthenticated && menu === 'login'">
      <div class="col-md-9 col-lg-6 col-xl-5 d-flex justify-content-center">
        <img src="./assets/logo.png" class="img-fluid" alt="Logo consultorio" style="max-width: 400px;">
      </div>
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <form @submit.prevent="login">
          <!-- Consultorio medico -->
          <div class="divider d-flex align-items-center my-4">
            <p class="text-center fs-3 fw-bold mx-3 mb-0">Consultorio Médico</p>
          </div>
          <!-- Cedula input -->
          <div class="form-outline mb-3">
            <input v-model="cedulaLogin" type="text" id="cedulaLogin" class="form-control form-control-lg"
              placeholder="Ingrese su cédula" />
            <label class="form-label" for="cedulaLogin">Cédula</label>
          </div>
          <!-- Password input -->
          <div class="form-outline mb-2">
            <input v-model="passwordLogin" type="password" id="password" class="form-control form-control-lg"
              placeholder="Ingrese su contraseña" />
            <label class="form-label" for="password">Contraseña</label>
          </div>
          <!-- Crear cuenta -->
          <div class="d-flex justify-content-between align-items-center">
            <a href="#" class="text-body" @click="updateMenu">¿No tienes una cuenta?</a>
          </div>
          <!-- Boton ingresar -->
          <div class="text-center text-lg-start mt-4 pt-2">
            <button type="submit" class="btn btn-primary btn-lg">Ingresar</button>
          </div>
        </form>
      </div>
    </div>
    <!-- PERFIL -->
    <div class="container-fluid" v-if="isAuthenticated">
      <h1>Perfil del paciente</h1>
      <div class="row d-flex justify-content-center align-items-center">
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          <form>
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
            <!-- Boton ingresar -->
            <div class="text-center text-lg-start mt-4 pt-2">
              <button type="button" class="btn btn-primary btn-lg m-2" @click="updatePacient">Actualizar</button>
              <button type="button" class="btn btn-secondary btn-lg m-2" @click="closeSession">Cerrar sesión</button>
              <button type="button" class="btn btn-danger btn-lg m-2" @click="deletePatient">Eliminar cuenta</button>
            </div>
            <div class="text-center text-lg-start mt-4 pt-2">
            </div>
            <div class="text-center text-lg-start mt-4 pt-2">
            </div>
          </form>
        </div>
      </div>
      <!-- footer -->

    </div>
    <!-- REGISTRAR -->
    <div class="container-fluid" v-if="!isAuthenticated && menu === 'register'">
      <h1>Registro de nuevo paciente</h1>
      <div class="row d-flex justify-content-center align-items-center">
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          <form>
            <!-- Consultorio medico -->
            <div class="divider d-flex align-items-center my-4">
              <p class="text-center fs-3 fw-bold mx-3 mb-0">Consultorio Médico</p>
            </div>
            <!-- Cedula input -->
            <div class="form-outline mb-3">
              <input v-model="newCedulaPaciente" type="text" id="cedulaRegister" class="form-control form-control-lg"
                placeholder="Ingrese su cédula" />
              <label class="form-label" for="cedulaRegister">Cédula</label>
            </div>
            <div class="form-outline mb-3">
              <input v-model="newNombrePaciente" type="text" id="nombresRegister" class="form-control form-control-lg"
                placeholder="Ingrese sus nombres" />
              <label class="form-label" for="nombresRegister">Nombres</label>
            </div>
            <div class="form-outline mb-3">
              <input v-model="newApellidoPaciente" type="text" id="apellidosRegister"
                class="form-control form-control-lg" placeholder="Ingrese sus apellidos" />
              <label class="form-label" for="apellidosRegister">Apellidos</label>
            </div>
            <div class="form-outline mb-3">
              <input v-model="newEdadPaciente" type="date" id="edadRegister" class="form-control form-control-lg"
                placeholder="Ingrese su edad" />
              <label class="form-label" for="edadRegister">Edad</label>
            </div>
            <div class="form-outline mb-3">
              <input v-model="newCorreoPaciente" type="email" id="emailRegister" class="form-control form-control-lg"
                placeholder="Ingrese su correo electrónico" />
              <label class="form-label" for="emailRegister">Correo electrónico</label>
            </div>
            <div class="form-outline mb-3">
              <input v-model="newTelefonoPaciente" type="text" id="telefonoRegister"
                class="form-control form-control-lg" placeholder="Ingrese su teléfono" />
              <label class="form-label" for="telefonoRegister">Teléfono</label>
            </div>
            <!-- Password input -->
            <div class="form-outline mb-2">
              <input v-model="newPassword" type="password" id="passwordRegister" class="form-control form-control-lg"
                placeholder="Ingrese su contraseña" />
              <label class="form-label" for="passwordRegister">Contraseña</label>
            </div>
            <div class="form-outline mb-2">
              <input v-model="newPassword2" type="password" id="password2Register" class="form-control form-control-lg"
                placeholder="Confirme su contraseña" />
              <label class="form-label" for="password2Register">Confirme su contraseña</label>
            </div>
            <!-- Crear cuenta -->
            <div class="d-flex justify-content-between align-items-center">
              <a href="#" class="text-body" @click="updateMenu">¿Ya tienes una cuenta?</a>
            </div>
            <!-- Boton ingresar -->
            <div class="text-center text-lg-start mt-4 pt-2">
              <button type="button" class="btn btn-primary btn-lg" @click="register">Registrarse</button>
            </div>
          </form>
        </div>
      </div>
      <!-- footer -->

    </div>
  </div>
</template>

<script>
export default ({
  name: 'home',
  components: {

  },
  data() {
    return {
      cedulaLogin: "",
      passwordLogin: "",
      idPaciente: "",
      cedulaPaciente: "",
      nombrePaciente: "",
      apellidoPaciente: "",
      edadPaciente: "",
      correoPaciente: "",
      telefonoPaciente: "",
      newCedulaPaciente: "",
      newNombrePaciente: "",
      newApellidoPaciente: "",
      newEdadPaciente: "",
      newCorreoPaciente: "",
      newTelefonoPaciente: "",
      newPassword: "",
      newPassword2: "",
      error: false,
      errorMsg: "",
      isAuthenticated: false,
      loggedUserCedula: '',
      loggedUserId: '',
      menu: 'login'
    }
  },
  computed: {
    authToken() {
      return sessionStorage.getItem("authToken");
    },
  },
  methods: {
    updateIsAuthenticated() {
      this.isAuthenticated = sessionStorage.getItem("isAuthenticated");
    },
    updateLoggedUserCedula(){
      this.loggedUserCedula = sessionStorage.getItem('loggedUserCedula');
    },
    updateLoggedUserId(){
      this.loggedUserId = sessionStorage.getItem('loggedUserId');
    },
    getAuthToken() {

      let options = {
        method: 'POST',
        body: JSON.stringify({ id: 1 }),
        headers: {
          'Content-Type': 'application/json'
        }
      };

      fetch('http://132.145.192.40:8080/consultorio/api/auth', options)
        .then(response => response.json())
        .then((data) => {
          sessionStorage.setItem('authToken', data.access);
        })
        .catch((error) => {
          this.error = true;
          this.errorMsg = error;
          console.log(error)
          throw error;
        })
    },
    login() {
      let userData = {
        cedulaLogin: this.cedulaLogin,
        passwordLogin: this.passwordLogin
      }

      if (userData.cedulaLogin.length == 0 || userData.passwordLogin.length == 0) {
        alert("Ingrese datos válidos.");
        return
      }

      this.cedulaLogin = "";
      this.passwordLogin = "";

      let userUrl = "http://132.145.192.40:8080/consultorio/api/usuarios/";

      let userOptions = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      };

      fetch(userUrl, userOptions)
        .then(response => response.json())
        .then((data) => {
          let usuarioCorrecto = data.filter((element) => {
            return element.cedula === userData.cedulaLogin && element.password === userData.passwordLogin;
          })[0]
          if (usuarioCorrecto) {
            sessionStorage.setItem('isAuthenticated', true);
            this.updateIsAuthenticated();
            sessionStorage.setItem('loggedUserCedula', usuarioCorrecto.cedula);
            this.updateLoggedUserCedula();
            sessionStorage.setItem('loggedUserId', usuarioCorrecto.id);
            this.updateLoggedUserId();
            alert("Inicio de sesión autorizado");
            this.updateInfo();
          } else {
            alert("Credenciales no válidas")
          }
        })
        .catch((error) => {
          console.log(error)
        })

    },
    updateInfo() {

      let patientUrl = "http://132.145.192.40:8080/consultorio/api/pacientes/";

      let patientOptions = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      };

      fetch(patientUrl, patientOptions)
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

      let newPatient = {
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

      let patientUrl = "http://132.145.192.40:8080/consultorio/api/pacientes/" + this.idPaciente;

      let patientOptions = {
        method: 'PUT',
        body: JSON.stringify(newPatient),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      };

      fetch(patientUrl, patientOptions)
        .then(response => {
          if (!response.status == 200) {
            return Promise.reject("Usuario no actualizado")
          }
          return response.json()
        })
        .then(() => {
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
      if (!confirmacion) {
        return
      }

      let patientUrl = "http://132.145.192.40:8080/consultorio/api/pacientes/" + this.idPaciente;

      let patientOptions = {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      }

      fetch(patientUrl, patientOptions)
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
                this.isAuthenticated = false;
                sessionStorage.removeItem('loggedUserCedula')
                this.loggedUserCedula = '';
                sessionStorage.removeItem('loggedUserId')
                this.loggedUserId = '';
                this.clearFields();
              } else {
                alert('El proceso no pudo completarse')
              }
            })
        })
        .catch(error => console.log(error))
    },
    register() {
      if (this.newCedulaPaciente.length === 0 || this.newNombrePaciente.length === 0
        || this.newApellidoPaciente.length === 0 || this.newEdadPaciente.length === 0
        || this.newCorreoPaciente.length === 0 || this.newTelefonoPaciente.length === 0) {
        alert("Todos los campos son obligatorios.")
        return
      }

      if (!this.newPassword === this.newPassword2) {
        alert("Las contraseñas no coinciden")
        return
      }

      let newUser = {
        cedula: this.newCedulaPaciente,
        password: this.newPassword
      }

      let newPatient = {
        cedulaPaciente: this.newCedulaPaciente,
        nombrePaciente: this.newNombrePaciente,
        apellidoPaciente: this.newApellidoPaciente,
        edadPaciente: this.newEdadPaciente,
        correoPaciente: this.newCorreoPaciente,
        telefonoPaciente: this.newTelefonoPaciente,
        usuario: {
          id: null
        }
      }

      let userUrl = "http://132.145.192.40:8080/consultorio/api/usuarios/";
      let patientUrl = "http://132.145.192.40:8080/consultorio/api/pacientes/";

      let userOptions = {
        method: 'POST',
        body: JSON.stringify(newUser),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authToken
        }
      };

      fetch(userUrl, userOptions)
        .then(response => {
          if (!response.status == 201) {
            return Promise.reject("Usuario no creado")
          }
          return response.json()
        })
        .then( data => {
          newPatient.usuario.id = data.id;
          let patientOptions = {
            method: 'POST',
            body: JSON.stringify(newPatient),
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + this.authToken
            }
          };

          fetch(patientUrl, patientOptions)
            .then(response => {
              if (!response.status == 201) {
                return Promise.reject("Paciente no creado");
              }
              return
            })
            .then( () => {
              console.log("Usuario creado")
              alert("Usuario creado con éxito. Por favor inicie sesión.")
              this.clearFields();
              this.menu = 'login'
            })
            .catch((error) => {
              alert("Usuario no creado, por favor intente otra vez.");
              console.log(error);
              return;
            })
        })
        .catch((error) => {
          alert("Usuario no creado, por favor intente otra vez.")
          console.log(error)
          return
        })


    },
    closeSession() {
      sessionStorage.removeItem('isAuthenticated');
      this.isAuthenticated = false;
      sessionStorage.removeItem('loggedUserCedula');
      this.loggedUserCedula = false;
      sessionStorage.removeItem('loggedUserId');
      this.loggedUserId = '';
      alert("Sesión finalizada con éxito");
      this.clearFields();
    },
    clearFields() {
      this.cedulaPaciente = '';
      this.nombrePaciente = '';
      this.apellidoPaciente = '';
      this.edadPaciente = '';
      this.correoPaciente = '';
      this.telefonoPaciente = '';
      this.idPaciente = '';
      this.newCedulaPaciente = '';
      this.newNombrePaciente = '';
      this.newApellidoPaciente = '';
      this.newEdadPaciente = '';
      this.newCorreoPaciente = '';
      this.newTelefonoPaciente = '';
      this.idPaciente = '';
      this.newPassword = '',
      this.newPassword2 = ''
    },
    updateMenu() {
      if (this.menu === 'login'){
        this.menu = 'register'
      } else {
        this.menu = 'login'
      }
    }
  },
  mounted() {
    this.getAuthToken()
  }
})
</script>


<style scoped>

</style>
