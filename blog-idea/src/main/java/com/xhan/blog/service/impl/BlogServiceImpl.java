package com.xhan.blog.service.impl;

import com.xhan.blog.NotFoundException;
import com.xhan.blog.dao.TBlogMapper;
import com.xhan.blog.pojo.TBlog;
import com.xhan.blog.pojo.TBlogTags;
import com.xhan.blog.pojo.TTag;
import com.xhan.blog.service.BlogService;
import com.xhan.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private TBlogMapper tBlogMapper;

    @Override
    public TBlog getBlog(Long id) {
        return tBlogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TBlog> getAllBlog() {
        return tBlogMapper.getAllBlog();
    }

    @Override
    public List<TBlog> getIndexBlog() {
        return tBlogMapper.getIndexBlog();
    }

    @Override
    public List<TBlog> getBlogByTypeId(Long typeId) {
        return tBlogMapper.getBlogByTypeId(typeId);
    }

    @Override
    public List<TBlog> getBlogByTagId(Long tagId) {
        return tBlogMapper.getBlogByTagId(tagId);
    }

    @Override
    public List<TBlog> getListBold(TBlog blog) {
        return tBlogMapper.getListBold(blog);
    }

    @Override
    public List<TBlog> searchAllBlog(TBlog tBlog) {
        return tBlogMapper.searchAllBlog(tBlog);
    }

    @Override
    public List<TBlog> getAllRecommendBlog() {


        return tBlogMapper.getAllRecommendBlog();
    }

    @Override
    public int saveBlog(TBlog blog) {
        blog.setCreateTime(new Date());
        blog.setViews(0);
         tBlogMapper.insertSelective(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        System.out.println(id);
        //将标签的数据存到t_blogs_tag表中
        List<TTag> tags=blog.getTags();
        TBlogTags blogTags=null;
        for (TTag tag : tags ){
            blogTags=new TBlogTags(tag.getId(),id);
            tBlogMapper.saveBlogAndTag(blogTags);

        }
        return 1;
    }

    @Override
    public int updateByPrimaryKeySelective(TBlog tBlog) {

        TBlog blog=tBlogMapper.selectByPrimaryKey(tBlog.getId());
        if (blog==null){
            throw new NotFoundException("该博客信息不存在");
        }
        //将标签的数据存到t_blogs_tag表中
        List<TTag> tags = tBlog.getTags();
        TBlogTags blogTags=null;
        if (!tBlog.getTagIds().equals(blog.getTagIds())){
            tBlogMapper.deleteBlogAndTag(tBlog.getId());
            for (TTag tag :tags){
//                System.out.println(tag);
                blogTags=new TBlogTags(tag.getId(),tBlog.getId());
//            blogTags.setTagsId(tag.getId());
//            blogTags.setBlogsId(tBlog.getId());
                tBlogMapper.saveBlogAndTag(blogTags);
            }
        }
        return tBlogMapper.updateByPrimaryKeySelective(tBlog);
    }




    @Override
    public int deleteBlog(Long id) {
        return tBlogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TBlog> getSearchBlog(String query) {

        return tBlogMapper.getSearchBlog(query);
    }

    @Override
    public TBlog getDetailedBlog(Long id) {
        TBlog blog=tBlogMapper.getDetailedBlog(id);
        if (blog== null){
            throw new NotFoundException("博客不存在");
        }
        String content=blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public Map<String, List<TBlog>> archiveBlog() {
        List<String> yearList=tBlogMapper.getBlogYear();
        Set<String> set=new HashSet<>(yearList);
        Map<String,List<TBlog>> map =new HashMap<>();
        for (String year : yearList ){
            map.put(year,tBlogMapper.getBlogByYear(year));
        }
        return map;
    }

    @Override
    public int BlogCount() {
        return tBlogMapper.getAllBlog().size();
    }

    @Override
    public void updateView(TBlog blog) {
        blog.setViews(blog.getViews()+1);
        tBlogMapper.updateView(blog);
    }
}
