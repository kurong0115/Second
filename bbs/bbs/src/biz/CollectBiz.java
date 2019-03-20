package biz;

import java.util.List;

import bean.collect;
import dao.CollectDao;

public class CollectBiz {

	CollectDao cd=new CollectDao();
	
	/**
	 * 加入收藏
	 * @param col
	 * @return
	 * @throws BizException 
	 */
	public void addCollect(collect col) throws BizException {
		int addCollect = cd.addCollect(col);
		System.out.println(addCollect);
		if(addCollect<0) {
			throw new BizException("服务器繁忙");
		}else if(addCollect==0) {
			throw new BizException("你已收藏该帖，请查看我的收藏");
		}
		
	}

	/**
	 * 查看我的收藏
	 * @param col
	 * @return
	 * @throws BizException 
	 */
	public List<collect> findMyCollect(collect col) throws BizException {
		List<collect> findMyCollect = cd.findMyCollect(col);
		if(findMyCollect==null) {
			throw new BizException("服务器繁忙");
		}
		return findMyCollect;
	}

	/**
	 * 取消收藏
	 * @param col
	 * @return
	 * @throws BizException 
	 */
	public void cancleCollect(collect col) throws BizException {
		int cancleCollect = cd.cancleCollect(col);
		if(cancleCollect<0l) {
			throw new BizException("服务器繁忙");
		}
		
	}

}
