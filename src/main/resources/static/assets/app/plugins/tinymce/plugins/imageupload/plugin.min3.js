(function () {
  tinymce.create('tinymce.plugins.ImageUploadPlugin', {
    init: function (ed, url) {
      url = tinyMCE.activeEditor.getParam('imageupload_rel') || url;
      var imageUploadUrl = tinyMCE.activeEditor.getParam('imageupload_url');
      var imageRoot = tinyMCE.activeEditor.getParam('image_root');

      var head = document.getElementsByTagName('body')[0];
      var css = document.createElement('link');
      css.type = 'text/css';
      css.rel = 'stylesheet';
      css.href = url + '/css/style.css';
      head.appendChild(css);

      var jform = document.createElement("script");
      jform.type = "text/javascript";
      jform.src = url + '/jquery.form.js';
      head.appendChild(jform);

      // Register commands
      ed.addCommand('mceImageUpload', function () {
        $('#image_upload_type').val('tinymce');
        $('body').append('<div class="imageUploadBg"></div>');

        var showImageUploadError = function (msg) {
          $('.imageUploadError').html(msg).show();
          removeForeground();
        };

        var removeForeground = function () {
          $('.imageUploadFg').remove();
          $('.imageUploadFgLoading').remove();
        };

        var removeBackground = function () {
          $('.imageUploadBg').remove();
          $('.imageUploadContainer').remove();
        };

        var container = '\
                    <div class="imageUploadContainer mce-container mce-panel mce-window">\
                        <div class="imageUploadContainerInner">\
                            <div class="mce-window-head">\
                                <div class="mce-title">上传文件</div>\
                                <button type="button" class="mce-close">×</button>\
                            </div>\
                            <form action="' + imageUploadUrl + '" method="POST"  enctype="multipart/form-data" id="uploadImageForm">\
                            <div class="mce-container imageUploadFormContainer" hidefocus="1" tabindex="-1">\
                                <div class="mce-container-body" style="padding-left: 20px;">\
                                    <label for="image-upload-area">上传文件</label>\
                                    <input type="file" name="file" id="image-upload-area" class="mce-textbox mce-placeholder" hidefocus="true" style="width: 258px;" accept="image/*,video/*">\
                                </div>\
                                <p class="imageUploadError"></p>\
                            </div>\
							<input type="hidden" name="item_id" value="0"/>\
							<input type="hidden" name="folder" value="Image"/>\
                            </form>\
                            <div class="imageUploadConfirmCase mce-panel">\
                                <div class="mce-btn mce-primary">\
                                    <button role="presentation" class="imageUploadSubmit">上传</button>\
                                </div>\
                                <div class="mce-btn">\
                                    <button role="presentation" class="imageUploadClose">放弃</button>\
                                </div>\
                            </div>\
                        </div>\
                    </div>\
                ';

        $('body').append(container);

        $('.imageUploadBg, .imageUploadContainer .imageUploadClose, .mce-close').on('click', function () {
          removeForeground();
          removeBackground();
        });

        var submitUpload = function () {
          $('#uploadImageForm').ajaxSubmit({
            dataType: 'json',
            success: function (response) {
              console.info(response)
              if (response.status == 0) {
                var tpl = '';
                switch (response.data[0].type) {
                  case 'image/png':
                  case 'image/jpg':
                  case 'image/jpeg':
                  case 'image/gif':
                    tpl = '<img src="%s" alt=""/><br/>';
                    break;

                  case 'video/mp4':
                    tpl = '<video src="%s" controls="controls" width="480" height="320"></video>';
                    break;

                  default:
                    tpl = '<a href="%s">下载</a>';
                    break;
                }
                var tplV = tpl.replace('%s', imageRoot + response.data[0].path);
                ed.insertContent(tplV);
                ed.focus();
                removeForeground();
                removeBackground();
              } else {
                showImageUploadError('上传失败，请重试！');
              }
            },
            error: function () {
              showImageUploadError('上传失败，请重试！');
            }
          });
        }

        $('#uploadImageForm').submit(function () {
          submitUpload();
          return false;
        });

        $('.imageUploadSubmit').on('click', function () {

          $('.imageUploadError').html('').hide();

          if ($('#image-upload-area').val() != '') {
            $('body').append('<div class="imageUploadFg"></div>');
            $('body').append('<div class="imageUploadFgLoading"></div>');
            $('#uploadImageForm').submit();
          } else {
            showImageUploadError('请选择图片格式的文件上传');
          }

        });
      });

      // Register buttons
      ed.addButton('imageupload', {
        title: '文件上传',
        cmd: 'mceImageUpload',
        image: url + '/img/icon.png'
      });
    },

    getInfo: function () {
      return {
        longname: 'Image Upload',
        author: 'BoxUK',
        authorurl: 'https://github.com/boxuk/tinymce-imageupload',
        infourl: 'https://github.com/boxuk/tinymce-imageupload/blob/master/README.md',
        version: '1.0.0'
      };
    }
  });

  tinymce.PluginManager.add('imageupload', tinymce.plugins.ImageUploadPlugin);
})();
