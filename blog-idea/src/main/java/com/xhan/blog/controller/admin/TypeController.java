package com.xhan.blog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhan.blog.pojo.TType;
import com.xhan.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        String orderBy = "id desc"; //PageHelper方法 （排序字段 空格 排序方式）（注意：都是数据库中的字段，不是实体pojo的）
        PageHelper.startPage(pageNum,5,orderBy);
        List<TType> tTypes=typeService.getAllType();
        PageInfo<TType> pageInfo=new PageInfo<TType>(tTypes);
        model.addAttribute("types",tTypes);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new TType());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid TType type, BindingResult result, RedirectAttributes attributes){
        TType type1=typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message","用户名已存在");
            return "redirect:/admin/types/input";
        }
        int num = typeService.saveType(type);
        if (num == 0 ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid TType type,BindingResult bindingResult,@PathVariable Long id, RedirectAttributes attributes){
        TType type1=typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message","用户名已存在");
            return "redirect:/admin/types/{id}/input";

        }
        int num= typeService.updateByPrimaryKeySelective(type);
        if (num==0){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }





}
