<template>
  <div class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5"
    id="footer">
    <!-- Copyright -->
    <div class="text-white mb-3 mb-md-0">
        Copyright © 2022. Todos los derechos reservados.
    </div>
    <!-- Copyright -->
  </div>
</template>


<script>
defineProps({
  msg: {
    type: String,
    required: false
  }
})
</script>


<style scoped>
  #footer{
    background-color:#2274a5 !important;
  }
</style>
