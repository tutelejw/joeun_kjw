package jw.services.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jw.services.user.dao.UserDAO;
import jw.services.user.vo.UserVO;

public class FindUser extends HttpServlet {
	
	//doGet() Overriding
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	//doPost() Overriding
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();
		
		//client에서 넘어온 값을 받자.
		String id = req.getParameter("id");
		
		////////////////////////  Session 사용으로 추가된 부분 ///////////////////////////////////////
		//==>id가 null인경우는 ?
		//==><a href='/homework03/FindUser'>내정보보기2(session사용)</a>
		if(id == null){
			//==>  Session에 저장된 userVO instance 의 id 사용
			HttpSession session = req.getSession(true);
			id =  ( (UserVO)session.getAttribute("userVO")).getId();
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//DB에 접근하는 UserDAO를 이용 회원정보 select 후
		UserDAO bean = new UserDAO();
		//==> 회원의 정보를 갖는 userVO intance로 받기
		UserVO userVO = bean.findUser(id);

		out.println("<html>");
		out.println("<body>");
		
		out.println("<h2>요청하신 회원정보</h2>");
        
		if(userVO != null){
            out.println("no  : "+ userVO.getNo()+" <br/>");
            out.println("id   : "+ userVO.getId()+" <br/>");
            out.println("pwd : "+ userVO.getPwd()+" <br/>");
		}else{
			out.println(id +"에 해당하는 data는 없습니다.<br/>");
		}

		out.println("</boyd>");
		out.println("</html>");
	}
}