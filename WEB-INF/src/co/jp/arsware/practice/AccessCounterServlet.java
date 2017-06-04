package co.jp.arsware.practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessCounterServlet extends HttpServlet {
	/** Default Serial Version ID */
	private static final long serialVersionUID = 1L;


	static int accessCount = 0;


	synchronized static int accessCountNextValue() {
		return ++accessCount;
	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
	    out.println("アクセスカウント");
	    out.println("</h1>");

	    int cnt = accessCountNextValue();
	    out.println(cnt + "回目のアクセスです．<br/>");

	    out.println("</body>");
	    out.println("</html>");
	    out.close();



	}

}
