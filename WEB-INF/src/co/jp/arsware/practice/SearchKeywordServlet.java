package co.jp.arsware.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class SearchKeywordServlet extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

	    /* HTML 出力用 PrintWriter */
	    PrintWriter out = response.getWriter();

	    /* HTML出力 */
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
	    out.println("<title>ファイル内検索</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>");
	    out.println("結果");
	    out.println("</h1>");

		out.println("<br/>");

		String keyword = null;
		String filename = null;
		InputStream fileStream = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> list = sfu.parseRequest(request);
			Iterator<FileItem> iter = list.iterator();
			while(iter.hasNext()) {
				FileItem item = iter.next();
				if(item.isFormField()) {
					if( "keyword".equals(item.getFieldName()) ) {
						keyword = item.getString();
					}
				}else{
					filename = item.getName();
					fileStream = item.getInputStream();
				}
			}
		}catch(FileUploadException e) {
			e.printStackTrace();
		}
		if(keyword==null || keyword.isEmpty()) {
			out.println("キーワードを入力してください．<br/>");
		}else if(filename==null || filename.isEmpty()) {
			out.println("ファイルを指定してください．<br/>");
		}else{
			out.println("検索文字列：" + keyword + "<br/>");
			out.println("ファイル名：" + filename + "<br/>");
			out.println("<br/>");

			BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
			int count = 0;
			int lineNo = 0;

			while(true) {
				String line = reader.readLine();
				if(line==null) {
					break;
				}
				lineNo+=1;
				if(line.contains(keyword)) {
					count+=1;
					out.println(lineNo+"行目：" + escapeHTML(line) + "<br/>");
				}
			}
			out.println("<br/>");
			out.println(count + "件ヒットしました．<br/>");

		}
	    out.println("</body>");
	    out.println("</html>");
	    out.close();
	}


	private String escapeHTML(String line) {
		StringBuilder sb = new StringBuilder();
		for(int i=0 ; i<line.length() ; i++) {
			char c = line.charAt(i);
			switch(c) {
			case '&':
				sb.append("&amp;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&#39;");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
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
