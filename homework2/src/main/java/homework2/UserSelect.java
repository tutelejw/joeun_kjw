package homework2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/UserSelect")
public class UserSelect extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id"); 

        HttpSession session = request.getSession(true);
        
		if(id == null){
			id =  ( (UserVO)session.getAttribute("userVO")).getId();
			System.out.println(" jsessionid = "+ session.getId() );
		}
        
        UserLoginPoolDAO dao = new UserLoginPoolDAO();
        UserVO userVO = dao.getUserById(id);
        
        out.println("<html><body>");
        
        if (userVO == null ) {
        	out.println(id +"에 해당하는 id data는 없습니다.<br/>");
        } else {
          out.println("no  : "+ userVO.getNo()+" <br/>");
          out.println("id   : "+ userVO.getId()+" <br/>");
          out.println("pwd : "+ userVO.getPwd()+" <br/>");
          out.println("Gender : "+ userVO.getGender()+" <br/>");
          out.println("Married : "+ userVO.getMarried()+" <br/>");
        }
        out.println("<br><a href='findUser.html'>이전 페이지로</a>");
        out.println("</body></html>");
    }
}
