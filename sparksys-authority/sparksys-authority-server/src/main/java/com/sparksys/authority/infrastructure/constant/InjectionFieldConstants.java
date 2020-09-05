package com.sparksys.authority.infrastructure.constant;

/**
 * description: 仅仅用于记录 RemoteField 注解相关的 接口和方法名称
 * 切记，该类下的接口和方法，一定要自己手动创建，否则会注入失败
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 09:25:48
 */
public class InjectionFieldConstants {

    /**
     * 组织 仓储查询类
     */
    public static final String ORG_ID_CLASS = "coreOrgRepository";
    /**
     * 组织 feign查询类
     */
    public static final String ORG_ID_FEIGN_CLASS = "orgApi";

    /**
     * 组织 查询方法
     */
    public static final String ORG_ID_METHOD = "findOrgByIds";
    public static final String ORG_ID_NAME_METHOD = "findOrgNameByIds";


    /**
     * 用户 service查询类
     */
    public static final String USER_ID_CLASS = "userServiceImpl";
    /**
     * 用户 feign查询类
     */
    public static final String USER_ID_FEIGN_CLASS = "userApi";

    /**
     * 用户 查询方法
     */
    public static final String USER_ID_METHOD = "findUserByIds";
    public static final String USER_ID_NAME_METHOD = "findUserNameByIds";


    /**
     * 组织 仓储查询类
     */
    public static final String STATION_ID_CLASS = "coreStationRepository";
    /**
     * 组织 feign查询类
     */
    public static final String STATION_ID_FEIGN_CLASS = "stationApi";

    /**
     * 组织 查询方法
     */
    public static final String STATION_ID_METHOD = "findStationByIds";
    public static final String STATION_ID_NAME_METHOD = "findStationNameByIds";
}
