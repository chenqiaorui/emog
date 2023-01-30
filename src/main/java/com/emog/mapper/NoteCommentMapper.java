package com.emog.mapper;

import com.emog.model.NoteComment;
import com.emog.model.NoteCommentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoteCommentMapper {
    int countByExample(NoteCommentExample example);

    int deleteByExample(NoteCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoteComment record);

    int insertSelective(NoteComment record);

    List<NoteComment> selectByExample(NoteCommentExample example);

    NoteComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoteComment record, @Param("example") NoteCommentExample example);

    int updateByExample(@Param("record") NoteComment record, @Param("example") NoteCommentExample example);

    int updateByPrimaryKeySelective(NoteComment record);

    int updateByPrimaryKey(NoteComment record);
}