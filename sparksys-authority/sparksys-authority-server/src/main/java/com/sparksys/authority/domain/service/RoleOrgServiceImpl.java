package com.sparksys.authority.domain.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sparksys.authority.application.service.IRoleOrgService;
import com.sparksys.authority.infrastructure.constant.CacheConstant;
import com.sparksys.authority.infrastructure.entity.RoleOrg;
import com.sparksys.authority.infrastructure.mapper.RoleOrgMapper;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.stereotype.Service;

/**
 * description: 角色组织关系 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:01:18
 */
@Service
public class RoleOrgServiceImpl extends AbstractSuperCacheServiceImpl<RoleOrgMapper, RoleOrg> implements IRoleOrgService {

    @Override
    public boolean deleteRoleOrgByOrgId(Long orgId) {
        return remove(new UpdateWrapper<RoleOrg>().lambda().eq(RoleOrg::getOrgId, orgId));
    }

    @Override
    protected String getRegion() {
        return CacheConstant.ROLE_ORG;
    }
}
