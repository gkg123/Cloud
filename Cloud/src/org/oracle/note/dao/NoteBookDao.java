package org.oracle.note.dao;

import java.util.List;

import org.oracle.note.entity.NoteBook;

public interface NoteBookDao {
	public List<NoteBook> findNoteBook(String id);
	public void addNoteBook(NoteBook noteBook);
	public void updateNoteBookName(NoteBook noteBook);
	public void deleteNoteBook(String notebookid);
}
