package io.dfjinxin.modules.upload.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.modules.upload.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年9月18日 上午9:42:16
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    List<SysMenuEntity> queryByPermsCode(List<String> codes);
}
