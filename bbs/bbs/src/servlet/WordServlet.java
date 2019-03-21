package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.BizException;
import biz.WordBiz;


@WebServlet("/noword")
public class WordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private WordBiz wb=new WordBiz();
    
    public WordServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=request.getParameter("flag");
		switch (flag) {
		case "add":
			add(request, response);
			break;

		default:
			break;
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String word=request.getParameter("noword");
		response.setContentType("text/html; charset=UTF-8");
		try {
			wb.add(word);
			response.getWriter().write(1);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			response.getWriter().write(e.getMessage().toString());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
