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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sales> salesList = salesService.getDailySales();

        resp.setHeader("Content-Type", "application/json; charset=utf-8");
        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(salesList);

        PrintWriter pw = resp.getWriter();
        pw.print(json);
        pw.flush();
    }
}
