package com.xhan.blog.service.impl;

import com.xhan.blog.dao.TCommentMapper;
import com.xhan.blog.pojo.TComment;
import com.xhan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private TCommentMapper tCommentMapper;

    @Override
    public List<TComment> getCommentListBybBlogId(Long bid) {
        return tCommentMapper.getCommentListBybBlogId(bid);
    }

    @Override
    public int saveComment(TComment comment) {
        //获取父评论Id
        Long parentCommentId=comment.getParentComment().getId();
        //若没有父级评论,则为返回-1
        if (parentCommentId!=-1){
            comment.setParentComment(tCommentMapper.findByParentCommentId(parentCommentId));
            comment.setParentCommentId(parentCommentId);
        }else {
            comment.setParentCommentId((long)-1);
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return tCommentMapper.insert(comment);
    }
}
