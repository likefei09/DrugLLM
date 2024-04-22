<template>
    <div>
        <div style="margin :10px 0">
         <el-carousel height="450px" :interval="10000">
            <el-carousel-item v-for="item in imgs" :key="item">
            <img :src="item" alt="" style="width: 100%">
            </el-carousel-item>
         </el-carousel>
        </div>
        <div style="margin: 10px 0">
            <el-row :gutter="10">
                <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px;">

                    <div style="border: 1px solid #ccc; padding-bottom: 10px;">
                    <img :src="item.url" alt="" style="width: 100%">
                    <div style="color: #666; padding: 10px;">{{ item.name }}</div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
export default {
    name: "FrontHome",
    data() {
        return {
            imgs : [
                'https://m.360buyimg.com/babel/jfs/t20270402/193539/36/43408/51480/660b6e5dF02422e86/2e426f61a2d2c7a9.jpg',
                'https://m.360buyimg.com/babel/jfs/t20270410/166401/34/43742/49068/66164f25Fc7f21494/2ac65070bd7bc793.jpg'
            ],
            files: []
        }
    },
    created() {
        this.request.get("/echarts/file/front/all").then(res => {
            console.log(res.data)
            this.files = res.data.filter(v => v.type === 'png' || v.type === 'jpg' || v.type === 'webg')
        })
    }
}
</script>
