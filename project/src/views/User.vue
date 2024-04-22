<template>
    <div>
        <div style="padding: 10px 0;">
        <el-input style="width: 200px" placeholder="请输入名称"  suffix-icon="el-icon-search" v-model="username" class="ml-5"></el-input>
        <el-input style="width: 200px" placeholder="请输入邮箱"  suffix-icon="el-icon-message" v-model="email" class="ml-5"></el-input>
        <el-input style="width: 200px" placeholder="请输入地址"  suffix-icon="el-icon-position" v-model="address" class="ml-5"></el-input>
        <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
      </div>
  
      <div>
        <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
        <el-button type="danger" @click="delBatch">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </div>
  
     <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
       <el-table-column prop="username" label="用户名" width="140"></el-table-column>
       <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
       <el-table-column prop="email" label="邮箱"></el-table-column>
       <el-table-column prop="phone" label="电话"></el-table-column>
       <el-table-column prop="address" label="地址"></el-table-column>
       <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
            <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我在想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="del(scope.row.id)"
          >
            
            <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
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
      <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
  <el-form label-width="80px" size="small">
    <el-form-item label="用户名" >
      <el-input v-model="form.username" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="用户昵称">
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
    <el-button type="primary" @click="save">确 定</el-button>
  </div>
</el-dialog>

    </div>
</template>

<script>
export default {
    name: "User",
    data() {
        return {
            tableData: [],
            total: 0,
            pageNum: 1,
            pageSize: 2,
            username: "",
            email: "",
            address: "",
            dialogFormVisible: false,
            form:{},
            multipleSelection:[],
        }
    },
    created() {
        this.load()
    },
    methods :{
    load(){
      this.request.get("/user/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res =>{
        this.tableData = res.data.records
        this.total = res.data.total
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
   },
   handleAdd(){
    this.dialogFormVisible = true
    this.form = {}
   },
   save(){
    this.request.post("/user",this.form).then(res=>{
      if (res) {
        this.$message.success("保存成功")
        this.dialogFormVisible = false
        this.load()
      }else{
        this.$message.error("保存失败")
        console.log(res)
      }
    })
   },
   handleEdit(row){
    this.form = row
    this.dialogFormVisible = true
   },
   del(id){
    this.request.delete("/user/" + id).then(res =>{
      if(res.data){
        this.$message.success("删除成功")
        console.log(res.data)
        this.load()
      }else{
        this.$message.error("删除失败")
      }
    })
   },
   handleSelectionChange(val){
    this.multipleSelection = val
   },
   delBatch(){
    let ids = this.multipleSelection.map(v => v.id)  //将对象数组变为id数组
    this.request.post("/user/del/batch" , ids).then(res =>{
      if(res.data){
        this.$message.success("批量删除成功")
        this.load()
      }else{
        this.$message.error("批量删除失败")
      }
    })
   }
    }
}
</script>
