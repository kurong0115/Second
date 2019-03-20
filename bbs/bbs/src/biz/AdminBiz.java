package biz;

import java.util.List;
import java.util.Map;

import bean.TblAdmin;
import dao.AdminDao;
import dao.StopDao;
import dao.UserDao;

public class AdminBiz {
	
	private UserDao ud=new UserDao();
	private StopDao sd=new StopDao();
	private AdminDao ad=new AdminDao();
	/**
	 * �����û���uid���������
	 * @param uid
	 * @return
	 * @throws BizException 
	 */
	public void releaseById(Integer uid) throws BizException {
		int releasePost = ud.releasePost(uid);
		if(releasePost<=0) {
			throw new BizException("��������æ");
		}
	}

	/**
	 * ����Ա��¼
	 * @param admin
	 * @return
	 * @throws BizException 
	 */
	public TblAdmin login(TblAdmin admin) throws BizException {
		TblAdmin login = ad.login(admin);
		if(login==null) {
			throw new BizException("�û������������");
		}
		return login;
	}
	
	/**
	 * ��ѯ���е����д�
	 * @return
	 */
	public List<Map<String, Object>> findAllWords() {		
		return sd.query();
	}
	
	/**
	 * ɾ�����д�
	 * @param sid
	 * @return
	 */
	public int delWordById(String sid) {
		return sd.delWordById(sid);
	}
}
