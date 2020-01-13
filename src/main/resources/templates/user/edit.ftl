<#include "/header.ftl" />
<div class="my-3 my-md-5">
  <div class="container" id="app">
    <div class="row">
      <div class="col-12">
        <div class="card">
          ${ShowNotices()}
          <div class="card-header">
            <h3 class="card-title">编辑: ${user.name}</h3>
            <div class="card-options">
            </div>
          </div>
          <div class="card-body">
            <form method="post" action="${request.contextPath}/user/save">
              <div class="form-group">
                <label class="form-label">上传路径</label>
                <input type="text" name="path" class="form-control" value="${(user.path)!""}"/>
              </div>
              <div class="form-footer">
                <button class="btn btn-primary btn-block">保存</button>
              </div>
              <input type="hidden" name="id" value="${user.id}">
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>

</script>
<#include "/footer.ftl" />