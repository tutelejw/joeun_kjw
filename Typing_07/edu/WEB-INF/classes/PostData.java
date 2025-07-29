import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostData")
public class PostData extends HttpServlet {
//    private static final long serialVersionUID = 1L;

    // doPost 메서드 오버라이딩
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 클라이언트에서 보낸 name, addr 받기 (한글 깨짐 방지용)
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String addr = request.getParameter("addr");

        // 서버 콘솔 출력
        System.out.println("서버로부터 받은 데이터 Post:");
        System.out.println("이름: " + name + " / 주소: " + addr);

        // 브라우저에 출력
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>입력하신 이름과 주소는 다음과 같습니다.</h2>");
        out.println("<p><strong>이름:</strong> " + name + "</p>");
        out.println("<p><strong>주소:</strong> " + addr + "</p>");
        out.println("</body></html>");
    }
}
