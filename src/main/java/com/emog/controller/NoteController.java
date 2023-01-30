package com.emog.controller;


import com.emog.common.CommonResult;
import com.emog.dto.PmsProductParam;
import com.emog.model.Note;
import com.emog.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "NoteController")
@Controller
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@ApiOperation("创建笔记")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody Note note) {
		int count = noteService.create(note);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

//	@ApiOperation("更新笔记？")
//	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
//	@ResponseBody
//	public CommonResult update(@PathVariable Integer id, @RequestBody Note note) {
//		int count = noteService.update(id, note);
//		if (count > 0) {
//			return CommonResult.success(count);
//		} else {
//			return CommonResult.failed();
//		}
//	}
//
	@ApiOperation("修改删除状态")
	@RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Integer> updateDeleteStatus(@RequestParam("ids") List<Integer> ids,
								@RequestParam("deleteStatus") Integer deleteStatus) {
		int count = noteService.updateDeleteStatus(ids, deleteStatus);
		return CommonResult.success(count);
	}

//	@ApiOperation("根据商品名称模糊查询")
//	@RequestMapping(value = "simpleList", method = RequestMethod.GET)
//	@ResponseBody
//	public CommonResult<List<Note>> getList(String keyword) {
//		List<Note> productList = noteService.list(keyword);
//		return CommonResult.success(productList);
//	}

	@ApiOperation("查询笔记")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
//	@PreAuthorize("hasAuthority('pms:product:list')")
	public CommonResult<List<Note>> getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
												  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<Note> list = noteService.list(pageSize, pageNum);
		return CommonResult.success(list);
	}
}
