package kr.co.subking.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.subking.cart.CartService;
import kr.co.subking.cart.CartServiceImpl;
import kr.co.subking.user.User;
import kr.co.subking.user.UserServiceImple;

@WebServlet("/api/v1/tempExit")
public class TempExit extends HttpServlet {
	private static final CartService cartService = CartServiceImpl.getInstance();

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
                cartService.deleteCartIdbyUserId(user_id);

                // 응답 설정
                resp.setContentType("text/html; charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.println("alert('회원 탈퇴가 완료되었습니다.');");
                out.println("window.location.href = 'http://localhost:8080/240930subKingProject/custom/home';");
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
