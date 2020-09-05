package com.sparksys.authority.application.service;

import com.sparksys.authority.infrastructure.entity.RoleOrg;
import com.sparksys.database.base.service.SuperCacheService;

/**
 * <p>
 * 角色组织关系 服务类
 * </p>
 *
 * @author zhouxinlei
 * @since 2020-07-19
 */
public interface IRoleOrgService extends SuperCacheService<RoleOrg> {

    /**
     * 删除角色组织
     *
     * @param orgId 组织id
     * @return
     */
    boolean deleteRoleOrgByOrgId(Long orgId);
}
