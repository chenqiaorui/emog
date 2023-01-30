package com.emog.service.impl;

import com.emog.mapper.NoteCommentMapper;
import com.emog.mapper.NoteMapper;
import com.emog.model.*;
import com.emog.service.NoteCommentService;
import com.emog.service.NoteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 标注Service作为一个service能被@Autowired
public class NoteCommentServiceImpl implements NoteCommentService {
    @Autowired
    private NoteCommentMapper noteCommentMapper;

    @Override
    public int create(NoteComment note) {
//        Note product = note;
//        product.setId(null);
        System.out.println(note);
        int count = noteCommentMapper.insertSelective(note);
        return count;
    }

    @Override
    public int update(Long id, NoteComment note) {
//        Note product = note;
//        note.setId(id);
//        System.out.println(note);
        noteCommentMapper.updateByPrimaryKeySelective(note);
        return 1;
    }

    @Override
    public List<NoteComment> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        NoteCommentExample noteCommentExample = new NoteCommentExample();
        NoteCommentExample.Criteria criteria = noteCommentExample.createCriteria();
        criteria.andIsDeleteEqualTo(0);
        return noteCommentMapper.selectByExample(noteCommentExample);
    }

    @Override
    public int updateDeleteStatus(List<Integer> ids, Integer deleteStatus) {
        NoteComment record = new NoteComment();
        record.setIsDelete(deleteStatus);
        NoteCommentExample example = new NoteCommentExample();
        example.createCriteria().andIdIn(ids);
        return noteCommentMapper.updateByExampleSelective(record, example);
    }

}
