/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.94
 * Generated at: 2019-11-05 12:24:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-CN\">\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"description\" content=\"\">\r\n");
      out.write("    <meta name=\"keys\" content=\"\">\r\n");
      out.write("    <meta name=\"author\" content=\"\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/font-awesome.min.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/login.css\">\r\n");
      out.write("\t<style>\r\n");
      out.write("\r\n");
      out.write("\t</style>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("    <nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\r\n");
      out.write("      <div class=\"container\">\r\n");
      out.write("        <div class=\"navbar-header\">\r\n");
      out.write("          <div><a class=\"navbar-brand\" href=\"index.html\" style=\"font-size:32px;\">尚筹网-创意产品众筹平台</a></div>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("\t\t\r\n");
      out.write("      <form id=\"loginForm\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/doLogin.do\" method=\"POST\" class=\"form-signin\" role=\"form\">\r\n");
      out.write("      \t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${exception.message }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("        <h2 class=\"form-signin-heading\"><i class=\"glyphicon glyphicon-log-in\"></i> 用户登录</h2>\r\n");
      out.write("\t\t  <div class=\"form-group has-success has-feedback\">\r\n");
      out.write("\t\t\t<input type=\"text\" class=\"form-control\" id=\"floginacct\" name=\"loginacct\" value=\"\" placeholder=\"请输入登录账号\" autofocus>\r\n");
      out.write("\t\t\t<span class=\"glyphicon glyphicon-user form-control-feedback\"></span>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t  <div class=\"form-group has-success has-feedback\">\r\n");
      out.write("\t\t\t<input type=\"password\" class=\"form-control\" id=\"fuserpswd\" name=\"userpswd\" value=\"123\" placeholder=\"请输入登录密码\" style=\"margin-top:10px;\">\r\n");
      out.write("\t\t\t<span class=\"glyphicon glyphicon-lock form-control-feedback\"></span>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t  <div class=\"form-group has-success has-feedback\">\r\n");
      out.write("\t\t\t<select id=\"ftype\" class=\"form-control\" name=\"type\">\r\n");
      out.write("                <option value=\"member\">会员</option>\r\n");
      out.write("                <option value=\"user\" selected>管理</option>\r\n");
      out.write("            </select>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("        <div class=\"checkbox\">\r\n");
      out.write("          <label>\r\n");
      out.write("            <input type=\"checkbox\" value=\"remember-me\"> 记住我\r\n");
      out.write("          </label>\r\n");
      out.write("          <br>\r\n");
      out.write("          <label>\r\n");
      out.write("            忘记密码\r\n");
      out.write("          </label>\r\n");
      out.write("          <label style=\"float:right\">\r\n");
      out.write("            <a href=\"reg.html\">我要注册</a>\r\n");
      out.write("          </label>\r\n");
      out.write("        </div>\r\n");
      out.write("        <a class=\"btn btn-lg btn-success btn-block\" onclick=\"dologin()\" > 登录</a>\r\n");
      out.write("      </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/jquery/jquery-2.1.1.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("    <script>\r\n");
      out.write("    function dologin() {\r\n");
      out.write("    \t/* $(\"#loginForm\").submit(); */\r\n");
      out.write("    \t\r\n");
      out.write("    \tvar floginacct = $(\"#floginacct\");\r\n");
      out.write("    \tvar fuserpswd = $(\"#fuserpswd\");\r\n");
      out.write("    \tvar ftype = $(\"#ftype\");\r\n");
      out.write("    \t\r\n");
      out.write("    \tif($.trim(floginacct.val())==\"\"){\r\n");
      out.write("    \t\talert(\"用户账号不能为空，请重新输入！\");\r\n");
      out.write("    \t\tfloginacct.val(\"\");\r\n");
      out.write("    \t\tfloginacct.focus();\r\n");
      out.write("    \t\treturn false;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t\r\n");
      out.write("    \tif($.trim(fuserpswd.val())==\"\"){\r\n");
      out.write("    \t\talert(\"用户密码不能为空，请重新输入！\");\r\n");
      out.write("    \t\tfuserpswd.val(\"\");\r\n");
      out.write("    \t\tfuserpswd.focus();\r\n");
      out.write("    \t\treturn false;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t\r\n");
      out.write("    \t$.ajax({\r\n");
      out.write("    \t\ttype : \"POST\",\r\n");
      out.write("    \t\tdata : {\r\n");
      out.write("    \t\t\tloginacct : floginacct.val(),\r\n");
      out.write("    \t\t\tuserpswd : fuserpswd.val(),\r\n");
      out.write("    \t\t\ttype : ftype.val()\r\n");
      out.write("    \t\t},\r\n");
      out.write("    \t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${APP_PATH}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/doLogin.do\",\r\n");
      out.write("    \t\tbeforeSend : function(){\r\n");
      out.write("    \t\t\t//一般做表单数据校验.\r\n");
      out.write("    \t\t\treturn true ;\r\n");
      out.write("    \t\t},\r\n");
      out.write("    \t\tsuccess : function(result){ //{\"success\":true}  或    {\"success\":false,\"message\":\"登录失败!\"}\r\n");
      out.write("    \t\t\tif(result.success){    \t\t\t\t\r\n");
      out.write("    \t\t\t\talert(\"ok\");\r\n");
      out.write("    \t\t\t}else{\r\n");
      out.write("    \t\t\t\talert(\"not ok\");\r\n");
      out.write("    \t\t\t}\r\n");
      out.write("    \t\t},\r\n");
      out.write("    \t\terror : function(){\r\n");
      out.write("    \t\t\talert(\"error\");\r\n");
      out.write("    \t\t}\t\r\n");
      out.write("    \t});\r\n");
      out.write("        /* var type = $(\":selected\").val();\r\n");
      out.write("        if ( type == \"user\" ) {\r\n");
      out.write("            window.location.href = \"main.html\";\r\n");
      out.write("        } else {\r\n");
      out.write("            window.location.href = \"index.html\";\r\n");
      out.write("        } */\r\n");
      out.write("    }\r\n");
      out.write("    </script>\r\n");
      out.write("  </body>\r\n");
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
