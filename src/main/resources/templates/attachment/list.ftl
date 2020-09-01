<#include "/header.ftl" />
<link rel="stylesheet" href="${request.contextPath}/assets/app/plugins/ELEMENT/index.css" />
<link rel="stylesheet" href="${request.contextPath}/assets/app/plugins/daterangepicker/plugin.css">
<script src="${request.contextPath}/assets/app/plugins/vue/plugin.js"></script>
<script src="${request.contextPath}/assets/app/plugins/axios/plugin.js"></script>
<script src="${request.contextPath}/assets/app/plugins/ELEMENT/plugin.js"></script>
<style>
  table a, table a:hover {color:#000;}
</style>
<div class="my-3 my-md-5">
  <div class="container" id="app">
    <div class="row">
      <div class="col-12">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">我的文件</h3>
            <div class="card-options">
              <button class="btn btn-primary btn-sm" @click="showUploadDlg = true"><i class="fe fe-arrow-up"></i> 上传</button>
            </div>
          </div>
          <div class="card-body">
            <table class="table card-table table-vcenter">
              <thead>
              <th>ID</th>
              <th>文件名</th>
              <th>上传时间</th>
              <th></th>
              <thead>
              <tbody>
              <#assign attas = datas.objects>
              <#list attas as att>
                <tr>
                  <td>${att.id}</td>
                  <td><a href="javascript:void(0)">${att.filename}</a></td>
                  <td>${att.createTime?datetime}</td>
                  <td><button class="btn btn-danger ml-auto" @click="handleRemove(${att.id})"><i class="fa fa-edit"></i> 删除</button></td>
                </tr>
              </#list>
              </tbody>
            </table>
          </div>
        </div>
        <el-dialog title="文件上传" :visible.sync="showUploadDlg" width="40%">
          <span>
            <el-upload class="upload-demo" drag multiple :on-success="onUploadSuccess" accept=".xls,.xlsx,.csv"
                   action="${request.contextPath}/attachment/upload" :data="postData">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
          </span>
        </el-dialog>
      </div>
    </div>
  </div>
</div>
<script>
  require(['vue', 'axios', 'ELEMENT'], function(Vue, axios, ELEMENT) {
    Vue.use(ELEMENT);
    var app = new Vue({
      el: '#app',
      data() {
        return {
          postData: {
            tenant_id: 1,
            object_id: 1,
            folder: '',
          },
          showUploadDlg: false,
        }
      },
      methods: {
        onUploadSuccess(response, file, fileList) {
          this.$message({
            message: '上传成功',
            type: 'success',
            duration: 1500,
            onClose: function(){
              location.reload();
            },
          })
        },

        handleRemove(id) {
          this.$confirm('确认要删除么', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            axios.get('${request.contextPath}/attachment/remove/' + id).then((resp) => {
              this.$message({
                message: '删除成功',
                type: 'success',
                duration: 1500,
                onClose: function(){
                  location.reload();
                },
              })

            });
        }).catch(() => {});

        },

      },
    })
  });
</script>
<#include "/footer.ftl" />