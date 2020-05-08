package io.dfjinxin.modules.upload.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjinxin.modules.upload.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单管理
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019年9月18日 上午9:33:01
 */
@Repository
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    List<SysMenuEntity> queryByPermsCode(@Param("codes") List<String> codes);

}
