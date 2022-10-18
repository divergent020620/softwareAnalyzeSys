<template>
  <div id="app">
    <router-link to="/login"></router-link>
    <router-link to="/upload"></router-link>
    <router-link to="/record"></router-link>
    <router-view></router-view>

  </div>
</template>

<script>


export default {
  name: 'App',
  components: {
  }
}
</script>

<style>
body {

  width:100vh;
  height: 100%;
}
</style>
