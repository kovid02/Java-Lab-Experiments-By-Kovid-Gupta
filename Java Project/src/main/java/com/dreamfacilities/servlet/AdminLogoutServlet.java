package com.dreamfacilities.servlet;
import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException;
@WebServlet("/admin/logout") public class AdminLogoutServlet extends HttpServlet {protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{HttpSession s=q.getSession(false);if(s!=null)s.invalidate();r.sendRedirect(q.getContextPath()+"/");} }
