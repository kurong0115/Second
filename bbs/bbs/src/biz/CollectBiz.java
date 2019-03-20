package biz;

import java.util.List;

import bean.collect;
import dao.CollectDao;

public class CollectBiz {

	CollectDao cd=new CollectDao();
	
	/**
	 * �����ղ�
	 * @param col
	 * @return
	 * @throws BizException 
	 */
	public void addCollect(collect col) throws BizException {
		int addCollect = cd.addCollect(col);
		System.out.println(addCollect);
		if(addCollect<0) {
			throw new BizException("��������æ");
		}else if(addCollect==0) {
			throw new BizException("�����ղظ�������鿴�ҵ��ղ�");
		}
		
	}

	/**
	 * �鿴�ҵ��ղ�
	 * @param col
	 * @return
	 * @throws BizException 
	 */
	public List<collect> findMyCollect(collect col) throws BizException {
		List<collect> findMyCollect = cd.findMyCollect(col);
		if(findMyCollect==null) {
			throw new BizException("��������æ");
		}
		return findMyCollect;
	}

	/**
	 * ȡ���ղ�
	 * @param col
	 * @return
	 * @throws BizException 
	 */
	public void cancleCollect(collect col) throws BizException {
		int cancleCollect = cd.cancleCollect(col);
		if(cancleCollect<0l) {
			throw new BizException("��������æ");
		}
		
	}

}
