package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * Created by Administrator on 2019/11/1.
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /*** 查询全部记录 * @return */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * @Param:
     * @return: 根据主键查询实体
     * @Author: liang
     * @Date: 2019/11/1
     */
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    /**
     * @Param:
     * @return: 新增
     * @Author: liang
     * @Date: 2019/11/1
     */
    public void add(Spit spit) {
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            // 如果存在上级ID,评论
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }

        spitDao.insert(spit);
    }

    /**
     * @Param:
     * @return: 修改
     * @Author: liang
     * @Date: 2019/11/1
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * @Param:
     * @return: 删除
     * @Author: liang
     * @Date: 2019/11/1
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    /**
     * @Param: 根据上级ID查询吐槽列表
     * @return:
     * @Author: liang
     * @Date: 2019/11/1
     */
    public Page<Spit> findByParentid(String parentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentId, pageRequest);
    }

    /**
     * @Param: 点赞--效率低下
     * @return:
     * @Author: liang
     * @Date: 2019/11/1
     */
    public void updateThumbup2(String id) {
        Spit spit = spitDao.findById(id).get();
        spit.setThumbup(spit.getThumbup() + 1);
        spitDao.save(spit);
    }

    /**
     * @Param: 点赞
     * @return:
     * @Author: liang
     * @Date: 2019/11/1
     */
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
