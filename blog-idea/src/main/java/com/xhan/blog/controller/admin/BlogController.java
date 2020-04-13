package com.xhan.blog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhan.blog.pojo.BlogQuery;
import com.xhan.blog.pojo.TBlog;
import com.xhan.blog.pojo.TUser;
import com.xhan.blog.service.BlogService;
import com.xhan.blog.service.TagService;
import com.xhan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
    }

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum, Model model){
        String orderBy = "update_Time desc"; //PageHelper方法 （排序字段 空格 排序方式）（注意：都是数据库中的字段，不是实体pojo的）
        PageHelper.startPage(pageNum,5,orderBy);
        List<TBlog> allBlog=blogService.getAllBlog();
        PageInfo<TBlog> pageInfo=new PageInfo<TBlog>(allBlog);
        model.addAttribute("blogs",allBlog);
        model.addAttribute("pageInfo",pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    //条件查询
    @PostMapping("/blogs/search")
    public String searchBlogs(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                              BlogQuery bg, Model model){
//        String orderBy = "update_Time desc"; //PageHelper方法 （排序字段 空格 排序方式）（注意：都是数据库中的字段，不是实体pojo的）
        PageHelper.startPage(pageNum,5);
        TBlog blog=new TBlog();
        blog.setTitle(bg.getTitle());
        blog.setTypeId(bg.getTypeId());
        blog.setRecommend(bg.isRecommend());
        System.out.println(blog);
        List<TBlog> allBlog=blogService.searchAllBlog(blog);
        System.out.println(allBlog);
        PageInfo<TBlog> pageInfo=new PageInfo<TBlog>(allBlog);
        model.addAttribute("blogs",allBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message","查询成功");
        setTypeAndTag(model);
        return "admin/blogs";
    }

    @GetMapping("/blogs/input") //去新增博客页面
    public String input(Model model){
        model.addAttribute("blog", new TBlog());  //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        TBlog blog=blogService.getBlog(id);

        blog.init(); //将tags集合转换为tagIds字符串
        System.out.println(blog.getTags());
        System.out.println(blog);
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String saveBlog(TBlog blog, HttpSession session,
                           RedirectAttributes attributes){
        //将当前作者名字输入
        blog.setUser((TUser)session.getAttribute("user"));
        //设置用户ID
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog的TypeId属性
        blog.setTypeId(blog.getType().getId());
//        给blog的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));

        if (blog.getId()==null){       //若Id为空，则为新文章，操作为添加
            blogService.saveBlog(blog);
            attributes.addFlashAttribute("message","新增成功");
        }else {
            System.out.println(blog.getTagIds());
            blogService.updateByPrimaryKeySelective(blog);
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";

    }




}
