<template>
    <el-container class="firstContainer"> 
     <el-header>
       <el-menu
     :default-active="activeIndex2"
     class="el-menu-demo"
     mode="horizontal"
     @select="handleSelect"
     background-color="rgb(48, 65, 86)"
     text-color="#fff"
     active-text-color="#ffd04b"
     >
     <el-menu-item index="1">处理中心</el-menu-item>
     <el-submenu index="2">
       <template slot="title">我的工作台</template>
       <el-menu-item index="2-1">选项1</el-menu-item>
       <el-menu-item index="2-2">选项2</el-menu-item>
       <el-menu-item index="2-3">选项3</el-menu-item>
       <el-submenu index="2-4">
         <template slot="title">选项4</template>
         <el-menu-item index="2-4-1">选项1</el-menu-item>
         <el-menu-item index="2-4-2">选项2</el-menu-item>
         <el-menu-item index="2-4-3">选项3</el-menu-item>
       </el-submenu>
     </el-submenu>
     <el-menu-item index="3" disabled>消息中心</el-menu-item>
     <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
   </el-menu>
   </el-header>
     <el-main>
      <div style="padding: 10px 0;">
          <el-input style="width: 200px" placeholder="请输入名称"  suffix-icon="el-icon-search" v-model="username" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="请输入邮箱"  suffix-icon="el-icon-message" v-model="email" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="请输入地址"  suffix-icon="el-icon-position" v-model="address" class="ml-5"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
    
        <div>
          <el-button type="primary">新增<i class="el-icon-circle-plus-outline"></i></el-button>
          <el-button type="danger">批量删除<i class="el-icon-remove-outline"></i></el-button>
          <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>
          <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
        </div>
    
       <el-table :data="tableData" border stripe>
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
         <el-table-column prop="username" label="用户名" width="140"></el-table-column>
         <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
         <el-table-column prop="email" label="邮箱"></el-table-column>
         <el-table-column prop="phone" label="电话"></el-table-column>
         <el-table-column prop="address" label="地址"></el-table-column>
         <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="success">编辑<i class="el-icon-edit"></i></el-button>
              <el-button type="danger">删除<i class="el-icon-remove-outline"></i></el-button>
            </template>
          </el-table-column>
       </el-table>
       <div style="padding: 10px 0;">
          <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :aria-current="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
        </div>
  
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible">
      <el-form label-width="120">
      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
    </div>
  </el-dialog>
  
     </el-main>
   </el-container>
   </template>
   
   <script>
  import request from '@/utils/request';
   
   
   export default {
   name: 'HomeView',
   data(){
     return{
       tableData: [],
       total: 0,
       pageNum: 1,
       pageSize: 2,
       username: "",
       email: "",
       address: "",
       form:{},
       dialogFormVisible: fasle,
       msg: "hello 你好!",
       activeIndex: '1',
       activeIndex2: '1',
     }
   },
   created(){
    //请求分页查询数据
    this.load()
   },
   methods:{
     handleSelect(key, keyPath) {
         console.log(key, keyPath);
       },
      load(){
        this.request.get("http://localhost:9090/user/page",{
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            username: this.username,
            email: this.email,
            address: this.address
          }
        }).then(res =>{
          console.log(res)
          this.tableData = res.records
          this.total = res.total
        })
      },
     handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
     },
     handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum   //之后需要再请求数据，fetch，于是封装一个方法
      this.load()
     },
     reset(){
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
     }
   }
   }
   </script>
   
   <style>
   .firstContainer{
     position: absolute;
     top: 0;
     left: 0;
     width: 100%;
     height:100%;
   }
   </style>
   