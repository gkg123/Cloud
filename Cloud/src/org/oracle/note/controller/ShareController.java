package org.oracle.note.controller;

import javax.annotation.Resource;

import org.oracle.note.entity.NoteResult;
import org.oracle.note.service.ShareService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareService shareService;
	@RequestMapping("/addShare.do")
	@ResponseBody
	public NoteResult addShare(String noteId,String title,String body){
		NoteResult result = shareService.addShare(noteId, title, body);
		return result;
	}
	@RequestMapping("/findTitle.do")
	@ResponseBody
	public NoteResult findTitle(String noteName){
		NoteResult result = shareService.findTitle(noteName);
		return result;
	}

}
