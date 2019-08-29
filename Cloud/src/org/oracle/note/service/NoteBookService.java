package org.oracle.note.service;

import org.oracle.note.entity.NoteResult;

public interface NoteBookService {
	public NoteResult show(String id);
	public NoteResult addNoteBook(String name,String id);
	public NoteResult updateNoteBookName(String bookId,String bookName);
	public NoteResult deleteNoteBook(String notebookid);
	public NoteResult checkNoteBook(String notebookid);

}
