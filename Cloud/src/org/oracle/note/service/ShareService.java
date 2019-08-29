package org.oracle.note.service;

import org.oracle.note.entity.NoteResult;

public interface ShareService {
public NoteResult addShare(String noteId,String title,String body);
public NoteResult findTitle(String noteName);
}
