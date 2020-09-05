package com.sparksys.authority.infrastructure.repository;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparksys.authority.domain.repository.ICoreOrgRepository;
import com.sparksys.authority.infrastructure.entity.CoreOrg;
import com.sparksys.authority.infrastructure.mapper.CoreOrgMapper;
import com.sparksys.core.utils.MapHelper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: 组织 仓储层实现类
 *
 * @author zhouxinlei
 * @date 2020-06-07 13:32:24
 */
@Repository
public class CoreOrgRepository implements ICoreOrgRepository {

    private final CoreOrgMapper coreOrgMapper;

    public CoreOrgRepository(CoreOrgMapper coreOrgMapper) {
        this.coreOrgMapper = coreOrgMapper;
    }

    @Override
    public Map<Serializable, Object> findOrgByIds(Set<Serializable> ids) {
        List<CoreOrg> orgList = getOrgs(ids);
        return MapHelper.uniqueIndex(orgList, CoreOrg::getId, (org) -> org);
    }

    private List<CoreOrg> getOrgs(Set<Serializable> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> idList = ids.stream().mapToLong(Convert::toLong).boxed().collect(Collectors.toList());
        List<CoreOrg> list;
        int size = 1000;
        if (idList.size() <= size) {
            list = idList.stream().map(this.coreOrgMapper::selectById).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            list = this.coreOrgMapper.selectList(new QueryWrapper<CoreOrg>().lambda().in(CoreOrg::getId, idList).eq(CoreOrg::getStatus, true));
        }
        return list;
    }
}
