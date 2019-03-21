package listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import bean.User;


@WebListener
public class UserLoginListener implements HttpSessionAttributeListener {

	Map<String,HttpSession> map = new HashMap<String, HttpSession>();
	 
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		System.out.println(name);
		if(name.equals("user")){
			User user= (User) event.getValue();
			//��Map�д��ڸ��˺�
			if(map.get(user.getUname())!=null){
				//map�������м�¼����ʾ���˺������������ϵ�¼��
				HttpSession session=map.get(user.getUname());
				session.invalidate();
				session.removeAttribute("user");
//				session.setAttribute("msg","�����˺������������ϵ�¼������������");
			}
			//��session���û���Ϊ����������Map
			map.put(user.getUname(),event.getSession());
		}
		
	}
 
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		if(name.equals("user")){
			User user= (User) event.getValue();
			map.remove(user.getUname());
		}
	}
 
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String name = event.getName();
		if(name.equals("user")){
			User oldUser= (User) event.getValue();
			//�Ƴ��ɵĵ�¼��Ϣ
			map.remove(oldUser.getUname());
			User user= (User) event.getSession().getAttribute("user");
			if(map.get(user.getUname())!=null){
				//map�������м�¼����ʾ���˺������������ϵ�¼��
				HttpSession session=map.get(user.getUname());
			    session.removeAttribute("user");
//				session.setAttribute("msg","�����˺������������ϵ�¼������������");
			}
			map.put(user.getUname(),event.getSession());
		}
	}

	
}
