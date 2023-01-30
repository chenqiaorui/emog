package com.emog.controller;


import com.emog.common.CommonResult;
import com.emog.model.NoteComment;
import com.emog.service.NoteCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "NoteCommentController")
@Controller
@RequestMapping("/noteComment")
public class NoteCommentController {

	@Autowired
	private NoteCommentService noteCommentService;

	@ApiOperation("创建评论")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody NoteComment comment) {
		int count = noteCommentService.create(comment);
		if (count > 0) {
			return CommonResult.success(count);
		} else {
			return CommonResult.failed();
		}
	}

	@ApiOperation("修改删除状态")
	@RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Integer> updateDeleteStatus(@RequestParam("ids") List<Integer> ids,
								@RequestParam("deleteStatus") Integer deleteStatus) {
		int count = noteCommentService.updateDeleteStatus(ids, deleteStatus);
		return CommonResult.success(count);
	}

	@ApiOperation("查询评论")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<NoteComment>> getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
												  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<NoteComment> list = noteCommentService.list(pageSize, pageNum);
		return CommonResult.success(list);
	}
}
