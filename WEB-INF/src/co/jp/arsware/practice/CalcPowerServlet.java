package co.jp.arsware.practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcPowerServlet extends HttpServlet {
	/** Default Serial Version ID */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		doCalc(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		doCalc(req, resp);
	}

	private void doCalc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html; charset=UTF-8");

	    /* HTML 出力用 PrintWriter */
	    PrintWriter out = response.getWriter();

	    /* HTML出力 */
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
	    out.println("<title>階乗計算</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>");
	    out.println("計算結果");
	    out.println("</h1>");

		out.println("<br/>");

		int x;
		try{
			x = Integer.parseInt(request.getParameter("x"));
		}catch(NumberFormatException nfe) {
			out.println("正数を入力してください．");
		    out.println("</body>");
		    out.println("</html>");
		    out.close();
			return;
		}

		long pow = 1;

		for(int i=1 ; i<=x ; i++) {
			pow *= i;
		}

		out.println(x + "!=" + pow + "<br/>");


	    // デバッグ用
	    debug(request, out);

	    out.println("</body>");
	    out.println("</html>");
	    out.close();
	}


	private void debug(HttpServletRequest request, PrintWriter out) {

		Map<?,?> keys = request.getParameterMap();
		out.println("<table border=\"1\">");
		out.println("<tr><th>key</th><th>value</th></tr>");
		for(Object key : keys.keySet()) {
			out.println("<tr><td>"+key+"</td><td>"+request.getParameter(key.toString())+"</td></tr>");
		}
		out.println("</table>");

	}





}
