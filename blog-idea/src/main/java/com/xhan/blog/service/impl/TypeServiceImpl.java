package com.xhan.blog.service.impl;

import com.github.pagehelper.Page;
import com.xhan.blog.NotFoundException;
import com.xhan.blog.dao.TTypeMapper;
import com.xhan.blog.pojo.TType;
import com.xhan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TTypeMapper tTypeMapper;

    @Override
    public List<TType> getAllType() {

        return tTypeMapper.getAllType();
    }

    @Override
    public List<TType> getIndexBlogType() {
        return tTypeMapper.getIndexBlogType();
    }

    @Override
    public List<TType> getBlogType() {
        return tTypeMapper.getBlogType();
    }

    @Override
    public TType getTypeByName(String name) {

        return tTypeMapper.selectByName(name);
    }

    @Override
    public int saveType(TType type) {

        return tTypeMapper.insertSelective(type);
    }

    @Override
    public TType getType(Long id) {

        return tTypeMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateType( TType type) {
        TType t1=getType(type.getId());
        if (t1==null){
            throw new NotFoundException("不存在该类型");
        }
        return tTypeMapper.updateByPrimaryKey(type);
    }

    @Override
    public int updateByPrimaryKeySelective(TType type) {

        return tTypeMapper.updateByPrimaryKey(type);
    }

    @Override
    public int deleteType(Long id) {
        return tTypeMapper.deleteByPrimaryKey(id);
    }
}
