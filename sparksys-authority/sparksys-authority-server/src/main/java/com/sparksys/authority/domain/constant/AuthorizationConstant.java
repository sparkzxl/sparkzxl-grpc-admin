package com.sparksys.authority.domain.constant;


import com.sparksys.authority.infrastructure.enums.SexEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/7 9:19 上午
 */
public class AuthorizationConstant {

    public static final Map<Integer, String> SEX_MAP = new HashMap<Integer, String>(3) {
        {
            put(SexEnum.MAN.getCode(),SexEnum.MAN.getDesc());
            put(SexEnum.WOMAN.getCode(),SexEnum.WOMAN.getDesc());
            put(SexEnum.NONE.getCode(),SexEnum.NONE.getDesc());
        }
    };


}
