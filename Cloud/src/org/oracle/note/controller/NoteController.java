package org.oracle.note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.oracle.note.entity.NoteResult;
import org.oracle.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	private NoteService noteService; 
	
	//显示笔记
	@RequestMapping("/noteBooks.do")
	@ResponseBody
	public NoteResult showNote(String id){
		NoteResult result = noteService.showBooks(id);
		return result;
	}
	
	//添加笔记
	@RequestMapping("/addNote.do")
	@ResponseBody
	public NoteResult addNote(String userId,String bookId,String bookTitle){
		NoteResult result = noteService.addNote(userId, bookId, bookTitle);
		return result;
	}
	
	
	//显示笔记内容
	@RequestMapping("/showBody.do")
	@ResponseBody
	public NoteResult showBody(String noteId){
		NoteResult result = noteService.showBody(noteId);
		return result;
	
	}
	@RequestMapping("/updateNote.do")
	@ResponseBody
	public NoteResult updateNote(String noteId,String title,String body){
		NoteResult result = noteService.updateNote(noteId,title,body);
		return result;
	
	}
	
	@RequestMapping("/updateStatus.do")
	@ResponseBody
	public NoteResult updateStatus(String noteId){
		NoteResult result = noteService.updateStatus(noteId);
		return result;
	}
	@RequestMapping("/recycle.do")
	@ResponseBody
	public NoteResult recycle(){
		NoteResult result = noteService.recycle();
		return result;
	}
	@RequestMapping("/deleteNote.do")
	@ResponseBody
	public NoteResult deleteNote(String id){
		NoteResult result = noteService.deleteNote(id);
		return result;
	}
	@RequestMapping("/recoverNote.do")
	@ResponseBody
	public NoteResult recoverNote(String notebookid,String noteId){
		NoteResult result = noteService.recoverNote(noteId, notebookid);
		return result;
	}
	@RequestMapping("/collect.do")
	@ResponseBody
	public NoteResult collect(String noteId){
		
		NoteResult result = noteService.collect(noteId);
		return result;
	}
	//显示收藏笔记本
	@RequestMapping("/findCollect.do")
	@ResponseBody
	public NoteResult findCollect(){
		NoteResult result = noteService.findCollect();
		return result;
	}
	
}
