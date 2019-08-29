package org.oracle.note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.oracle.note.dao.NoteBookDao;
import org.oracle.note.dao.NoteDao;
import org.oracle.note.entity.Note;
import org.oracle.note.entity.NoteBook;
import org.oracle.note.entity.NoteResult;
import org.oracle.note.util.GetUUID;
import org.springframework.stereotype.Service;
@Service
public class NoteBookImpl implements NoteBookService {
	@Resource
	private NoteBookDao noteBookDao;
	@Resource
	private NoteDao noteDao;
	public NoteResult show(String id) {
		List<NoteBook> books = noteBookDao.findNoteBook(id);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("显示成功");
		result.setData(books);
		return result;
	}
	public NoteResult addNoteBook(String name,String id) {
		System.out.println(name+","+id);
		NoteResult result = new NoteResult();
		NoteBook noteBook = new NoteBook();
		noteBook.setCn_user_id(id);
		noteBook.setCn_notebook_name(name);
		GetUUID uuid = new GetUUID();
		String noteBook_id = uuid.getUuid();
		noteBook.setCn_notebook_id(noteBook_id);
		noteBook.setCn_notebook_desc("001");
		noteBook.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		noteBookDao.addNoteBook(noteBook);
		result.setStatus(0);
		result.setMsg("添加成功");
		result.setData(noteBook_id);
		return result;
	}
	public NoteResult updateNoteBookName(String bookId, String bookName) {
		NoteResult result = new NoteResult();
		NoteBook noteBook = new NoteBook();
		noteBook.setCn_notebook_id(bookId);
		noteBook.setCn_notebook_name(bookName);
		noteBookDao.updateNoteBookName(noteBook);
		result.setStatus(0);
		result.setMsg("重命名成功");
		return result;
	}
	public NoteResult deleteNoteBook(String notebookid) {
		NoteResult result = new NoteResult();
		List<Note> noteList = noteDao.findNote(notebookid);
		if(noteList.size()==0){
			noteBookDao.deleteNoteBook(notebookid);
			result.setStatus(0);
			result.setMsg("删除成功");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("笔记本中有内容不能删除");
			return result;
		}
	
	}
	public NoteResult checkNoteBook(String notebookid) {
		NoteResult result = new NoteResult();
		List<Note> checkList = noteDao.findNote(notebookid);
		if(checkList.size()>0){
			result.setStatus(0);
			result.setMsg("0");
			result.setData(checkList);
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("0");
			return result;
		}
		
	}
}
