package io.dfjinxin.modules.upload.controller;

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
public class AttachmentController {

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping("/list")
    public String list(Map<String, Object> models) {
        DataSet<AttachmentEntity> datas = attachmentService.queryAttachments(1l, 1, 20);
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        models.put("datas", datas);
        models.put("user", user);
        return "attachment/list";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public R upload(@RequestParam(name = "file") MultipartFile file,
                  @RequestParam(name = "folder") String folder,
                  @RequestParam(name = "user_id", defaultValue = "1") Long userId,
                  @RequestParam(name = "object_id", defaultValue = "0") Long objectId) {
        if (null != file && !file.isEmpty()) {
            AttachmentEntity atta = attachmentService.upload(file, folder, userId, objectId);
            return R.ok("OK").put("file", atta);
        } else {
            return R.error();
        }
    }
}
