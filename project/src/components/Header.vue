<template>
    <div style="line-height: 60px; display: flex">
      <div style="flex: 1; font-size: 20px">
        <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="func_collapse"></span>
        <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
      </div>
      <el-dropdown style="width: 150px; cursor: pointer; text-align: right;">
                <div style="display: inline-block">
                <img :src="user.avatar" alt=""
                style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
                <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
                </div>
        <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
            <router-link to="/password">修改密码</router-link>
            </el-dropdown-item>
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
            <router-link to="/person">个人信息</router-link>
            </el-dropdown-item>
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
            <span style="text-decoration: none" @click="logout">退出</span>
            </el-dropdown-item>
        </el-dropdown-menu>
        </el-dropdown>
    </div>
  </template>

<script>
export default{
    name:"Header",
    props: {
        collapseBtnClass:String,
        collapse:Boolean,
        user: Object
    },
    watch:{//监听路由变化，百度
        '$route':function(){
            this.currentPathName = localStorage.getItem("currentPathName")//取出route里面设置的当前路由信息
        }
    },
    data(){
        return{
            currentPathName:localStorage.getItem("currentPathName")
        }
    },
    methods:{
      func_collapse() {
      // this.$parent.$parent.$parent.$parent.collapse()  // 通过4个 $parent 找到父组件，从而调用其折叠方法
      this.$emit("asideCollapse")
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

</style>
