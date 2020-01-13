package io.dfjinxin.modules.upload.service;

import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.util.DataSet;

public interface IUserService {

    UserEntity getUser(Long userId);
    DataSet<UserEntity> queryUsers(Integer pageIndex, Integer pageSize);
    boolean saveOrUpdate(UserEntity userEntity);
}
