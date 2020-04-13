package com.xhan.blog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhan.blog.pojo.TTag;
import com.xhan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        String orderBy = "id desc"; //PageHelper方法 （排序字段 空格 排序方式）（注意：都是数据库中的字段，不是实体pojo的）
        PageHelper.startPage(pageNum,5,orderBy);
        List<TTag> tTags=tagService.getAllTag();
        PageInfo<TTag> pageInfo=new PageInfo<TTag>(tTags);
        model.addAttribute("tags",tTags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new TTag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid TTag tag, BindingResult result,
                       RedirectAttributes attributes){
        TTag tag1=tagService.getTagByName(tag.getName());
        if (tag1!=null){
            attributes.addFlashAttribute("message","用户名已存在");
            return "redirect:/admin/tags/input";
        }
        int num=tagService.saveTag(tag);
        if (num==0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid TTag tag,BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        TTag tag1=tagService.getTagByName(tag.getName());
        if (tag1!=null){
            attributes.addFlashAttribute("message","用户名已存在");
            return "redirect:/admin/tags/{id}/input";
        }
        int num=tagService.updateByPrimaryKeySelective(tag);
        if (num==0){
            attributes.addFlashAttribute("message","更新失败功");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
