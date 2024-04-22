<template>
    <div ref="vantaRef" style="width:100%;height:100vh;overflow: auto">
  
      <div style=" box-shadow: 7px 7px 4px rgba(155,151,151,0.54); margin: 200px 60%; background-color: #fff; width: 350px; height: 280px; padding: 20px; border-radius: 5px">
        <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
        <el-form :model="user" :rules="rules" ref="userForm">
          <el-form-item prop="username">
            <el-input size="medium"  prefix-icon="el-icon-user" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input size="medium" prefix-icon="el-icon-lock" show-password
                      v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item style="margin: 10px 0; text-align: right">
            <el-button type="warning" size="small" autocomplete="off" >注册</el-button>
            <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
  
      <div class="my_title">DrugLLM</div>
      <div class="my_title2">基于大语言模型的药物分子性质分析平台</div>
    </div>
  </template>
  
  <script>
  
  import * as THREE from 'three'
  import BIRDS from 'vanta/src/vanta.birds'
  
  
  
  export default {
      name: "Login",
      data() {
          return {
              user: {},
              rules: {
            username: [
              { required: true, message: '请输入用户名', trigger: 'blur' },
              { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
            ],
            password: [
              { required: true, message: '请输入密码', trigger: 'blur' },
              { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
            ],
          }
      }
  },
       mounted() {
       this.vantaEffect = BIRDS({
        el: this.$refs.vantaRef,
        THREE: THREE
       })
      },
      beforeDestroy() {
        if (this.vantaEffect) {
        this.vantaEffect.destroy()
      }
     },
      methods: {
          login() {
            this.$refs['userForm'].validate((valid) => {
            if (valid) {   //表单校验合法
              this.request.post("/user/login", this.user).then(res => {
                  if(res.code === '200'){
                      localStorage.setItem("user", JSON.stringify(res.data))  //存储用户信息到浏览器
                      this.$router.push("/front/home")
                      this.$message.success("登录成功")
                  }else{
                      this.$message.error(res.msg)
                  }
              })
            }
          });
          }
      }
  }
  </script>
  
  <style>
  .wrapper {
      height: 100vh;
      background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
      overflow: hidden;
  }
  .my_title{
    z-index: 999;
    position: fixed;
    top: 30%;
    text-shadow:2px 3px 1px rgba(232, 225, 225, 0.54);
    left: 10%;
    color: #ffffff;
    font-size: 90px;
    font-weight: bolder;
  }
  .my_title2{
    z-index: 999;
    position: fixed;
    top: 50%;
    left: 10%;
    color: #ffffff;
  
    font-size: 25px;
    font-weight: bolder;
  }
  </style>
  