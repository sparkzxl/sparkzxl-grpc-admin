package com.sparksys.authority.interfaces.controller.auth;


import com.sparksys.authority.application.service.IUserRoleService;
import com.sparksys.authority.interfaces.dto.role.RoleUserDTO;
import com.sparksys.authority.interfaces.dto.role.RoleUserDeleteDTO;
import com.sparksys.authority.interfaces.dto.role.RoleUserSaveDTO;
import com.sparksys.log.annotation.WebLog;
import com.sparksys.web.annotation.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * description: 账号角色绑定 前端控制器
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 21:07:12
 */
@RestController
@ResponseResult
@WebLog
@Api(tags = "角色管理")
public class UserRoleController {

    private final IUserRoleService userRoleService;

    public UserRoleController(IUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @ApiOperation("保存角色用户")
    @PostMapping("/role/user")
    public boolean saveAuthRoleUser(@Validated @RequestBody RoleUserSaveDTO roleUserSaveDTO) {
        return userRoleService.saveAuthRoleUser(roleUserSaveDTO);
    }


    @ApiOperation("删除角色用户")
    @DeleteMapping("/role/user")
    public boolean deleteAuthRoleUser(@Validated @RequestBody RoleUserDeleteDTO roleUserDeleteDTO) {
        return userRoleService.deleteAuthRoleUser(roleUserDeleteDTO);
    }


    @ApiOperation("查询角色用户")
    @GetMapping("/role/user/{roleId}")
    public RoleUserDTO getRoleUserList(@PathVariable("roleId") Long roleId) {
        return userRoleService.getRoleUserList(roleId);
    }

}
