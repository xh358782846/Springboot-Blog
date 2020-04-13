package com.xhan.blog.service.impl;

import com.xhan.blog.NotFoundException;
import com.xhan.blog.dao.TTagMapper;
import com.xhan.blog.pojo.TTag;
import com.xhan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TTagMapper tTagMapper;

    @Override
    public TTag getTagByName(String name) {
        return tTagMapper.selectByName(name);
    }

    @Override
    public int saveTag(TTag tag) {
        return tTagMapper.insertSelective(tag);
    }

    @Override
    public TTag getTag(Long id) {
        return tTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TTag> getAllTag() {
        return tTagMapper.getAllType();
    }

    @Override
    public List<TTag> getTagByString(String text) {    //从tagIds字符串中获取id，根据id获取tag集合
        List<TTag> tags=new ArrayList<>();
        List<Long> longs=convertToList(text);
        for (Long long1 : longs){
            tags.add(tTagMapper.selectByPrimaryKey(long1));
        }
        return tags;
    }

    @Override
    public List<TTag> getIndexBlogTag() {
        return tTagMapper.getIndexBlogTag();
    }

    @Override
    public List<TTag> getBlogTag() {
        return tTagMapper.getBlogTag();
    }

    private List<Long> convertToList(String ids){
        List<Long> list=new ArrayList<>();
        if (!"".equals(ids)&& ids != null){
            String [] idarray=ids.split(",");
            for (int i = 0; i <idarray.length ; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }


    @Override
    public int updateType(TTag tag) {
        TTag t1=getTag(tag.getId());
        if (t1==null){
            throw  new NotFoundException("不存在该标签");
        }
        return tTagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public int updateByPrimaryKeySelective(TTag tag) {
//        TTag t1=getTag(tag.getId());
//        if (t1==null){
//            throw  new NotFoundException("不存在该标签");
//        }
        return tTagMapper.updateByPrimaryKey(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tTagMapper.deleteByPrimaryKey(id);
    }
}
