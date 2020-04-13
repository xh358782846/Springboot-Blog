package com.xhan.blog.service;

import com.xhan.blog.pojo.TComment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {

    List<TComment> getCommentListBybBlogId(Long bid);
    int saveComment(TComment comment);
}
