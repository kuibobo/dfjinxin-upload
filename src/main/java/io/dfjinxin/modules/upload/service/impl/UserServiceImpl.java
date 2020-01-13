package io.dfjinxin.modules.upload.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.upload.dao.IUserDao;
import io.dfjinxin.modules.upload.entity.UserEntity;
import io.dfjinxin.modules.upload.service.IUserService;
import io.dfjinxin.util.DataSet;
import io.dfjinxin.util.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<IUserDao, UserEntity> implements IUserService {

    @Override
    public UserEntity getUser(Long userId) {
        return super.getById(userId);
    }

    @Override
    public DataSet<UserEntity> queryUsers(Integer pageIndex, Integer pageSize) {
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("pageIndex", pageIndex);
            put("pageSize", pageSize);
        }};

        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params)
        );

        return new DataSet(page);
    }

    @Override
    public boolean saveOrUpdate(UserEntity userEntity) {
        return super.saveOrUpdate(userEntity);
    }
}
