package com.dhcc.ms.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dao.ExceptionWarnDao;
import com.dhcc.ms.ims.dto.request.ExceptionWarnDtoPageReq;
import com.dhcc.ms.ims.dto.response.ExceptionWarnDtoResp;
import com.dhcc.ms.ims.po.ExceptionWarn;
import com.dhcc.ms.ims.service.ExceptionWarnService;
import com.dhcc.ms.utils.ErrorCodes;
import com.dhcc.ms.utils.dto.ListRespDto;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ExceptionWarnServiceImpl implements ExceptionWarnService {
    @Autowired
    private ExceptionWarnDao exceptionWarnDao;

    @Override
    public ListRespDto<ExceptionWarnDtoResp> getExceptionWarnsByPage(ExceptionWarnDtoPageReq page)
            throws CommonException {
        PageHelper.startPage(page.getPageIndex(), page.getPageSize());
        Example example = new Example(ExceptionWarn.class);
        if(!StringUtils.isEmpty(page.getFilter())) {
            example.or().andLike("httpPath", "%"+page.getFilter()+"%");
            example.or().andLike("error", "%"+page.getFilter()+"%");
            example.or().andLike("traceId", "%"+page.getFilter()+"%");
        }
        example.setOrderByClause("timestamp desc");
        List<ExceptionWarn> warnlist = exceptionWarnDao.selectByExample(example);
        if(warnlist.isEmpty()) {
            throw new CommonException(ErrorCodes.DATA_NOT_EXIST_CODE_KEY,ErrorCodes.DATA_NOT_EXIST_MSG_KEY);
        }
        PageInfo<ExceptionWarn> pageInfo = new PageInfo<ExceptionWarn>(warnlist);
        List<ExceptionWarnDtoResp> warnDtoList = new ArrayList<>();
        for(ExceptionWarn warn:warnlist){
            ExceptionWarnDtoResp warnDto = new ExceptionWarnDtoResp();
            BeanUtils.copyProperties(warn,warnDto);
            warnDtoList.add(warnDto);
        }
        ListRespDto<ExceptionWarnDtoResp> warnListResp = new ListRespDto<ExceptionWarnDtoResp>(warnDtoList,pageInfo.getTotal());
        return warnListResp;
    }

    @Override
    public int addExceptionWarn(ExceptionWarn ewarn) throws CommonException {
        return exceptionWarnDao.insert(ewarn);
    }
    
    @Override
    public int getNewExceptionWarnsCount(String startTimestamp, String endTimrestamp) throws CommonException {
        return exceptionWarnDao.getNewExceptionWarnsCount(startTimestamp, endTimrestamp);
    }
    
}
