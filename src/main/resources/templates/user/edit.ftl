<#include "/header.ftl" />
<div class="my-3 my-md-5">
  <div class="container" id="app">
    <div class="row">
      <div class="col-12">
          ${ShowNotices()}

        <form method="post" action="${request.contextPath}/user/save">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">编辑: ${user.name}</h3>
            <div class="card-options">
            </div>
          </div>
          <div class="card-body">
              <div class="form-group">
                <label class="form-label">上传路径</label>
                <input type="text" name="path" class="form-control" value="${(user.path)!""}"/>
              </div>
              <input type="hidden" name="id" value="${user.id}">
          </div>
          <div class="card-footer text-right">
            <a class="btn btn-secondary" href="${request.contextPath}/user/list">取消</a>
            <button class="btn btn-primary">保存</button>
          </div>
        </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script>

</script>
<#include "/footer.ftl" />