package org.oracle.note.controller;

import javax.annotation.Resource;

import org.oracle.note.entity.NoteResult;
import org.oracle.note.service.NoteBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/notebook")
public class NoteBookController {
	@Resource
	private NoteBookService noteBookService;
	
	@RequestMapping("/show.do")
	@ResponseBody
	public NoteResult showBooks(String id){
		NoteResult result = noteBookService.show(id);
		return result;
	}
	
	
	@RequestMapping("/addNoteBook.do")
	@ResponseBody
	public NoteResult addNoteBook(String name,String id){
		NoteResult result = noteBookService.addNoteBook(name,id);
		return result;
	}
	@RequestMapping("/updateBookName.do")
	@ResponseBody
	public NoteResult updateNoteBookName(String bookId,String bookName){
		NoteResult result = noteBookService.updateNoteBookName(bookId, bookName);
		return result;
		
	}
	@RequestMapping("/checkNoteBook.do")
	@ResponseBody
	public NoteResult checkNoteBook(String notebookid){
		NoteResult result = noteBookService.checkNoteBook(notebookid);
		return result;
	}
	@RequestMapping("/deleteNoteBook.do")
	@ResponseBody
	public NoteResult deleteNoteBook(String notebookid){
		NoteResult result = noteBookService.deleteNoteBook(notebookid);
		return result;
	}
}
