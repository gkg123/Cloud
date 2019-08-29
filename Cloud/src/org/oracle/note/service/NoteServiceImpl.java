package org.oracle.note.service;

import java.util.List;


import javax.annotation.Resource;

import org.oracle.note.dao.NoteDao;
import org.oracle.note.entity.Note;
import org.oracle.note.entity.NoteResult;
import org.oracle.note.util.GetUUID;
import org.springframework.stereotype.Service;
@Service
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDao noteDao;
	//��ʾ�ʼ�
	public NoteResult showBooks(String id) {
		NoteResult result = new NoteResult();
		List<Note> list = noteDao.findBooks(id);
	
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}
	//��ӱʼ�
	public NoteResult addNote(String userId, String noteBookid, String noteTitle) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(noteBookid);
		note.setCn_note_title(noteTitle);
		GetUUID uuid = new GetUUID();
		String note_id = uuid.getUuid();
		note.setCn_note_id(note_id);
		note.setCn_note_status_id("1");
		note.setCn_note_type_id("5");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_body("");
		noteDao.addNote(note);
		result.setStatus(0);
		result.setMsg("��ӱʼǳɹ�");
		result.setData(note_id);
		return result;
	}
	//��ʾ�ʼ�����
	public NoteResult showBody(String noteId) {
		NoteResult result = new NoteResult();
		Note note = noteDao.showBody(noteId);
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		result.setData(note);
		return result;
	}
	public NoteResult updateNote(String noteId,String title,String body) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_body(body);
		note.setCn_note_title(title);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteDao.updateNote(note);
		result.setStatus(0);
		result.setMsg("�޸ĳɹ�");
		result.setData(title);
		return result;
	}
	//ɾ���ʼ�
	public NoteResult updateStatus(String noteId) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		noteDao.updateStatus(note);
		result.setStatus(0);
		result.setMsg("ɾ���ɹ�");
		return result;
	}
	//����վ
	public NoteResult recycle() {
		NoteResult result = new  NoteResult();
		List<Note> list = noteDao.recycle();
		result.setStatus(0);
		result.setMsg("����վ");
		result.setData(list);
		return result;
	}
	//����վɾ��
	public NoteResult deleteNote(String id) {
		NoteResult result = new NoteResult();
		noteDao.deleteNote(id);
		result.setStatus(0);
		result.setMsg("��ɾ��(���ɻָ�)");
		return result;
	}
	public NoteResult recoverNote(String noteId,String notebookid) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_notebook_id(notebookid);
		note.setCn_note_id(noteId);
		noteDao.recoverNote(note);
		result.setStatus(0);
		result.setMsg("�ָ��ɹ�");
		return result;
	}
	//�ղرʼ�
	public NoteResult collect(String noteId) {
		NoteResult result = new NoteResult();
		noteDao.collect(noteId);
		result.setStatus(0);
		result.setMsg("�ղسɹ�");
		return result;
	}
	//��ѯ�ղرʼ�
	public NoteResult findCollect() {
		NoteResult result = new NoteResult();
		List<Note> findCollect = noteDao.findCollect();
		result.setStatus(0);
		result.setMsg("��ѯ�ղرʼǳɹ�");
		result.setData(findCollect);
		return result;
	}


}
