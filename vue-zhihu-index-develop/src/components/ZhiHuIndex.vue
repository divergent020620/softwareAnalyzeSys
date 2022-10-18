<template>
  <component :toggleComponent="toggleComponent" :is="typeName"></component>
</template>

<script>
export default {
  name: 'ZhiHuIndex',
  data () {
    return {
      typeName: 'Login'
    }
  },
  methods:{
    toggleComponent:function(){
      this.typeName=this.typeName==="Login"?"Register":"Login"
     // this.typeName=this.typeName==="Login"?"upload":"Login"
    },

  },
  components:{
    Login,
    Register
  }
}
import Login from "./Login"
import Register from "./Register"
import upload from "./upload";
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
