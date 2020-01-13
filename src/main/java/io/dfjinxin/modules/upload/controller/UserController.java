package io.dfjinxin.modules.upload.controller;

import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.modules.upload.service.IUserService;
import io.dfjinxin.util.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public String list(Map<String, Object> models) {
        DataSet<UserEntity> datas = userService.queryUsers(1, 20);
        models.put("datas", datas);
        return "user/list";
    }

    @GetMapping("/edit/{user_id}")
    public String edit(@PathVariable("user_id") Long userId,
                       Map<String, Object> models) {
        models.put("user", userService.getUser(userId));
        return "user/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute UserEntity userEntity) {
        userService.saveOrUpdate(userEntity);
        super.addNotices("保存成功");
        return "redirect:/user/edit/" + userEntity.getId() ;
    }
}
