import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;




public class HelloServletdoPost extends HttpServlet {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//	this.doPost(request, response);
//	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // --- 클라이언트 정보 로그 기록 시작 ---
        // 1. 클라이언트 IP 주소 가져오기
        String clientIpAddress = request.getRemoteAddr();

        // 2. User-Agent 헤더에서 클라이언트 정보 가져오기 (브라우저 및 OS, 때로는 Java 버전 정보도 포함)
        // 주의: User-Agent는 클라이언트가 전송하는 문자열로, 모든 정보가 명확히 파싱되지 않을 수 있습니다.
        String userAgent = request.getHeader("User-Agent");
        
        // 서블릿 컨테이너의 로그에 정보 출력
        System.out.println("클라이언트 IP 주소: " + clientIpAddress);
        System.out.println("User-Agent (클라이언트 정보): " + userAgent);
        // --- 클라이언트 정보 로그 기록 끝 ---

        // 응답 컨텐츠 타입 설정 (HTML 및 UTF-8 인코딩)
        response.setContentType("text/html;charset=UTF-8");
        
        // 브라우저로 응답을 전송하기 위한 PrintWriter 객체 얻기
        PrintWriter out = response.getWriter();
        
        // HTML 내용 출력
        out.println("<html>");
        out.println("<head><title>Hello Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Korea, 안녕 HelloServlet</h1>"); // 요청하신 문구 출력
		// --- 브라우저 화면에 클라이언트 정보 추가 시작 ---
        out.println("<hr>"); // 구분선
        out.println("<p><strong>클라이언트 IP 주소:</strong> " + clientIpAddress + "</p>");
        out.println("<p><strong>클라이언트 User-Agent (정보):</strong> " + userAgent + "</p>");
        out.println("<p><small>참고: User-Agent에 자바 버전 정보가 명시적으로 포함되지 않을 수 있습니다.</small></p>");
        // --- 브라우저 화면에 클라이언트 정보 추가 끝 ---

        out.println("</body>");
        out.println("</html>");
        
        // PrintWriter 닫기
        out.close();
    }
}

//HelloServletService.java 구현
//1. httpServlet상속
//2.service() method OverRding
//3.브라우저 화면에 Korea, 안녕 HelloServlet출력


//1.web.xml 설정
//FQCN HelloServletService.class
//URL /HelloServletService