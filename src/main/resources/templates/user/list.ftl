<#include "/header.ftl" />
<div class="my-3 my-md-5">
  <div class="container" id="app">
    <div class="row">
      <div class="col-12">

        ${ShowNotices()}

        <div class="card">

          <div class="card-header">
            <h3 class="card-title">用户列表</h3>
            <div class="card-options">
            </div>
          </div>
          <div class="card-body">
            <table class="table card-table table-vcenter">
              <thead>
              <th>ID</th>
              <th>用户名</th>
              <th>上传路径</th>
              <th></th>
              <thead>
              <tbody>
              <#assign users = datas.objects>
              <#list users as user>
                <tr>
                  <td>${user.id}</td>
                  <td>${user.name}</td>
                  <td>
                    <#if user.path??>
                      ${path}/${(user.path)!""}
                    <#else>
                      ${path}/${user.name}
                    </#if>
                  </td>
                  <th>
                    <a class="btn btn-primary btn-sm" href="${request.contextPath}/user/edit/${user.id}"><i class="fe fe-edit"></i> 设置</a>
                  </th>
                </tr>
              </#list>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>

</script>
<#include "/footer.ftl" />