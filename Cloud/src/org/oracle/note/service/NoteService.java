package org.oracle.note.service;

import org.oracle.note.entity.NoteResult;

public interface NoteService {
	public NoteResult showBooks(String id);
	public NoteResult addNote(String userId,String noteBookid,String noteTitle);
	public NoteResult showBody(String noteId);
	public NoteResult updateNote(String noteId ,String title,String body);
	public NoteResult updateStatus(String noteId);
	public NoteResult recycle();
	public NoteResult deleteNote(String id);
	public NoteResult recoverNote(String noteId,String notebookid);
	public NoteResult collect(String noteId);
	public NoteResult findCollect();
}
