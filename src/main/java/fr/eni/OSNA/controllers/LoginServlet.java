package fr.eni.OSNA.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.OSNA.bll.UserManager;
import fr.eni.OSNA.bo.User;

@WebServlet("/connexion")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = UserManager.getInstance();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		try {
			User user = userManager.login(id, password);
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/");
		} catch (Exception e) {
			request.setAttribute("erreur", e);
			doGet(request, response);
		}
	}

}