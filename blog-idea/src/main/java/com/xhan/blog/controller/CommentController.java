package com.xhan.blog.controller;


import com.xhan.blog.pojo.TComment;
import com.xhan.blog.pojo.TUser;
import com.xhan.blog.service.BlogService;
import com.xhan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String commentList(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.getCommentListBybBlogId(blogId));
        model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }

//    发送评论
    @PostMapping("/comments")
    public String post(TComment comment, HttpSession session){
        System.out.print(comment);
        Long blogId=comment.getBlog().getId();
        comment.setBlog(blogService.getDetailedBlog(blogId));
        comment.setBlogId(blogId);
        TUser user=(TUser)session.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }
//        comment.setAvatar(avatar);
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }

}