package kr.co.subking.sales;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

@WebServlet("/api/v1/sales")
public class SalesAPI extends HttpServlet {
    private SalesService salesService = SalesServiceImpl.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("SalesAPI 서블릿 초기화됨");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet 메서드 호출됨: " + req.getRequestURI());
//        try {
            List<Sales> salesList = salesService.getDailySales();
            System.out.println("Sales data retrieved: " + salesList);

            resp.setHeader("Content-Type", "application/json; charset=utf-8");
            JsonMapper jsonMapper = new JsonMapper();
            String json = jsonMapper.writeValueAsString(salesList);

            System.out.println("JSON 변환 후 데이터: " + json);

            PrintWriter pw = resp.getWriter();
            pw.print(json);
            pw.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류 발생: " + e.getMessage());
//        }
    }
}


