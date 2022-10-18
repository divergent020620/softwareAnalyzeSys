<template xmlns:option="http://java.sun.com/xml/ns/javaee">
  <div class="image-uploader">
<!--    <img :src="uploadIcon" class="icon"/>-->
    <p class = "showTitle">软件缺陷检测系统</p>
    <div class="real-btn">
      SELECT A FiLE
      <form content="multipart/form-data">
        <input type="file" id="testFile" name="testFile" @change="checkUpload" class="hide-btn">
      </form>

    </div>
    <div style="left: auto">
      <select name="Prefix" id="Prefix"  @change="chooseModel" class="optionShow">
        <option :value="option" v-for="option in optionArray" :key="option" >{{option}}</option>
      </select>
    </div>

    <div>
      <h1 class = "showFile">{{fileName}} </h1>
    </div>
    <div>
      <h2 class = "showTraining">{{training}}</h2>
    </div>
    <div>

      <el-button plain type="button" id="btn" @click="Upload" class="btnShow">提交</el-button>
      <el-button plain type="button" id="btn" @click="showHistory" class="btnShow">查看历史信息</el-button>
    </div>
  </div>

</template>

<script>
//import uploadIcon from '../assets/upload.png'

import axios from "axios";

export default {
  name: 'imageUpload',
  data () {
    return {
      //  uploadIcon: uploadIcon,
      fileObj:null,
      fileName:"暂无已选择文件",
      optionArray: ["随机森林-100棵树", "随机森林-1000棵树","随机森林-10000棵树","K-means", "KNN","逻辑回归","贝叶斯分类器","多模型综合评估"],
      selected: "随机森林-100棵树",
      model:"随机森林-100棵树",
      training:""
    }
  },
  methods:{

    checkUpload:function (){
      const btn = document.getElementById("btn");
      const testFile=document.getElementById("testFile");
      console.log(testFile.files);
      if (testFile.files.length == 0) {
        alert("请先至少选择一个文件！");
        return;
      }else{
        this.fileObj = event.target.files[0];
        this.fileName="已选择文件："+event.target.files[0].name;

      }
    },
    Upload(event) {
      const testFile=document.getElementById("testFile");
      if (testFile.files.length == 0) {
        this.$alert("请先至少选择一个文件！");
        return;
      }
      console.log("hahaha");
      let formData = new FormData();
      switch (this.model){
        case "随机森林-100棵树":
          this.model='rf100';
          break;
        case "随机森林-1000棵树":
          this.model='rf1000';
          break;
        case "随机森林-10000棵树":
          this.model='rf10000';
          break;
        case "K-means":
          this.model='kmeans';
          break;
        case "KNN":
          this.model='knn';
          break;
        case "逻辑回归":
          this.model='lr';
          break;
        case "贝叶斯分类器":
          this.model='bayes';
          break;
        case "多模型综合评估":
          this.model='multi';
          break;
      }

      formData.append('selected',this.model)
      formData.append('fileObj',this.fileObj);
      console.log(formData.get("fileObj"));
      this.training="模型正在评估...";
      axios.post('http://localhost:8088/upload',formData,{
        'Content-type' : 'multipart/form-data'
      }).then(res=>{
        // 上传成功后的处理

        console.log(res.data.data);
        if(res.data.data=="1"){
          this.training="模型评估完毕";
          this.$alert("代码存在缺陷！");
        }else{
          this.training="模型评估完毕";
          this.$alert("代码没有问题！");
        }

        /*//this.$confirm("Are you sure?").then(() => {
          //do something...
        });
        /*this.$prompt("Input your name").then(text => {
          // do somthing with text
        });*/

      },err=> {
        console.log(res.data);
        alert("后端接受失败！");
      })
    },
    chooseModel:function (){
      let chooseIndex=document.getElementById("Prefix");

      this.model=chooseIndex.value;
      console.log(this.model+'选中的index');
    },
    showHistory:function (){
      console.log(sessionStorage.getItem('userName'));
      window.open("http://localhost:8080/#/record", "_blank");


    },
  }

}
/*const btn = document.getElementById("btn");
const testFile=document.getElementById("testFile");

}*/
</script>

<style scoped>
.image-uploader {
  margin: 20px;
  border: 3px dashed #fff;
  background: #2196F3;
  padding: 10px;
  text-align: center;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: #fff;
  border-radius: 10px;
  font-weight: 500;
  position: absolute;
  box-sizing: border-box;
  width: 650px;
}
.real-btn {
  cursor: pointer;

  top: 0px;
  left: 30px;

  width: 50%;
  position: relative;
  padding: 10px 20px;
  background: #fff;
  border-radius: 10px;
  color: #2196F3;
}
.hide-btn {
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  position: absolute;
  opacity: 0;
}
.icon {
  width: 50px;
  height: 50px;
}
.showFile{
  height: 35px;

  text-indent: 0em;
  padding-top: 5px;
  font-size: 20px;
}
.optionShow{
  cursor: pointer;

  top: -40px;
  left: 170px;
  width: 30%;
  position: relative;
  padding: 10px 20px;
  background: #fff;
  border-radius: 10px;
}
.btnShow{
  background-color: #ffffff;
  color: #0084ff;

  width: 30%;
  border-radius: 10px;
  letter-spacing: 0.2cm;
}
.showTraining{
  height: 10px;
  color:red;
  text-indent: 0em;
  padding-top: 5px;
  font-size: 20px;
}
.showTitle{
  color: #ffffff;
  text-align: center;
  font-size: 30px;
  margin-top: 15px;
  font-weight:bold;
  letter-spacing: 0.5cm;
  border-bottom: 1px  hidden gray;
}
</style>
