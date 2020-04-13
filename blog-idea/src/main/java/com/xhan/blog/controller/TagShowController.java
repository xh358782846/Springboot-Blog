package com.xhan.blog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhan.blog.pojo.TBlog;
import com.xhan.blog.pojo.TTag;
import com.xhan.blog.service.BlogService;
import com.xhan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                       @PathVariable Long id, Model model){
        PageHelper.startPage(pageNum,100);
        List<TTag> tags=tagService.getBlogTag();
        if (id==-1){
            id=tags.get(0).getId();
        }
        List<TBlog> blogs=blogService.getBlogByTagId(id);

        PageInfo<TBlog> pageInfo=new PageInfo<TBlog>(blogs);
        model.addAttribute("blogs",blogs);
        model.addAttribute("tags",tags);
        model.addAttribute("activeTagId",id);
        model.addAttribute("pageInfo",pageInfo);

        return "tags";
    }

}
