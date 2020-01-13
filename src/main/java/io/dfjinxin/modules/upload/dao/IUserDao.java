package io.dfjinxin.modules.upload.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjinxin.modules.upload.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * @author bourne
 * @email kuibobo@gmail.com
 * @date 2019-05-23 14:56:29
 */
@Mapper
public interface IUserDao extends BaseMapper<UserEntity> {

}
