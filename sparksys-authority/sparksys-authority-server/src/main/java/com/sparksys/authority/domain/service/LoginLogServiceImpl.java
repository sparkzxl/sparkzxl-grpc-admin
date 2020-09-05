package com.sparksys.authority.domain.service;

import cn.hutool.core.util.StrUtil;
import com.sparksys.authority.application.service.ILoginLogService;
import com.sparksys.authority.domain.repository.IAuthUserRepository;
import com.sparksys.authority.domain.repository.ILoginLogRepository;
import com.sparksys.authority.infrastructure.constant.CacheConstant;
import com.sparksys.authority.infrastructure.entity.AuthUser;
import com.sparksys.authority.infrastructure.entity.LoginLog;
import com.sparksys.authority.infrastructure.entity.LoginLogCount;
import com.sparksys.authority.infrastructure.mapper.LoginLogMapper;
import com.sparksys.core.entity.UserAgentEntity;
import com.sparksys.core.utils.KeyUtils;
import com.sparksys.database.base.service.impl.AbstractSuperCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * description：系统日志 服务实现类
 *
 * @author zhouxinlei
 * @date 2020/6/17 0017
 */
@Service
public class LoginLogServiceImpl extends AbstractSuperCacheServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Autowired
    private IAuthUserRepository authUserRepository;

    @Autowired
    private ILoginLogRepository loginLogRepository;

    private final static String[] BROWSER = new String[]{
            "Chrome", "Firefox", "Microsoft Edge", "Safari", "Opera"
    };
    private final static String[] OPERATING_SYSTEM = new String[]{
            "Android", "Linux", "Mac OS X", "Ubuntu", "Windows 10", "Windows 8", "Windows 7", "Windows XP", "Windows Vista"
    };

    private static String simplifyOperatingSystem(String operatingSystem) {
        for (String b : OPERATING_SYSTEM) {
            if (StrUtil.containsIgnoreCase(operatingSystem, b)) {
                return b;
            }
        }
        return operatingSystem;
    }

    private static String simplifyBrowser(String browser) {
        for (String b : BROWSER) {
            if (StrUtil.containsIgnoreCase(browser, b)) {
                return b;
            }
        }
        return browser;
    }

    @Override
    public void save(Long userId, String account, UserAgentEntity userAgentEntity, String description) {
        AuthUser authUser;
        if (userId != null) {
            authUser = authUserRepository.selectById(userId);
        } else {
            authUser = authUserRepository.selectByAccount(account);
        }
        LoginLog loginLog = LoginLog.builder()
                .location(userAgentEntity.getLocation())
                .loginDate(LocalDate.now())
                .description(description)
                .requestIp(userAgentEntity.getRequestIp()).ua(userAgentEntity.getUa())
                .browser(userAgentEntity.getBrowser())
                .browserVersion(userAgentEntity.getBrowserVersion())
                .operatingSystem(userAgentEntity.getOperatingSystem())
                .build();
        if (authUser != null) {
            loginLog.setAccount(authUser.getAccount()).setUserId(authUser.getId()).setUserName(authUser.getName())
                    .setCreateUser(authUser.getId());
        }
        loginLogRepository.saveLoginLog(loginLog);
        LocalDate now = LocalDate.now();
        LocalDate tenDays = now.plusDays(-9);
        cacheTemplate.remove(CacheConstant.LOGIN_LOG_TOTAL);
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TODAY, now));
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TODAY_IP, now));
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_BROWSER));
        cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_SYSTEM));
        if (authUser != null) {
            cacheTemplate.remove(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TEN_DAY, tenDays, account));
        }
    }

    @Override
    public Long findTotalVisitCount() {
        return cacheTemplate.get(CacheConstant.LOGIN_LOG_TOTAL);
    }

    @Override
    public Long findTodayVisitCount() {
        LocalDate now = LocalDate.now();
        return cacheTemplate.get(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TODAY, now));
    }

    @Override
    public Long findTodayIp() {
        LocalDate now = LocalDate.now();
        return cacheTemplate.get(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TODAY_IP, now));
    }

    @Override
    public List<LoginLogCount> findLastTenDaysVisitCount(String account) {
        LocalDate tenDays = LocalDate.now().plusDays(-9);
        return cacheTemplate.get(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_TEN_DAY, tenDays, account),
                (key) -> loginLogRepository.findLastTenDaysVisitCount(tenDays, account));
    }

    @Override
    public List<LoginLogCount> findByBrowser() {
        return cacheTemplate.get(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_BROWSER),
                (key) -> loginLogRepository.findByBrowser());
    }

    @Override
    public List<LoginLogCount> findByOperatingSystem() {
        return cacheTemplate.get(KeyUtils.buildKey(CacheConstant.LOGIN_LOG_SYSTEM),
                (key) -> loginLogRepository.findByOperatingSystem());
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return loginLogRepository.clearLog(clearBeforeTime, clearBeforeNum);
    }

    @Override
    protected String getRegion() {
        return null;
    }
}
