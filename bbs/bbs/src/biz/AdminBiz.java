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
	 * 根据用户的uid来解除禁言
	 * @param uid
	 * @return
	 * @throws BizException 
	 */
	public void releaseById(Integer uid) throws BizException {
		int releasePost = ud.releasePost(uid);
		if(releasePost<=0) {
			throw new BizException("服务器繁忙");
		}
	}

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 * @throws BizException 
	 */
	public TblAdmin login(TblAdmin admin) throws BizException {
		TblAdmin login = ad.login(admin);
		if(login==null) {
			throw new BizException("用户名或密码错误");
		}
		return login;
	}
	
	/**
	 * 查询所有的敏感词
	 * @return
	 */
	public List<Map<String, Object>> findAllWords() {		
		return sd.query();
	}
	
	/**
	 * 删除敏感词
	 * @param sid
	 * @return
	 */
	public int delWordById(String sid) {
		return sd.delWordById(sid);
	}
}
