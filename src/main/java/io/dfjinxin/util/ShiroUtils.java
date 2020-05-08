package io.dfjinxin.util;

import io.dfjinxin.modules.auth.utils.UserThreadLocal;
import io.dfjinxin.modules.auth.vo.OnlineUser;
import io.dfjinxin.modules.upload.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShiroUtils {

    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";
    /**  循环次数 */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static UserEntity getCurrentUserEntity() {
        OnlineUser onlineUser = UserThreadLocal.get();
        UserEntity entity = new UserEntity();
        entity.setId(onlineUser.getUserId());
        entity.setName(onlineUser.getUsername());

        Set<String> paramCode = onlineUser.getPermissions();
        if (paramCode.size() > 0) {
            List<String> permissionList = new ArrayList<>(paramCode);
            boolean isAdmin = permissionList.stream().anyMatch(code -> code.equals("user-list"));
            entity.setAdmin(isAdmin);
        } else {
            entity.setAdmin(false);
        }
        return entity;
    }

    public static Long getUserId() {
        return getCurrentUserEntity().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return getCurrentUserEntity() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
