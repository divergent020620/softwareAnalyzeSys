import VueRouter from "vue-router";
import Vue from "vue";
import upload from "../components/upload";
import ZhiHuIndex from "../components/ZhiHuIndex";
import record from "../components/record";
Vue.use(VueRouter)

const router = new VueRouter({
  routes:[
    {path:"/",redirect:'/login'},
    {path:'/login',component:ZhiHuIndex},
    {path: '/upload',component:upload},
    {path:'/record',component:record},
  ]
})
router.beforeEach((to, from,next)=> { //from中包含跳转前的路由信息，to包含跳转到的目的路由信息
  console.log("拦截器信息")
  console.log(sessionStorage.getItem('userName'))
  console.log(from.fullPath)
  console.log(to.fullPath)

  /*if(to.fullPath=="/upload"&&(sessionStorage.getItem('userName').length<=0||from.fullPath!="/login")){
    console.log("进来了")
    next({path:'/login'}) ;
   }*/
  if(to.fullPath=="/upload"&&sessionStorage.getItem('userName')!=null&&from.fullPath=="/login"){
    //console.log("进来了1")
    next() ;
  }else if(sessionStorage.getItem('userName')==null&&to.fullPath=="/upload"){
   // console.log("进来了2")
    next({path:'/login'})
  }else if(to.fullPath=="/record"&&sessionStorage.getItem('userName')!=null&&from.fullPath=="/upload") {
    next();
  } else if(sessionStorage.getItem('userName')==null&&to.fullPath=="/record") {
    next({path:'/login'});
  }else {

    next()
  }
})

export default router
