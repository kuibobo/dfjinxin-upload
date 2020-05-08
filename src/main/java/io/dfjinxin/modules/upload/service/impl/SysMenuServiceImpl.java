package io.dfjinxin.modules.upload.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.upload.dao.SysMenuDao;
import io.dfjinxin.modules.upload.entity.SysMenuEntity;
import io.dfjinxin.modules.upload.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Override
    public List<SysMenuEntity> queryByPermsCode(List<String> codes) {
        if (codes.size() == 0) {
            return new ArrayList<>();
        }
        List<SysMenuEntity> menuIdList = baseMapper.queryByPermsCode(codes);
        List<SysMenuEntity> rootTrees = new ArrayList<SysMenuEntity>();
        for (SysMenuEntity tree : menuIdList) {
            if(tree.getParentId() == 0){
                rootTrees.add(tree);
            }
            for (SysMenuEntity t : menuIdList) {
                if(t.getParentId() == tree.getMenuId()){
                    if(tree.getList() == null){
                        List<SysMenuEntity> myChildrens = new ArrayList<SysMenuEntity>();
                        myChildrens.add(t);
                        tree.setList(myChildrens);
                    }else{
                        tree.getList().add(t);
                    }
                }
            }
        }
        return rootTrees;
    }
}
