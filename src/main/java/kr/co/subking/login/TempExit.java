/*
 * package kr.co.subking.login;
 * 
 * import java.io.IOException; import java.io.PrintWriter; import
 * javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
 * import javax.servlet.http.HttpServlet; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession; import java.sql.Connection; import
 * java.sql.DriverManager; import java.sql.PreparedStatement;
 * 
 * @WebServlet("/api/v1/tempExit") public class TempExit extends HttpServlet {
 * 
 * @Override protected void doDelete(HttpServletRequest req, HttpServletResponse
 * resp) throws ServletException, IOException { HttpSession session =
 * req.getSession(); String user_id = (String) session.getAttribute("user_id");
 * 
 * if (user_id == null) { resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 * return; }
 * 
 * // DB 연결 및 사용자 삭제 boolean isDeleted = deleteUserFromDatabase(user_id);
 * 
 * // 세션 무효화 session.invalidate();
 * 
 * resp.setContentType("application/json; charset=UTF-8"); PrintWriter out =
 * resp.getWriter();
 * 
 * // 삭제 결과에 따라 응답 생성 if (isDeleted) {
 * out.println("{\"success\": true, \"message\": \"회원탈퇴가 완료되었습니다.\"}");
 * resp.setStatus(HttpServletResponse.SC_OK); } else {
 * out.println("{\"success\": false, \"message\": \"회원탈퇴에 실패했습니다.\"}");
 * resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); } out.close(); }
 * 
 * private boolean deleteUserFromDatabase(String user_id) { String url =
 * "jdbc:mysql://192.168.0.188:3306/SUBKING"; String username = "dc"; String
 * password = "1234";
 * 
 * try (Connection connection = DriverManager.getConnection(url, username,
 * password); PreparedStatement statement =
 * connection.prepareStatement("DELETE FROM user WHERE user_id = ?")) {
 * 
 * statement.setString(1, user_id); int rowsAffected =
 * statement.executeUpdate();
 * 
 * return rowsAffected > 0; // 삭제된 행이 있으면 true 반환 } catch (Exception e) {
 * e.printStackTrace(); // 예외 처리 return false; // 오류가 발생하면 false 반환 } } }
 */
package kr.co.subking.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.subking.user.User;
import kr.co.subking.user.UserServiceImple;

@WebServlet("/api/v1/tempExit")
public class TempExit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 세션을 가져옵니다.
        HttpSession session = req.getSession();
        String user_id =  (String) session.getAttribute("user_id");
        System.out.println(user_id);
        
        if (user_id != null) {
           
            UserServiceImple userserviceimple = new UserServiceImple();
            int isDeleted = userserviceimple.delete(user_id);
            
            if (isDeleted == 1) {
                // 삭제 성공 시 세션 무효화
                session.invalidate();

                // 응답 설정
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.println("alert('회원 탈퇴가 완료되었습니다.');");
                out.println("window.location.href = 'http://localhost:8080/240930subKingProject/static/jsp/subking.jsp';");
                out.println("</script>");
                out.close();
            } else {
                // 삭제 실패 시
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.println("alert('회원 탈퇴에 실패했습니다. 다시 시도해주세요.');");
                out.println("window.location.href = 'http://localhost:8080/240930subKingProject/static/jsp/subking.jsp';");
                out.println("</script>");
                out.close();
            }
        } else {
            // 사용자 ID가 없는 경우
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('세션이 만료되었습니다. 다시 로그인 해주세요.');");
            out.println("window.location.href = 'http://localhost:8080/240930subKingProject/static/jsp/subking.jsp';");
            out.println("</script>");
            out.close();
        }
    }
}
