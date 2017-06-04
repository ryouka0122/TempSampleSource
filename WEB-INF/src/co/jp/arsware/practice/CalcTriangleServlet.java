package co.jp.arsware.practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcTriangleServlet extends HttpServlet {
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
	    out.println("<title>三角形の面積を計算</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>");
	    out.println("判定");
	    out.println("</h1>");

		out.println("<br/>");



		int Ax, Ay, Bx, By, Cx, Cy;

		try{
			Ax = Integer.parseInt(request.getParameter("Ax"));
			Ay = Integer.parseInt(request.getParameter("Ay"));
			Bx = Integer.parseInt(request.getParameter("Bx"));
			By = Integer.parseInt(request.getParameter("By"));
			Cx = Integer.parseInt(request.getParameter("Cx"));
			Cy = Integer.parseInt(request.getParameter("Cy"));
		}catch(NumberFormatException nfe) {
			out.println("数値を入力してください．");
		    out.println("</body>");
		    out.println("</html>");
		    out.close();
			return;
		}

		// ベクトル
		int ABx = Bx - Ax;
		int ABy = By - Ay;
		int ACx = Cx - Ax;
		int ACy = Cy - Ay;

		int S = ABx * ACy - ABy * ACx; // 外積計算

		if(S < 0) {
			// 負数の場合，-1を掛ける（絶対値処理）
			S *= -1;
		}
		double dS = S / 2.0;
		out.println("面積：" + dS + "<br/>");


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
