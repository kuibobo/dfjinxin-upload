package io.dfjinxin.modules.auth.service;


import io.dfjinxin.modules.auth.vo.OnlineUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public interface AuthService {

    OnlineUser nowOnlineUser(String accessToken, HttpServletRequest request, HttpServletResponse response);

    Set<String> selectPermissionsByUserIdAndSystemToSet(Long userId);

    String queryUsersByIds(Long userId);

    Boolean loginOut(HttpServletRequest request);

    Boolean checkLogin(HttpServletRequest request);
}
