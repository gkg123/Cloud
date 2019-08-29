package org.oracle.note.dao;

import java.util.List;

import org.oracle.note.entity.Share;

public interface ShareDao {
	public void addShare(Share share);
	public List<Share> findTitle(String noteName);
}
