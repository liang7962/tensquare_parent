package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Administrator on 2019/11/1.
 */
public interface SpitDao extends MongoRepository<Spit, String> {

    /**
    * @Param:  根据上级ID查询吐槽列表（分页）
    * @return:  
    * @Author: liang 
    * @Date: 2019/11/1 
    */ 
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
