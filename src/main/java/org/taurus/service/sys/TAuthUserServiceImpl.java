package org.taurus.service.sys;

import org.springframework.stereotype.Service;
import org.taurus.dao.sys.TAuthUserDao;
import org.taurus.entity.sys.TAuthUserEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TAuthUserServiceImpl extends ServiceImpl<TAuthUserDao, TAuthUserEntity> implements TAuthUserService {

}
