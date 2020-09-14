package org.taurus.service.sys;

import org.springframework.stereotype.Service;
import org.taurus.dao.sys.TAuthUrlDao;
import org.taurus.entity.sys.TAuthUrlEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TAuthUrlServiceImpl extends ServiceImpl<TAuthUrlDao, TAuthUrlEntity> implements TAuthUrlService {

}
