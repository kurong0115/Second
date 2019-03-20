package biz;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import bean.PageBean;
import bean.Topic;
import bean.User;
import bean.UserInfo;
import dao.StopDao;
import dao.TopicDao;
import dao.UserDao;
import utils.Myutil;

public class TopicBiz {
	TopicDao td=new TopicDao();
	UserDao ud=new UserDao();
	StopDao sd=new StopDao();
	private  UserInfo info;
	
	public UserInfo getUserinfo() {
		return info;
	}
	public void setUserinfo(UserInfo info) {
		this.info = info;
	}
	
	/**
	 * ��ҳ
	 * @param topic
	 * @return
	 * @throws BizException 
	 */
	public PageBean<Topic> findPageBean(Topic topic) throws BizException {
		PageBean<Topic> findPageBean = td.findPageBean(topic);
		
		if(findPageBean==null) {
			throw new BizException("��������æ�����Ժ�����");
		}
		return findPageBean;
	}


	/**
	 * ����
	 * @param topic
	 * @param email
	 * @return
	 * @throws BizException
	 */
	public void post(Topic topic, String email) throws BizException {
		UserInfo userinfo=ud.selectAll(topic.getUid());
		if(userinfo.getEndtime()!=null&&userinfo.getStarttime()!=null) {
			if(userinfo.getEndtime().before(new Timestamp(System.currentTimeMillis()))) {
				ud.releasePost(topic.getUid());
				userinfo.setStarttime(null);
				userinfo.setEndtime(null);
				userinfo.setTime(0);
			}
			if(userinfo.getEndtime()!=null&&userinfo.getEndtime().after(new Timestamp(System.currentTimeMillis()))) {
				System.out.println("���ѱ�����");
				throw new BizException("���ѱ�����,���Խ���ʱ��Ϊ"+userinfo.getEndtime());			
			}
		}
		this.setUserinfo(userinfo);
		System.out.println("ִ��post����"+userinfo);

		List<Map<String,Object>> list=sd.query();
		//�жϹ���ǰ��������Ƿ�һ��,�粻,�������û��Ĵ���
		String beforeTitle=topic.getTitle();
		String beforeContent=topic.getContent();
		String afterTitle=beforeTitle;
		String afterContent=beforeContent;
		for(int i=0;i<list.size();i++) {
			afterTitle=afterTitle.replace((String)list.get(i).get("sname"), "**");
			afterContent=afterContent.replace((String)list.get(i).get("sname"), "**");
		}
		if(!beforeTitle.equals(afterTitle)||!beforeContent.equals(afterContent)) {
			ud.addTime(topic.getUid());
			info.setTime(info.getTime()+1);
		}
 		
		//ÿ�������໰����һ��
		if(info.getTime()==3) {
			ud.stopPost(userinfo.getUid());			
			new Thread() {
				public void run() {
					Myutil.sendemail(email, new Timestamp(System.currentTimeMillis()+24*60*60*1000));
				}; {};
			}.start();
		}
		
		//���������óɹ���֮�������
		topic.setTitle(afterTitle);
		topic.setContent(afterContent);
		
		int post = td.post(topic, email);
		
		if(post<0) {
			throw new BizException("��������æ");			
		}
	}
	
	/**
	 *��ѯ�������ӵ�����
	 * @param topic
	 * @return
	 * @throws BizException 
	 */
	public Topic topicdetail(Topic topic) throws BizException {
		Topic topicdetail = td.topicdetail(topic);
		if(topicdetail==null) {
			throw new BizException("��������æ");	
		}
		return topicdetail;
	}
	
	/**
	 * ɾ������
	 * @param topic
	 * @return
	 * @throws BizException 
	 */
	public void delTopic(Topic topic) throws BizException {
		int delTopic = td.delTopic(topic);
		if(delTopic<0) {
			throw new BizException("��������æ��ɾ��ʧ��");
		}
		
	}
	
	/**
	 * ÿ�����ǰ10������
	 * @param topic
	 * @return
	 * @throws BizException 
	 */
	public List<Topic> findHostTopic(Topic topic) throws BizException {
		List<Topic> findHostTopic = td.findHostTopic(topic);
		if(findHostTopic==null) {
			throw new BizException("��������æ�����Ժ�����");
		}
		return findHostTopic;
	}
	
	/**
	 * ��̳����
	 * @return
	 * @throws BizException 
	 */
	public List<Topic> findAllHostTopic() throws BizException {
		List<Topic> findAllHostTopic = td.findAllHostTopic();
		if(findAllHostTopic==null) {
			throw new BizException("��������æ�����Ժ�����");
		}
		return findAllHostTopic;
	}
	
	/**
	 * ��������
	 * @return
	 * @throws BizException 
	 */
	public List<User> personTop() throws BizException {
		List<User> personTop = td.personTop();
		if(personTop==null) {
			throw new BizException("��������æ�����Ժ�����");
		}
		return personTop;
	}
	
	
	/**
	 * �����������������
	 * @return
	 * @throws BizException 
	 */
	public List<Topic> personTopTopic(Topic topic) throws BizException {
		List<Topic> personTopTopic = td.personTopTopic(topic);
		if(personTopTopic==null) {
			throw new BizException("��������æ�����Ժ�����");
		}
		return personTopTopic;
	}
	
	
	
	
}