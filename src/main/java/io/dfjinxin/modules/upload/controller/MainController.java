package io.dfjinxin.modules.upload.controller;

import io.dfjinxin.component.PortalFilter;
import io.dfjinxin.config.SystemParams;
import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MainController {


    @Autowired
    private SystemParams systemParams;

    @RequestMapping("logincas")
    public String logincas() {
        return "redirect:" + systemParams.getPortalUrl();
    }

    @RequestMapping("logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:" + systemParams.getPortalUrl();
    }

    @RequestMapping(value = {"/"})
    public String index(HttpServletRequest request, Map<String, Object> map) {
        PortalFilter sso = new PortalFilter();
        boolean isLogin = sso.doLogin(request);
        if (!isLogin) {
            return "redirect:" + systemParams.getPortalUrl();
        } else {
            return "redirect:/attachment/list";
        }
    }
}
