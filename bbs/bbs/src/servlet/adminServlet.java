package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import bean.TblAdmin;
import biz.AdminBiz;
import biz.BizException;
import dao.UserDao;

@WebServlet("/admin")
@MultipartConfig
public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao ud=new UserDao();

    AdminBiz ab=new AdminBiz();
    public adminServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String flag = request.getParameter("flag");		
		switch (flag) {
		case "adminLogin":
			adminLogin(request,response);
			break;
		case "loginOut":
			loginOut(request,response);
			break;
		case "release":
			releaseById(request,response);
			break;
		case "findAllWords":
			findAllWords(request,response);
			break;
		case "delById":
			delById(request,response);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 根据sid删除对应的敏感词
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sid=request.getParameter("sid");
		System.out.println("sid"+sid);
		System.out.println("删除对应的敏感词");
		int num=ab.delWordById(sid);		
		if(num>0) {
			response.getWriter().write("ok");
		}else {
			response.getWriter().write("no");
		}		
	}

	/**
	 * 查询所有的敏感词
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	private void findAllWords(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String,Object>> list=ab.findAllWords();
		
		@SuppressWarnings("rawtypes")
		Map map=new HashMap<>();
		map.put("rows", list);
		
		String jsonString=JSON.toJSONString(list);
		System.out.println(jsonString);
		response.getWriter().write(jsonString);
		
	}

	//管理员登录
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TblAdmin admin=new TblAdmin();

		String username=request.getParameter("loginName");
		String pwd=request.getParameter("loginPass");
		admin.setRapwd(pwd);
		admin.setRaname(username);
		HttpSession session=request.getSession();
		
		try {
			admin = ab.login(admin);
			ud.releaseAll();
			session.setAttribute("admin", admin);
			request.getRequestDispatcher("adminPages/admin.jsp").forward(request, response);
		} catch (BizException e) {
			
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("adminPages/adminLogin.jsp").forward(request, response);
		}
		if(admin!=null) {
			ud.releaseAll();
			session.setAttribute("admin", admin);
			request.getRequestDispatcher("adminPages/admin.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("adminPages/adminLogin.jsp").forward(request, response);
		}
	}
	
	//退出
	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("admin", "");
		request.getRequestDispatcher("adminPages/adminLogin.jsp").forward(request, response);
	}
	
	//解除禁言
	private void releaseById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid=request.getParameter("uid");

		try {
			ab.releaseById(Integer.valueOf(uid));
			response.getWriter().write("解除成功");
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (BizException e) {
			
			e.printStackTrace();
			response.getWriter().write(e.getMessage().toString());
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
