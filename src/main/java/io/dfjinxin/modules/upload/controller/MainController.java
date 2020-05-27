package io.dfjinxin.modules.upload.controller;


import io.dfjinxin.exception.ApplicationException;
import io.dfjinxin.modules.auth.service.AuthService;
import io.dfjinxin.modules.auth.utils.CookieUtils;
import io.dfjinxin.modules.auth.utils.UserThreadLocal;
import io.dfjinxin.modules.auth.vo.OnlineUser;
import io.dfjinxin.modules.upload.entity.SysMenuEntity;
import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.modules.upload.service.IUserService;
import io.dfjinxin.modules.upload.service.SysMenuService;
import io.dfjinxin.util.Constant;
import io.dfjinxin.util.CookieUtil;
import io.dfjinxin.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    @Value("${auth.login.url}")
    private String loginUrl;

    @Value("${auth.logout.url}")
    private String logoutUrl;

    @Autowired
    private AuthService authService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private IUserService sysUserService;

    @RequestMapping("logincas")
    public String logincas() throws UnsupportedEncodingException {
        return "redirect:" + gtoLoginUrl();
    }

    private String gtoLoginUrl () throws UnsupportedEncodingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path;
        return loginUrl + URLEncoder.encode(basePath + "/loginback" , "utf-8");
    }

    @RequestMapping("loginback")
    public String loginback(HttpServletRequest request, HttpServletResponse response){
        String access_token = request.getParameter("access_token");
        String refresh_token = request.getParameter("refresh_token");
        if (StringUtils.isNotEmpty(access_token)) {
            CookieUtil.set(response, Constant.ACCESS_TOKEN, access_token, 60 * 60 * 12 * 2 * 7);
        }
        if (StringUtils.isNotEmpty(refresh_token)) {
            CookieUtil.set(response, Constant.REFRESH_TOKEN, refresh_token, 60 * 60 * 12 * 2 * 7);
        }
        return "redirect:/";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        io.dfjinxin.util.CookieUtil.set(response, Constant.ACCESS_TOKEN, null, 0);
        io.dfjinxin.util.CookieUtil.set(response, Constant.REFRESH_TOKEN, null, 0);
        authService.loginOut(request);
        return "redirect:"+logoutUrl;
    }

    @RequestMapping(value = {"/"})
    public String index(HttpServletRequest request, Map<String, Object> map) {
//        PortalFilter sso = new PortalFilter();
//        boolean isLogin = sso.doLogin(request);
//        if (!isLogin) {
//            return "redirect:" + systemParams.getPortalUrl();
//        } else {
//            return "redirect:/attachment/list";
//        }

        OnlineUser onlineUser = UserThreadLocal.get();
        if (!onlineUser.isIaAuth()) {
            return "redirect:" + loginUrl;
        }

        UserEntity userEntity = sysUserService.getByName(onlineUser.getUsername());
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setId(onlineUser.getUserId());
            userEntity.setName(onlineUser.getUsername());
            sysUserService.saveOrUpdate(userEntity);
        }
        Set<String> paramCode = onlineUser.getPermissions();
        if (paramCode.size() > 0 || true) {
            return "redirect:/attachment/list";
        } else {
            return "noaccess";
        }
    }

    @GetMapping("noaccess")
    public String noaccess() {
        return "noaccess";
    }
}
