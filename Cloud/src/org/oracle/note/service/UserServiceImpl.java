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
	//��¼
	public NoteResult checkLogin(String name, String password) throws Exception {
		System.out.println(name+","+password);
		NoteResult result = new NoteResult();
		User user = userDao.findByName(name);
//		if(name==""){
//			result.setStatus(1);
//			result.setMsg("�û�������Ϊ��");
//			return result;
//		}else if(password==""){
//			result.setStatus(2);
//			result.setMsg("���벻��Ϊ��");
//			return result;
//		}
		
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û�������");
			return result;
		}
		String pwd = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(pwd)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		result.setStatus(0);
		result.setMsg("�˺�����������ȷ");
		result.setData(user.getCn_user_id());
		return result;
		
	}
//ע��
	public NoteResult checkRegister(String name, String nick, String password,
			String password1) throws Exception {
		NoteResult result = new NoteResult();
		User findByName = userDao.findByName(name);
		if(findByName!=null){
			result.setStatus(2);
			result.setMsg("�û����Ѵ���");
			return result;
		}else{
			
		
		if(!password.equals(password1)){
			result.setStatus(1);
			result.setMsg("�������벻һ��");
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
			result.setMsg("ע��ɹ�");
			System.out.println("ע��ɹ�");
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
			result.setMsg("ԭ�������");
			return result;
		}else if(!newPassword.equals(newPassword1)){
			result.setStatus(2);
			result.setMsg("�������벻һ��");
			return result;
		}else{
			String password = NoteUtil.md5(newPassword);
			
			 user =new User();
//			user.setCn_user_id(uid);
			user.setCn_user_name(name);
			user.setCn_user_password(password);
			userDao.updatePassword(user);
			result.setStatus(0);
			result.setMsg("�޸ĳɹ�");
			return result;
		}
	}

}
