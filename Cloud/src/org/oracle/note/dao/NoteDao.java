package org.oracle.note.dao;

import java.util.List;
import java.util.Map;

import org.oracle.note.entity.Note;

public interface NoteDao {
	public List<Note> findBooks(String id);
	public void addNote(Note note);
	public Note showBody(String id);
	public void updateNote(Note note);
	public void updateStatus(Note note);
	public List<Note> recycle();
	public void deleteNote(String id);
	public void recoverNote(Note note);
	public List<Note> findNote(String bookid);
	public void collect(String cn_note_id);
	public List<Note> findCollect();
}
