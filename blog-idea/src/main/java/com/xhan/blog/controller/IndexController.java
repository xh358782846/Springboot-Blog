package com.xhan.blog.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhan.blog.NotFoundException;
import com.xhan.blog.pojo.TBlog;
import com.xhan.blog.pojo.TTag;
import com.xhan.blog.pojo.TType;
import com.xhan.blog.service.BlogService;
import com.xhan.blog.service.TagService;
import com.xhan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;


    //首页展示
    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                        Model model){
        PageHelper.startPage(pageNum,5);
        List<TBlog> blogs=blogService.getIndexBlog();

 //        System.out.println(blogs);
        List<TBlog> recommendBlog=blogService.getAllRecommendBlog(); //最新推荐
        PageInfo<TBlog> pageInfo=new PageInfo<TBlog>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("blogs",blogs);
        PageHelper.startPage(0,10);
        List<TType> types=typeService.getIndexBlogType(); //右边分类
        PageHelper.startPage(0,10);
        List<TTag> tags=tagService.getIndexBlogTag(); //右边标签

        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("recommendBlogs",recommendBlog);
        return "index";
    }

    //搜索按钮
    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                         Model model,@RequestParam String query){
        PageHelper.startPage(pageNum,8);
        List<TBlog> blogs=blogService.getSearchBlog(query);
        PageInfo<TBlog> pageInfo=new PageInfo<TBlog>(blogs);
        model.addAttribute("blogs",blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }



    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        TBlog blog=blogService.getDetailedBlog(id);
        blogService.updateView(blog);
        model.addAttribute("blog",blog);
        return "blog";
    }
}
