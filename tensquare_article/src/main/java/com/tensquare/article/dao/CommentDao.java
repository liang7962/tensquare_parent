package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Administrator on 2019/11/1.
 */
public interface CommentDao extends MongoRepository<Comment, String> {

   /**
   * @Param: 根据文章ID查询评论列表 
   * @return:  
   * @Author: liang 
   * @Date: 2019/11/1 
   */ 
    public List<Comment> findByArticleid(String articleid);
}
