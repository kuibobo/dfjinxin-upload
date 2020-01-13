package io.dfjinxin.modules.upload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public enum NoticesType {

        ERROR(0),
        SUCCESS(1);

        private int value;
        NoticesType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static NoticesType get(int value) {
            return NoticesType.values()[value];
        }
    }

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected void addNotices(String msg) {
        addNotices(msg, NoticesType.SUCCESS);
    }

    protected void addError(String msg) {
        addNotices(msg, NoticesType.ERROR);
    }

    protected void addNotices(String msg, NoticesType type) {
        Cookie cookie = null;

        cookie = new Cookie("_bk_message", URLEncoder.encode(msg));
        cookie.setDomain(request.getServerName());
        cookie.setPath("/");
        response.addCookie(cookie);

        cookie = new Cookie("_bk_message_type", type.toString().toLowerCase());
        cookie.setDomain(request.getServerName());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
