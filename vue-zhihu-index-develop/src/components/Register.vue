<template>
    <div class="register">
        <span class="zhihu-name">
            知乎
        </span>
        <div class="tip">注册知乎，发现更大的世界</div>
        <form>
            <div class="phone">


                <input type="text" @blur="onBlurCheck('phone')" :class="{'phone-input':true,error:errorMessage.phone.indexOf('不能为空')>-1}" :placeholder="errorMessage['phone']" v-model.number.trim="phone">
            </div>
            <div class="code">
              <input type="password" v-if="hidden" @blur="onBlurCheck('password')"
                     :class="{'phone-input':true,error:errorMessage.password.indexOf('不能为空')>-1}"
                     :placeholder="errorMessage['password']"  v-model.trim="password">
              <input type="text" v-else @blur="onBlurCheck('password')"
                     :class="{'phone-input':true,error:errorMessage.password.indexOf('不能为空')>-1}"
                     :placeholder="errorMessage['password']" v-model.trim="password">
              <i v-if="hidden" @click="toggleHiddenPassword" class="iconfont icon-yanjing-xiexian"></i>
              <i v-else @click="toggleHiddenPassword" class="iconfont icon-yanjing"></i>

            </div>

            <a href="javascript:" class="register-btn" @click="checkRegister">注册</a>
            <div class="register-org">
                <span>注册即代表同意《隐私政策》</span>
                </div>
        </form>
        <div class="bottom">
            <span>已有账号？<a href="javascript:" @click="toggleComponent" class="toggle-register-btn">登陆</a></span>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
  data() {
    return {
      msg: "zhihu",
      phone: "",
      password:"",
      code: "",
      hidden:true,
      errorMessage: {
        phone: "用户名",
        password: "密码"
      }
    };
  },
  methods: {
    onBlurCheck(name) {
      console.log(name, "check name");
      const value = this[name];
      if (!value || value.length < 1)
        return (this.errorMessage[name] = "不能为空");
      else this.errorMessage[name] = "";
    },
    checkRegister: function () {
      if(this.phone.length==0||this.password==0) {
        alert("用户名或密码不能为空！")
        return;
      }
      sessionStorage.setItem('userName',this.phone);
      axios.get(`http://localhost:8088/hello`, {params: {userName: this.phone, Password: this.password}}).then(res => {
        console.log(res.data)
        if (res.data == "0") {


          window.location.href = 'http://localhost:8080/#/upload';
          alert('注册成功！')
        } else {
          alert('用户名已存在！')
        }
      })
    },
    toggleHiddenPassword() {
      this.hidden = !this.hidden;
    },
  },

  props: ["toggleComponent"]
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
input {
  box-sizing: border-box;
  border: 0;
  outline: none;
}
.error::-webkit-input-placeholder {
  color: red;
}
a {
  text-decoration: none;
  outline: none;
}
a:hover,
a:visited,
a:link,
a:active {
  color: black;
}
.register {
  width: 432px;
  background: #ffffff;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-shadow: 0 1px 3px rgba(26, 26, 26, 0.1);
  border-radius: 2px;
  box-sizing: border-box;
}
.zhihu-name {
  font-size: 65px;
  color: #0084ff;
  text-align: center;
  display: block;
  margin-top: 30px;
}
.tip {
  color: #0084ff;
  text-align: center;
  font-size: 22px;
  margin-top: 15px;
}
form {
  padding: 0 40px 36px;
  margin-top: 30px;
}
.phone {
  height: 48px;
  border-bottom: 1px solid gray;
  padding-top: 5px;
}
#Prefix {
  width: 110px;
  color: black;
  height: 38px;
  line-height: 38px;
  font-size: 18px;
  text-align: left;
  box-sizing: border-box;
  border: 0;
  outline: none;
  float: left;
}
.sot-line {
  height: 22px;
  width: 1px;
  float: left;
  background: #ebebeb;
  margin: 12px 12px 0;
}
.phone-input {
  color: black;
  height: 38px;
  line-height: 38px;
  font-size: 14px;
  text-align: left;
  box-sizing: border-box;
  border: 0;
  outline: none;
  overflow: hidden;
}
.code-btn {
  color: #175199;
  width: 98px;
  line-height: 48px;
  margin-left: 5px;
  float: right;
  text-align: right;
}
.souce {
  color: #175199;
  line-height: 48px;
  text-align: right;
  font-size: 14px;
  cursor: pointer;
}
.code {
  height: 48px;
  padding-top: 5px;
  font-size: 14px;
  border-bottom: 1px solid gray;
}
.code-input {
  color: black;
  height: 48px;
  line-height: 48px;
  font-size: 14px;
  text-align: left;
  box-sizing: border-box;
  border: 0;
}
a.register-btn {
  background-color: #0084ff;
  color: #ffffff;
  margin-top: 14px;
  height: 36px;
  line-height: 36px;
  width: 100%;
  display: inline-block;
  text-align: center;
}
.register-org {
  width: 100%;
  margin-top: 18px;
  font-size: 14px;
}
.register-org span {
  color: #8590a6;
}
a.org-btn {
  color: #175199;
  float: right;
}
a.toggle-register-btn {
  color: #175199;
}
.bottom {
  text-align: center;
  height: 60px;
  line-height: 60px;
  background-color: #f6f6f6;
  border-top: 1px solid #ebebeb;
  font-size: 16px;
}
.icon-yanjing-xiexian,
.icon-yanjing {
  font-size: 24px;
  color: #999;
  float: right;
  line-height: 48px;

}
</style>
