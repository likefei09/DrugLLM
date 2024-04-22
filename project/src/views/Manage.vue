<template>
  <el-container style= "min-height: 100vh;">
     <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
     <Aside :isCollapse ="isCollapse" :logoTextShow="logoTextShow" />
   </el-aside>
 
   <el-container>
     <el-header style=" border-bottom: 1px solid #ccc ; ">
       <Header :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse" :user="user" />
       <!-- pathName -->
     </el-header>
 
    <el-main>
       <!-- 表示当前页面的子路由会在 router-view 里面显示 （类似于index.js中的children的子路由）-->
       <router-view @refreshUser="getUser" />
    </el-main>
 
  </el-container>
 </el-container>
  </template>
  
  <script>
 
  import Aside from '@/components/Aside.vue';
   import Header from '@/components/Header.vue';
  
  export default {
  name: 'ManageView',
  data(){
    return{
      activeIndex: '1',
      activeIndex2: '1',
      user: {},
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
    }
  },
 components: {
   Aside,
   Header,
 },
 created() {
   //调用后台的方法，让user一开始就从后台获取最新的数据
   this.getUser()
 },
  methods:{
   getUser() {
         //取一下username
         let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username :"" //从浏览器缓存中取到username
         //从后台获取user数据
          this.request.get("/user/username/" + username).then(res => {
           //重新赋值后台的最新user数据
           this.user = res.data
          })
         },
   collapse(){  //点击收缩按钮触发
       this.isCollapse = !this.isCollapse
       console.log(this.isCollapse);
       if(this.isCollapse){
         this.sideWidth = 64
         this.collapseBtnClass = 'el-icon-s-unfold'
         this.logoTextShow=false
       }else{
         this.sideWidth = 200
         this.collapseBtnClass = 'el-icon-s-fold'
         this.logoTextShow=true
       }
     },
  }
  }
  </script>
  
  <style>
  /* .firstContainer{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height:100%;
  } */
  </style>
  