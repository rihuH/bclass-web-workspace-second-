/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.99
 * Generated at: 2024-12-16 04:38:56 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1732761397193L));
    _jspx_dependants.put("jar:file:/C:/bclass99/web-workspace2/RealSuperProject/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Document</title>\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<br />\r\n");
      out.write("	<br />\r\n");
      out.write("\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/header.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<div class=\"outer\">\r\n");
      out.write("		<div class=\"container\">\r\n");
      out.write("\r\n");
      out.write("			<div class=\"row\">\r\n");
      out.write("				<div class=\"offset-lg-2 col-lg-8\">\r\n");
      out.write("					<div class=\"card\">\r\n");
      out.write("						<div class=\"card-header text-white\"\r\n");
      out.write("							style=\"background-color: #52b1ff;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${board.boardNo }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("번 게시물 내용</div>\r\n");
      out.write("						<div class=\"card-body\">\r\n");
      out.write("\r\n");
      out.write("							<div class=\"form-group\">\r\n");
      out.write("								<label>카테고리</label><br> <span>원본게시글 카테고리</span>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("							<div class=\"form-group\">\r\n");
      out.write("								<label>작성자</label> <input type=\"text\" class=\"form-control\"\r\n");
      out.write("									name='writer' value=\"원본게시글작성자\" readonly>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("							<div class=\"form-group\">\r\n");
      out.write("								<label>제목</label> <input type=\"text\" class=\"form-control\"\r\n");
      out.write("									name='title' value=\"원본게시글제목\" readonly>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("							<div class=\"form-group\">\r\n");
      out.write("								<label>내용</label>\r\n");
      out.write("								<textarea class=\"form-control\" rows=\"5\" name='content' readonly\r\n");
      out.write("									style=\"resize: none;\">원본 게시글 내용</textarea>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("							<div class=\"form-group\">\r\n");
      out.write("								<label>첨부파일</label>\r\n");
      out.write("\r\n");
      out.write("								<!-- 첨부파일은 있을수도있음 -->\r\n");
      out.write("								<a download=\"파일명\" href=\"파일경로\">파일명</a><br>\r\n");
      out.write("\r\n");
      out.write("								<img src=\"파일경로\" />\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("								<!-- 첨부파일은 없을수도있음 -->\r\n");
      out.write("								&nbsp;&nbsp;<span>첨부파일이 존재하지 않습니다.</span>\r\n");
      out.write("							</div>\r\n");
      out.write("\r\n");
      out.write("							<a class=\"btn\" href=\"boards?currentPage=1\"\r\n");
      out.write("								style=\"background-color: #52b1ff; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8\">목록</a>&nbsp;&nbsp;\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("							<a class=\"btn\" href=\"수정요청 매핑값\"\r\n");
      out.write("								style=\"background-color: orange; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8\">수정</a>&nbsp;&nbsp;\r\n");
      out.write("\r\n");
      out.write("							<a class=\"btn\" href=\"삭제하기\"\r\n");
      out.write("								onclick=\"return confirm('정말로 삭제하시겠습니까?')\"\r\n");
      out.write("								style=\"background-color: red; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8\">삭제</a>&nbsp;&nbsp;\r\n");
      out.write("\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div id=\"reply-area\">\r\n");
      out.write("\r\n");
      out.write("			<table class=\"form-group\" align=\"center\">\r\n");
      out.write("				<thead>\r\n");
      out.write("					<tr>\r\n");
      out.write("						<th>댓글작성</th>\r\n");
      out.write("						<td><textarea id=\"replyContent\" cols=\"50\" rows=\"3\"\r\n");
      out.write("								style=\"resize: none;\" class=\"form-control\"></textarea></td>\r\n");
      out.write("						<td><button onclick=\"insertReply();\" class=\"btn\"\r\n");
      out.write("								style=\"width: 100%; height: 100%; background-color: #52b1ff; color: white;\">댓글등록</button></td>\r\n");
      out.write("\r\n");
      out.write("						<td><textarea readonly cols=\"50\" rows=\"3\"\r\n");
      out.write("								style=\"resize: none;\" class=\"form-control\">로그인 후 이용가능한 서비스입니다.</textarea>\r\n");
      out.write("						</td>\r\n");
      out.write("						<td><button class=\"btn\"\r\n");
      out.write("								style=\"width: 100%; height: 100%; background-color: #52b1ff; color: white;\">댓글등록</button></td>\r\n");
      out.write("					</tr>\r\n");
      out.write("				</thead>\r\n");
      out.write("				<tbody>\r\n");
      out.write("\r\n");
      out.write("				</tbody>\r\n");
      out.write("			</table>\r\n");
      out.write("			<br>\r\n");
      out.write("			<br>\r\n");
      out.write("			<br>\r\n");
      out.write("			<br>\r\n");
      out.write("		</div>\r\n");
      out.write("\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../include/footer.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}