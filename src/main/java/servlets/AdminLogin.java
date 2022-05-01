package servlets;

import java.io.IOException;

import beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.AdminDB;
import utils.Utils;

public class AdminLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AdminLogin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		Utils utils = new Utils();

		
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		
		if(password == null && email == null){
			request.setAttribute("msg", "请输入邮箱名和密码");
			request.getRequestDispatcher("/templates/loginnn.jsp").forward(request, response);
			return;
		}
		AdminDB admin_db = new AdminDB();
		//将密码加密后去找
		User user = admin_db.get(email, password);

		if (user != null){
			request.getSession().setAttribute("admin", user);
			request.getRequestDispatcher("/templates/adminnn.jsp").forward(request, response);
			return;
		}else{
			request.setAttribute("msg", "用户不存在");
			request.getRequestDispatcher("/templates/loginnn.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
