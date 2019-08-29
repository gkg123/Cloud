package org.oracle.note.controller;

import javax.annotation.Resource;

import org.oracle.note.entity.NoteResult;

import org.oracle.note.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	//µÇÂ¼
	@Resource
	private UserService userService;
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult login(String name,String password)throws Exception{
		NoteResult result= userService.checkLogin(name, password);
		return result;
		
	}
	
	//×¢²á
	@RequestMapping("/register.do")
	@ResponseBody
	public NoteResult register(String name,String nick,String password,String password1 )throws Exception{
		NoteResult result = userService.checkRegister(name, nick, password, password1);
	
		return result;
	}
	
	//ÐÞ¸ÄÃÜÂë
	@RequestMapping("/changePassword.do")
	@ResponseBody
	public NoteResult changePassword(String oldPassword,String newPassword,String newPassword1,String uid)throws Exception{
		NoteResult result = userService.checkPassword(oldPassword, newPassword, newPassword1,uid);
		return result;
		
	}
	
}
