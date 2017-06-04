package co.jp.arsware.practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcSegmentCrossServlet extends HttpServlet {
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

	    response.setContentType("text/html; charset=Shift_JIS");

	    /* HTML 出力用 PrintWriter */
	    PrintWriter out = response.getWriter();

	    /* HTML出力 */
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Hello!</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>");
	    out.println("判定");
	    out.println("</h1>");

	    // 数値の取得
		int Ax,Ay,Bx,By,Cx,Cy,Dx,Dy;
		try{
			Ax = Integer.parseInt(request.getParameter("Ax"));
			Ay = Integer.parseInt(request.getParameter("Ay"));
			Bx = Integer.parseInt(request.getParameter("Bx"));
			By = Integer.parseInt(request.getParameter("By"));
			Cx = Integer.parseInt(request.getParameter("Cx"));
			Cy = Integer.parseInt(request.getParameter("Cy"));
			Dx = Integer.parseInt(request.getParameter("Dx"));
			Dy = Integer.parseInt(request.getParameter("Dy"));
		}catch(NumberFormatException nfe) {
			out.println("数値を入力してください．");
		    out.println("</body>");
		    out.println("</html>");
		    out.close();
			return;
		}

		int Px = Bx-Ax;
		int Py = By-Ay;
		int Qx = Dx-Cx;
		int Qy = Dy-Cy;
		int Rx = Cx-Ax;
		int Ry = Cy-Ay;

		int s = Qx * Ry - Qy * Rx ;
		int t = Px * Ry - Py * Rx;
		int det = - Px * Qy + Py * Qx;

		if(det == 0) {
			out.println("２つの線分が重なってます．");
		}else{
			double dS = (double)s/det;
			double dT = (double)t/det;
			if(0.0 <= dS && dS <= 1.0 && 0.0 <= dT && dT <= 1.0) {
				out.println("交差してます．");
				double x = Ax + (Bx-Ax)*dS;
				double y = Ay + (By-Ay)*dS;
				out.println(String.format("交点：(%f,%f)", x, y));
			}else{
				out.println("交差してません．");
			}
		}
		out.println("<br/>");

	    // デバッグ用
		out.println(String.format("det=%d<br/>", det));
		out.println(String.format("s=%d<br/>", s));
		out.println(String.format("t=%d<br/>", t));
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
