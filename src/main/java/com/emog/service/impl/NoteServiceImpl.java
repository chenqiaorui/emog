package com.emog.service.impl;

import com.emog.mapper.NoteMapper;
import com.emog.model.Note;
import com.emog.model.NoteExample;
import com.emog.service.NoteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 标注NoteService作为一个service能被@Autowired
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteMapper noteMapper;

    @Override
    public int create(Note note) {
//        Note product = note;
//        product.setId(null);
        System.out.println(note);
        int count = noteMapper.insertSelective(note);
        return count;
    }

    @Override
    public int update(Long id, Note note) {
//        Note product = note;
//        note.setId(id);
//        System.out.println(note);
        noteMapper.updateByPrimaryKeySelective(note);
        return 1;
    }

    @Override
    public List<Note> list(Integer pageSize, Integer pageNum) {
        return null;
    }


    @Override
    public int updateDeleteStatus(List<Integer> ids, Integer deleteStatus) {
        Note record = new Note();
        record.setIsDelete(deleteStatus);
        NoteExample example = new NoteExample();
        example.createCriteria().andIdIn(ids);
        return noteMapper.updateByExampleSelective(record, example);
    }
//
//    @Override
//    public List<Note> list(String keyword) {
//        NoteExample noteExample = new NoteExample();
//        NoteExample.Criteria criteria = noteExample.createCriteria();
//        criteria.andDeleteStatusEqualTo(0);
//        if(!StrUtil.isEmpty(keyword)){
//            criteria.andNameLike("%" + keyword + "%");
//            noteExample.or().andDeleteStatusEqualTo(0);
//        }
//        return noteMapper.selectByExample(noteExample);
//    }

}
