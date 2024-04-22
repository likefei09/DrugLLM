<template>
    <el-menu
:default-active="activeIndex2"
class="el-menu-demo"
mode="horizontal"
@select="handleSelect"
background-color="rgb(48, 65, 86)"
text-color="#fff"
active-text-color="#ffd04b"
router
>
<el-menu-item index="/home">
<i class="el-icon-s-home"></i>
DrugLLM 主页
</el-menu-item>
<el-menu-item index="/consult">
<i class="el-icon-s-opportunity"></i>
咨询
</el-menu-item>
<el-menu-item index="/trans">
<i class="el-icon-thumb"></i>
转换
</el-menu-item>
<el-menu-item index="/analysis">
<i class="el-icon-s-data"></i>
综合分析
</el-menu-item>
<el-menu-item index="/file">
<i class="el-icon-document"></i>
文件管理
</el-menu-item>
<el-submenu index="2">
 <template slot="title">
    <i class="el-icon-user"></i>
    系统管理
</template>
 <el-menu-item index="/user">用户管理</el-menu-item>
 <el-menu-item index="2-2">选项2</el-menu-item>
 <el-menu-item index="2-3">选项3</el-menu-item>
 <el-submenu index="2-4">
   <template slot="title">选项4</template>
   <el-menu-item index="2-4-1">选项1</el-menu-item>
   <el-menu-item index="2-4-2">选项2</el-menu-item>
   <el-menu-item index="2-4-3">选项3</el-menu-item>
 </el-submenu>
</el-submenu>
<el-dropdown style="width: 70px; cursor :pointer; text-align: right;" class= "header-container">
<div style="display: inline-block">
<img :src="user.avatar" alt=""
style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px;">
<span style="color: #fff;">{{user.nickname}}</span><i class="el-icon-arrow-down" style="color: #fff;"></i>
</div>
<el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center;">
<el-dropdown-item>
  <router-link to="/password">修改密码</router-link>
</el-dropdown-item> 
<el-dropdown-item>
  <router-link to="/person">个人信息</router-link>
</el-dropdown-item>  
<el-dropdown-item>
  <span style="text-decoration: none" @click="logout">退出</span>
</el-dropdown-item>
</el-dropdown-menu>
</el-dropdown> 
</el-menu>
</template>

<script>
export default {
name :"Aside",
props: {  //接收传来的参数
    activeIndex2 :String,
    user: Object
},
data(){
  return {
    //由于上方接收了来自manage的参数，故此处不需要获取
    //user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
  }
},
methods: {
    handleSelect(index) {
    console.log(index);
    console.log(this.$router.options.routes)
 },
 logout() {
  this.$router.push("/login")
  localStorage.removeItem("user")
  this.$message.success("退出成功")
 }
}
}
</script>

<style>
.header-container {
position: relative;
left: 500px;
top: 5px;
/* display: flex;
justify-content: flex-end; */
/* 其他样式属性 */
}
</style>


