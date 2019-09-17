package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 标签业务逻辑类
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;


    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }
    /**
     * 根据ID查询标签
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    /**
     * 增加标签
     * @param label
     */
    public void add(Label label){
        label.setId( idWorker.nextId()+"" );//设置ID
        labelDao.save(label);
    }
    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }
    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }


    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
            * @Param:  root:根对象     criteriaQuery：查询关键字     criteriaBuilder：封装条件对象
            * @return: java.util.List<com.tensquare.base.pojo.Label>
            * @Author: liang
            * @Date: 2019/9/17
            */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                if(null!=label){
                    if(StringUtils.isNotBlank(label.getLabelname())){
                        Predicate pd=criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                        list.add(pd);
                    }
                    if(StringUtils.isNotBlank(label.getState())){
                        Predicate pd=criteriaBuilder.equal(root.get("state").as(String.class),label.getState());
                        list.add(pd);
                    }
                }
                if(null==list || list.size()==0){
                    return criteriaBuilder.and(null);
                }
                Predicate[] predicates=new Predicate[list.size()];
                predicates=list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        });
    }


    public Page<Label> pageQuery(Label label, Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page*(page-1),size);//从0开始

        return labelDao.findAll(new Specification<Label>() {
            /**
             * @Param: root:根对象     criteriaQuery：查询关键字     criteriaBuilder：封装条件对象
             * @return: java.util.List<com.tensquare.base.pojo.Label>
             * @Author: liang
             * @Date: 2019/9/17
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (null != label) {
                    if (StringUtils.isNotBlank(label.getLabelname())) {
                        Predicate pd = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                        list.add(pd);
                    }
                    if (StringUtils.isNotBlank(label.getState())) {
                        Predicate pd = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                        list.add(pd);
                    }
                }
                if (null == list || list.size() == 0) {
                    return criteriaBuilder.and(null);
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        },pageable);
    }
}
