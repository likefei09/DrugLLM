<template>
 <div>
    <!-- 头部 -->
    <div style="display: flex; height: 60px; line-height: 60px; border-bottom: 1px solid #ccc;">
        <div style="width: 200px; text-align: center;"><h1>DrugLLM</h1> </div>
        <div style="flex: 1;">
        <!-- 导航菜单
        <ul style="list-style: none; background-color: #008000;">
            <li class="item">
                <el-dropdown size="meidum">
                <span>更多菜单<i class="el-icon-arrow-down el-icon--right"></i></span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>黄金糕</el-dropdown-item>
                    <el-dropdown-item>狮子头</el-dropdown-item>
                    <el-dropdown-item>螺蛳粉</el-dropdown-item>
                    <el-dropdown-item>双皮奶</el-dropdown-item>
                    <el-dropdown-item>蚵仔煎</el-dropdown-item>
                </el-dropdown-menu>
                </el-dropdown>
            </li>
            <li class="item"><a href="/">菜单2</a></li>
            <li class="item"><a href="/">菜单3</a></li>
            <li class="item"><a href="/">菜单4</a></li>
        </ul> -->
        <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" router>
            <el-menu-item index="/front/home">
    <i class="el-icon-s-home"></i>
    主页
   </el-menu-item>
   <el-menu-item index="/front/consult">
    <i class="el-icon-s-opportunity"></i>
    咨询
   </el-menu-item>
   <el-menu-item index="/front/trans">
    <i class="el-icon-thumb"></i>
    转换
   </el-menu-item>
   <el-menu-item index="/front/analysis">
    <i class="el-icon-s-data"></i>
    综合分析
   </el-menu-item>
</el-menu>
        </div>
        <div style="width: 200px" >
        <div v-if="!user.username" style="text-align: right; padding-right: 30px;">
            <el-button @click="$router.push('/login')">登录</el-button>
            <el-button @click="$router.push('/register')">注册</el-button>
    </div>
        <div v-else>
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
        </div>
    </div>
    
    <div style="width: 1000px; margin: 0 auto;">
        <router-view />
    </div>
 </div>
</template>

<script>
export default {
    name: "Front",
    data() {
        return {
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) :{},
            activeIndex2: '1',
        }
    },
    created() {

    },
    methods: {
        logout() {
            this.$router.push("/login")
            localStorage.removeItem("user")
            this.$message.success("退出成功")
    },
    }
}
</script>

<style>
.item{
    display: inline-block;
    width: 100px;
    
    text-align: center;
    cursor: pointer;
}
.item a{
    color: #fff;
}
.item:hover {
    background-color: #FFD700;
}
</style>
