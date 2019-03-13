package com.dhcc.ms.ims.service.instance.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dao.InstanceInfoDao;
import com.dhcc.ms.ims.dto.request.DowntimeInstaneInfoDtoPageReq;
import com.dhcc.ms.ims.dto.request.ExceptionWarnDtoPageReq;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.ims.po.InstanceInfo.RunningStatus;
import com.dhcc.ms.ims.service.instance.InstancesFinder;
import com.dhcc.ms.utils.dto.ListRespDto;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InstanceFinderImpl implements InstancesFinder {
    @Autowired
    private InstanceInfoDao dao;

    @Override
    public ListRespDto<InstanceInfo> getDowntimeInstancesByPage(DowntimeInstaneInfoDtoPageReq page)
            throws CommonException {
        PageHelper.startPage(page.getPageIndex(), page.getPageSize());

        Example example = new Example(InstanceInfo.class);

        if (!StringUtils.isEmpty(page.getFilter())) {
            example.or().andNotEqualTo("status", RunningStatus.UP).andLike("appName", "%" + page.getFilter() + "%");
            example.or().andNotEqualTo("status", RunningStatus.UP).andLike("registeId", "%" + page.getFilter() + "%");
            example.or().andNotEqualTo("status", RunningStatus.UP).andLike("serviceUrl", "%" + page.getFilter() + "%");
        }else {
            example.or().andNotEqualTo("status", RunningStatus.UP);
        }
        example.setOrderByClause("appName");

        List<InstanceInfo> infos = dao.selectByExample(example);

        PageInfo<InstanceInfo> pageInfo = new PageInfo<InstanceInfo>(infos);

        return new ListRespDto<InstanceInfo>(infos, pageInfo.getTotal());
    }

    @Override
    public int getDowntimeInstancesCount(long startTimestamp, long endTimestamp) {
        Example example = new Example(InstanceInfo.class);
        example.or().andNotEqualTo("status", RunningStatus.UP)
                .andGreaterThanOrEqualTo("statusStartTimestamp", new Date(startTimestamp))
                .andLessThan("statusStartTimestamp", new Date(endTimestamp));
        return dao.selectCountByExample(example);
    }

    @Override
    public Set<String> allInstanceIds() {
        return dao.selectAllInstanceId();
    }

    @Override
    public InstanceInfo instance(String instanceId) {
        return dao.selectByPrimaryKey(instanceId);
    }

    @Override
    public ListRespDto<InstanceInfo> instances(ExceptionWarnDtoPageReq page) {
        PageHelper.startPage(page.getPageIndex(), page.getPageSize());

        Example example = new Example(InstanceInfo.class);
        if (!StringUtils.isEmpty(page.getFilter())) {
            example.or().andLike("appName", "%" + page.getFilter() + "%");
            example.or().andLike("registeId", "%" + page.getFilter() + "%");
            example.or().andLike("status", "%" + page.getFilter() + "%");
        }
        example.setOrderByClause("appName");

        List<InstanceInfo> infos = dao.selectByExample(example);
        PageInfo<InstanceInfo> pageInfo = new PageInfo<InstanceInfo>(infos);

        return new ListRespDto<InstanceInfo>(infos, pageInfo.getTotal());
    }

    @Override
    public ListRespDto<InstanceInfo> instancesWeight(ExceptionWarnDtoPageReq page) {
        PageHelper.startPage(page.getPageIndex(), page.getPageSize());

        Example example = new Example(InstanceInfo.class);
        if (!StringUtils.isEmpty(page.getFilter())) {
            example.or().andLike("appName", "%" + page.getFilter() + "%").andEqualTo("status", "UP");
            example.or().andLike("registeId", "%" + page.getFilter() + "%").andEqualTo("status", "UP");
        } else {
            example.createCriteria().andEqualTo("status", "UP");
        }
        
        example.setOrderByClause("appName");

        List<InstanceInfo> infos = dao.selectByExample(example);
        PageInfo<InstanceInfo> pageInfo = new PageInfo<InstanceInfo>(infos);

        return new ListRespDto<InstanceInfo>(infos, pageInfo.getTotal());
    }

}
