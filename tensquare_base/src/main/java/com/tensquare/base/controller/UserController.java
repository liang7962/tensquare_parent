package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 标签控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class UserController {

    @Autowired
    private LabelService labelService;
    
    
    /**
    * @Param:  查询全部列表
    * @return:  
    * @Author: liang 
    * @Date: 2019/9/17 
    */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll(){
        return new Result<>(true, StatusCode.OK,"查询成功",
                labelService.findAll() );
    }
    
    /**
    * @Param:  根据ID查询标签
    * @return:  
    * @Author: liang 
    * @Date: 2019/9/17 
    */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result<List> findById(@PathVariable String id){
        return new Result<>(true,StatusCode.OK,"查询成 功",labelService.findById(id));
    }
    
    /**
    * @Param:  增加标签
    * @return:  
    * @Author: liang 
    * @Date: 2019/9/17 
    */
    @RequestMapping(method = RequestMethod.POST)
    public Result add( @RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"增加成功");
    }
    
    /**
    * @Param:
    * @return:  修改标签
    * @Author: liang 
    * @Date: 2019/9/17 
    */
    @RequestMapping(value="/{id}" ,method = RequestMethod.PUT)
    public Result update( @RequestBody Label label,@PathVariable String
            id){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * @Param:
     * @return:  删除标签
     * @Author: liang
     * @Date: 2019/9/17
     */
    @RequestMapping(value="/{id}" ,method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list=labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label,@PathVariable Integer page,@PathVariable Integer size){
       Page<Label> pageData=labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
