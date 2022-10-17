<template>
    <div class="container-fluid">
      <div class="row d-flex justify-content-center align-items-center">
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          <form v-on:submit.prevent="register">
            <!-- Consultorio medico -->
            <div class="divider d-flex align-items-center my-4">
              <p class="text-center fs-3 fw-bold mx-3 mb-0">Consultorio Médico</p>
            </div>
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
              <input v-model="password" type="password" id="password" class="form-control form-control-lg"
                placeholder="Ingrese su contraseña" />
              <label class="form-label" for="password">Contraseña</label>
            </div>
            <div class="form-outline mb-2">
              <input v-model="password2" type="password" id="password2" class="form-control form-control-lg"
                placeholder="Confirme su contraseña" />
              <label class="form-label" for="password">Confirme su contraseña</label>
            </div>
            <!-- Crear cuenta -->
            <div class="d-flex justify-content-between align-items-center">
                <a href="http://localhost:5173/" class="text-body">¿Ya tienes una cuenta?</a>
            </div>
            <!-- Boton ingresar -->
            <div class="text-center text-lg-start mt-4 pt-2">
              <button type="submit" class="btn btn-primary btn-lg">Registrarse</button>
            </div>
          </form>
        </div>
      </div>
      <!-- footer -->
  
    </div>
</template>


<script>

export default ({
  name: 'register',
  components: {

  },
  data() {
    return {
        cedulaPaciente : "",
        nombrePaciente : "",
        apellidoPaciente : "",
        edadPaciente : "",
        correoPaciente : "",
        telefonoPaciente : "",
        password : "",
        password2 : ""
    }
  },
  computed: {
    authToken(){
        return sessionStorage.getItem('authToken');
    },
    isAuthenticated(){
        return sessionStorage.getItem('isAuthenticated')
    }
  },
  methods: {
    checkIsAuthenticated(){
      if(this.isAuthenticated){
        this.$router.push('/dashboard')
      }
    },
    register(){
        if (this.cedulaPaciente.length === 0 || this.nombrePaciente.length === 0
            || this.apellidoPaciente.length === 0 || this.edadPaciente.length === 0
            || this.correoPaciente.length === 0 || this.telefonoPaciente.length === 0){
                alert("Todos los campos son obligatorios.")
                return
            }

        if(!this.password === this.password2){
            alert("Las contraseñas no coinciden")
            return
        }

        let newUser = {
            cedula : this.cedulaPaciente,
            password : this.password
        }

        let newPacient = {
            cedulaPaciente : this.cedulaPaciente,
            nombrePaciente : this.nombrePaciente,
            apellidoPaciente : this.apellidoPaciente,
            edadPaciente : this.edadPaciente,
            correoPaciente : this.correoPaciente,
            telefonoPaciente : this.telefonoPaciente,
            usuario : {
                id : null
            }
        }

        let userUrl = "http://localhost:8080/api/usuarios/";
        let pacientUrl = "http://localhost:8080/api/pacientes/";

        let userOptions = {
            method: 'POST',
            body: JSON.stringify(newUser),
            headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + this.authToken
            }
        };

        fetch(userUrl, userOptions)
            .then( response => {
                if (!response.status == 201){
                    return Promise.reject("Usuario no creado")
                }
                return response.json()
            })
            .then( data => {
                newPacient.usuario.id = data.id;
                let pacientOptions = {
                    method: 'POST',
                    body: JSON.stringify(newPacient),
                    headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + this.authToken
                    }
                };

                fetch(pacientUrl, pacientOptions)
                    .then( response => {
                        if (!response.status == 201){
                            return Promise.reject("Paciente no creado");
                        }
                        return response.json()
                    })
                    .then( data => {
                        console.log("Usuario creado")
                        // console.log(data)
                        alert("Usuario creado con éxito. Por favor inicie sesión.")
                        this.$router.push('/')
                    })
                    .catch( (error) => {
                        alert("Usuario no creado, por favor intente otra vez.");
                        console.log(error);
                        return;
                    })
            })
            .catch( (error) => {
                alert("Usuario no creado, por favor intente otra vez.")
                console.log(error)
                return
            })


    }
  },
  mounted() {
    this.checkIsAuthenticated()
  },
})
</script>


<style>

</style>

