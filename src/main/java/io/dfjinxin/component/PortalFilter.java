//package io.dfjinxin.component;
//
//import io.dfjinxin.modules.upload.entity.UserEntity;
//import io.dfjinxin.modules.upload.service.IUserService;
//import io.dfjinxin.util.ShiroUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import java.security.Principal;
//
//public class PortalFilter {
//    private static final Logger log = LoggerFactory.getLogger(PortalFilter.class);
//
//    private IUserService sysUserService;
//
//    public boolean doLogin(HttpServletRequest request) {
//        boolean isLogin = ShiroUtils.isLogin();
//        if (isLogin) {
//            return true;
//        }
//        //如果未登录，读取cas信息
//        String casName = getCasName(request);
//        if (StringUtils.isBlank(casName)) {
//            warn("casUserCookieId为空，无法获取门户用户信息");
//            if (StringUtils.isBlank(casName))
//                return false;
//        }
//
//        ServletContext sc = request.getServletContext();
//        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//        sysUserService = ctx.getBean(IUserService.class);
//
//        UserEntity userEntity = sysUserService.getByName(casName);
//        if (userEntity == null) {
//            userEntity = new UserEntity();
//            userEntity.setName(casName);
//            sysUserService.saveOrUpdate(userEntity);
//        }
//        //设置已登录
//        Subject subject = ShiroUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(casName, "无需登录密码");
//        subject.login(token);
//        return true;
//    }
//
//    private String getCasName(HttpServletRequest request) {
//        String username = request.getRemoteUser();
//        if (StringUtils.isNotBlank(username))
//            return username;
//        Principal pal = request.getUserPrincipal();
//        if (pal != null) {
//            username = pal.getName();
//            if (username != null)
//                return username;
//        }
//        Object obj = request.getAttribute("credentials");
//        if (obj != null)
//            return obj.toString();
//        return username;
//    }
//
//    private void warn(String msg) {
//        log.warn(msg);
//    }
//}