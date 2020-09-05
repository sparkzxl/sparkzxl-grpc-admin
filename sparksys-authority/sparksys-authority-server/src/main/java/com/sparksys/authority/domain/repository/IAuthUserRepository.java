package com.sparksys.authority.domain.repository;

import com.sparksys.authority.infrastructure.entity.AuthUser;
import com.sparksys.authority.infrastructure.entity.LoginAuthUser;
import com.sparksys.authority.infrastructure.entity.RoleResource;

import java.util.List;

/**
 * description: 用户仓储层
 *
 * @author zhouxinlei
 * @date 2020-06-05 20:39:15
 */
public interface IAuthUserRepository {


    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    AuthUser selectById(Long id);

    /**
     * 根据账户查询用户信息
     *
     * @param account
     * @return
     */
    AuthUser selectByAccount(String account);

    /**
     * 密码输错自增
     *
     * @param id id
     */
    void incrPasswordErrorNumById(Long id);

    /**
     * 获取用户权限集
     *
     * @param id id
     * @return Set<String>
     */
    List<String> getAuthUserPermissions(Long id);

    /**
     * 获取用户角色
     *
     * @param id 用户id
     * @return
     */
    List<String> getAuthUserRoles(Long id);

    /**
     * 查询角色路径
     *
     * @return List<RoleResource>
     */
    List<RoleResource> getRoleResourceList();

    /**
     * 密码输错自增
     *
     * @param account 账户
     */
    void incrPasswordErrorNumByAccount(String account);

    /**
     * 删除用户关联信息
     *
     * @param ids 用户ids
     */
    void deleteUserRelation(List<Long> ids);

    /**
     * 获取登录用户信息
     *
     * @param id 用户id
     * @return LoginAuthUser
     */
    LoginAuthUser getLoginAuthUser(Long id);
}
