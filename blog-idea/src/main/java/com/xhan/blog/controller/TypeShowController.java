package com.xhan.blog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhan.blog.pojo.TBlog;
import com.xhan.blog.pojo.TType;
import com.xhan.blog.service.BlogService;
import com.xhan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                        @PathVariable Long id, Model model){

        PageHelper.startPage(pageNum,100);
        List<TType> types=typeService.getBlogType();
        if (id==-1){
            //得到排序第一的ID
            id=types.get(0).getId();
        }
        List<TBlog> blogs=blogService.getBlogByTypeId(id);
        PageInfo<TBlog> pageInfo=new PageInfo<TBlog>(blogs);
        model.addAttribute("blogs",blogs);
        model.addAttribute("types",types);
        //当前选中ID
        model.addAttribute("activeTypeId",id);
        model.addAttribute("pageInfo",pageInfo);

        return "types";
    }
}
