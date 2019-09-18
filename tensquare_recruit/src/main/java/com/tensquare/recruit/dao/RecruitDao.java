package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /*查询最新热门企业列表*/
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);


    List<Recruit> findTop12ByStateNotOrOrderByCreatetimeDesc(String state);
}
