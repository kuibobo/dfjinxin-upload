package io.dfjinxin.modules.upload.controller;

import cn.hutool.core.io.FileUtil;
import io.dfjinxin.modules.upload.entity.AttachmentEntity;
import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.modules.upload.service.IAttachmentService;
import io.dfjinxin.util.DataSet;
import io.dfjinxin.util.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/attachment")
public class AttachmentController extends AbstractController{

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping("/list")
    public String list(Map<String, Object> models) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        DataSet<AttachmentEntity> datas = attachmentService.queryAttachments(user.getId(), 1, 20);

        models.put("datas", datas);
        return "attachment/list";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public R upload(@RequestParam(name = "file") MultipartFile file,
                  @RequestParam(name = "folder") String folder,
                  @RequestParam(name = "object_id", defaultValue = "0") Long objectId) {
        if (null != file && !file.isEmpty()) {
            UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
            AttachmentEntity atta = attachmentService.upload(file, folder, user.getId(), objectId);
            return R.ok("OK").put("file", atta);
        } else {
            return R.error();
        }
    }

    @GetMapping(value = "/remove/{id}")
    @ResponseBody
    public R remove(@PathVariable("id") Long id) {
        attachmentService.remove(id);
        return R.ok("OK");
    }
}
