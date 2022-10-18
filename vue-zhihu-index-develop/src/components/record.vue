<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="fileName"
      label="文件名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="time"
      label="时间"
      width="180">
    </el-table-column>
    <el-table-column
      prop="isBug"
      label="是否有缺陷">
    </el-table-column>
  </el-table>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      /*tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]*/
      tableData:[]
    }
  },
  created:function () {
    axios.get("http://localhost:8088/record", { params: { userName: sessionStorage.getItem('userName') } }).then((response)=>{
      this.tableData=response.data
    })
  }
}
</script>


<style scoped>

</style>
