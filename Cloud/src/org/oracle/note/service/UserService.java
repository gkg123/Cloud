package org.oracle.note.service;

import org.oracle.note.entity.NoteResult;
import org.oracle.note.entity.User;

public interface UserService {
	public NoteResult checkLogin(String name,String password)throws Exception;
	public NoteResult checkRegister(String name,String nick,String password,String password1 )throws Exception;
	public NoteResult checkPassword(String oldPassword,String newPassword,String newPassword1,String uid)throws Exception;
}
