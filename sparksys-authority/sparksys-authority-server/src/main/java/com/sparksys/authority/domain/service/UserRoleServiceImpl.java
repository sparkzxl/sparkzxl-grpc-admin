package com.sparksys.authority.domain.service;

import com.sparksys.authority.application.service.IUserRoleService;
import com.sparksys.authority.domain.repository.IUserRoleRepository;
import com.sparksys.authority.infrastructure.constant.CacheConstant;
import com.sparksys.authority.infrastructure.convert.AuthUserConvert;
import com.sparksys.authority.infrastructure.entity.AuthUser;
import com.sparksys.authority.infrastructure.entity.UserRole;
import com.sparksys.authority.infrastructure.mapper.UserRoleMapper;
import com.sparksys.authority.interfaces.dto.role.RoleUserDTO;
import com.sparksys.authority.interfaces.dto.role.RoleUserDeleteDTO;
import com.sparksys.authority.interfaces.dto.role.RoleUserSaveDTO;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * description: 账号角色绑定 服务实现类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:01:40
 */
@Service
public class UserRoleServiceImpl extends AbstractSuperCacheServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Override
    protected String getRegion() {
        return CacheConstant.USER_ROLE;
    }

    @Override
    public boolean saveAuthRoleUser(RoleUserSaveDTO roleUserSaveDTO) {
        return userRoleRepository.saveAuthRoleUser(roleUserSaveDTO.getId(), roleUserSaveDTO.getUserIds());
    }

    @Override
    public boolean deleteAuthRoleUser(RoleUserDeleteDTO roleUserDeleteDTO) {
        return userRoleRepository.deleteAuthRoleUser(roleUserDeleteDTO.getId(), roleUserDeleteDTO.getUserIds());
    }

    @Override
    public RoleUserDTO getRoleUserList(Long roleId) {
        RoleUserDTO roleUserDTO = new RoleUserDTO();
        roleUserDTO.setId(roleId);
        List<AuthUser> authUsers = userRoleRepository.getRoleUserList(roleId);
        Optional.ofNullable(authUsers).ifPresent(x -> roleUserDTO.setAuthUsers(authUsers.stream()
                .map(AuthUserConvert.INSTANCE::convertAuthUserDTO).collect(Collectors.toList())));
        return roleUserDTO;
    }
}
