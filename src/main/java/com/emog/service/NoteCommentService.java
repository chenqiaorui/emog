package com.emog.service;

import com.emog.model.Note;
import com.emog.model.NoteComment;

import java.util.List;

public interface NoteCommentService {
	/**
	 * 创建
	 */
	int create(NoteComment noteComment);

	/**
	 * 更新
	 */
	int update(Long id, NoteComment noteComment);

	/**
	 * 分页查询
	 */
	List<NoteComment> list(Integer pageSize, Integer pageNum);

	/**
	 * 批量更新删除状态
	 */
	int updateDeleteStatus(List<Integer> ids, Integer deleteStatus);

}