package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Board;
import bean.User;
import biz.BizException;
import biz.BoardBiz;
import biz.CollectBiz;


@WebServlet("/init")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BoardBiz bb=new BoardBiz();
    CollectBiz cb=new CollectBiz();
    public InitServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, List<Board>> allBoard;
		try {
			HttpSession session = request.getSession();
			
			if(session.getAttribute("user")!=null) {
				User user = (User) session.getAttribute("user");
				int collectTotal=cb.findAllCollect(user);
				session.setAttribute("collectTotal", collectTotal);
			}
			
			allBoard = bb.findAllBoard();
			
			
			session.setAttribute("boardMap", allBoard);
			request.getRequestDispatcher("pages/show.jsp").forward(request, response);
		} catch (BizException e) {
			
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("pages/show.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
