package com.emog.service;

import com.emog.model.Note;
import java.util.List;

public interface NoteService {
	/**
	 * 创建
	 */
	int create(Note note);

	/**
	 * 更新
	 */
	int update(Long id, Note note);

	/**
	 * 分页查询
	 */
	List<Note> list(Integer pageSize, Integer pageNum);

	/**
	 * 批量更新删除状态
	 */
	int updateDeleteStatus(List<Integer> ids, Integer deleteStatus);

	/**
	 * 根据商品名称模糊查询
	 */
//	List<Note> list(String keyword);
}