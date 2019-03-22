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
			//若Map中存在改账号
			if(map.get(user.getUname())!=null){
				//map集合中有记录，表示该账号在其他机器上登录过
				HttpSession session=map.get(user.getUname());
				session.invalidate();
				session.removeAttribute("user");
//				session.setAttribute("msg","您的账号在其他机器上登录，您被迫下线");
			}
			//将session以用户名为索引，放入Map
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
			//移除旧的登录信息
			map.remove(oldUser.getUname());
			User user= (User) event.getSession().getAttribute("user");
			if(map.get(user.getUname())!=null){
				//map集合中有记录，表示该账号在其他机器上登录过
				HttpSession session=map.get(user.getUname());
			    session.removeAttribute("user");
//				session.setAttribute("msg","您的账号在其他机器上登录，您被迫下线");
			}
			map.put(user.getUname(),event.getSession());
		}
	}

	
}
