package org.oracle.note.service;

import javax.annotation.Resource;

import org.oracle.note.dao.UserDao;
import org.oracle.note.entity.NoteResult;
import org.oracle.note.entity.User;
import org.oracle.note.util.GetUUID;
import org.oracle.note.util.NoteUtil;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	//登录
	public NoteResult checkLogin(String name, String password) throws Exception {
		System.out.println(name+","+password);
		NoteResult result = new NoteResult();
		User user = userDao.findByName(name);
//		if(name==""){
//			result.setStatus(1);
//			result.setMsg("用户名不能为空");
//			return result;
//		}else if(password==""){
//			result.setStatus(2);
//			result.setMsg("密码不能为空");
//			return result;
//		}
		
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名错误");
			return result;
		}
		String pwd = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(pwd)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		result.setStatus(0);
		result.setMsg("账号密码输入正确");
		result.setData(user.getCn_user_id());
		return result;
		
	}
//注册
	public NoteResult checkRegister(String name, String nick, String password,
			String password1) throws Exception {
		NoteResult result = new NoteResult();
		User findByName = userDao.findByName(name);
		if(findByName!=null){
			result.setStatus(2);
			result.setMsg("用户名已存在");
			return result;
		}else{
			
		
		if(!password.equals(password1)){
			result.setStatus(1);
			result.setMsg("两次密码不一致");
			return result;
		}else{
			User user = new User();
			user.setCn_user_id(new GetUUID().getUuid());
			user.setCn_user_name(name);
			user.setCn_user_nick(nick);
			String pwd = NoteUtil.md5(password);
			user.setCn_user_password(pwd);
			userDao.register(user);
			result.setStatus(0);
			result.setMsg("注册成功");
			System.out.println("注册成功");
			return result;
		}
		}
	}
	public NoteResult checkPassword(String oldPassword, String newPassword,
			String newPassword1,String uid) throws Exception {
		NoteResult result = new NoteResult();
		String passowrd = NoteUtil.md5(oldPassword);
		User user = new User();
		user.setCn_user_id(uid);
		user.setCn_user_password(passowrd);
		String name = userDao.findBypwd(user);
		System.out.println(name);
		if(name==null){
			result.setStatus(1);
			result.setMsg("原密码错误");
			return result;
		}else if(!newPassword.equals(newPassword1)){
			result.setStatus(2);
			result.setMsg("两次密码不一致");
			return result;
		}else{
			String password = NoteUtil.md5(newPassword);
			
			 user =new User();
//			user.setCn_user_id(uid);
			user.setCn_user_name(name);
			user.setCn_user_password(password);
			userDao.updatePassword(user);
			result.setStatus(0);
			result.setMsg("修改成功");
			return result;
		}
	}

}
