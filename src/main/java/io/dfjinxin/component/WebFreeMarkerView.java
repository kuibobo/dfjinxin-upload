package io.dfjinxin.component;

import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.modules.upload.service.IUserService;
import io.dfjinxin.util.CookieUtil;
import io.dfjinxin.util.HttpServletUtil;
import io.dfjinxin.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

public class WebFreeMarkerView extends FreeMarkerView {

    private void cleanNotices() {
        Cookie cookie = null;
        HttpServletRequest request = HttpServletUtil.getRequest();
        HttpServletResponse response = HttpServletUtil.getResponse();

        cookie = CookieUtil.getCookie(request.getCookies(), "_bk_message");
        if (cookie != null) {
            try {
                request.setAttribute("_bk_message", URLDecoder.decode(cookie.getValue(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
            }

            cookie.setMaxAge(1);
            cookie.setDomain(request.getServerName());
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        cookie = CookieUtil.getCookie(request.getCookies(), "_bk_message_type");
        if (cookie != null) {
            request.setAttribute("_bk_message_type", cookie.getValue());

            cookie.setMaxAge(1);
            cookie.setDomain(request.getServerName());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AppPathProperties appPathProperties = BeanComponent.getBean(AppProperties.class).getPath();
        model.put("path", appPathProperties.getWorkDir() + appPathProperties.getUpload());
        model.put("uri", request.getRequestURI());

        UserEntity currentUser = ShiroUtils.getCurrentUserEntity();
        UserEntity dbUser = BeanComponent.getBean(IUserService.class).getUser(currentUser.getId());
        if (dbUser == null) {
            dbUser = new UserEntity();
            dbUser.setId(currentUser.getId());
            dbUser.setName(currentUser.getName());
            BeanComponent.getBean(IUserService.class).saveOrUpdate(dbUser);
        }
        currentUser.setPath(dbUser.getPath());
        model.put("currentUser", currentUser);

        cleanNotices();
        super.exposeHelpers(model, request);
    }
}