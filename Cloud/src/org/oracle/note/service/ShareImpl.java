package org.oracle.note.service;

import java.util.List;

import javax.annotation.Resource;

import org.oracle.note.dao.ShareDao;
import org.oracle.note.entity.NoteResult;
import org.oracle.note.entity.Share;
import org.oracle.note.util.GetUUID;
import org.springframework.stereotype.Service;
@Service
public class ShareImpl implements ShareService {
	@Resource
	private ShareDao shareDao;
	//����ʼ�
	public NoteResult addShare(String noteId, String title, String body) {
		NoteResult result = new NoteResult();
		Share share = new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_title(title);
		share.setCn_share_body(body);
		GetUUID u = new GetUUID();
		String uuid = u.getUuid();
		share.setCn_share_id(uuid);
		shareDao.addShare(share);
		result.setStatus(0);
		result.setMsg("����ɹ�");
		return result;
	}
	//ģ����ѯ
	public NoteResult findTitle(String noteName) {
		NoteResult result = new NoteResult();
		List<Share> list = shareDao.findTitle(noteName+"%");
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}

}
