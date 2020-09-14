package org.taurus.service.sys;

import org.springframework.stereotype.Service;
import org.taurus.dao.sys.TMCodeDao;
import org.taurus.entity.sys.TMCodeEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TMCodeServiceImpl extends ServiceImpl<TMCodeDao, TMCodeEntity> implements TMCodeService {

}
