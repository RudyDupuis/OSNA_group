package fr.eni.OSNA.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.OSNA.bll.UserManager;
import fr.eni.OSNA.bo.User;

@WebServlet("/mon-profil")
public class MyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserManager userManager = UserManager.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/myProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		User userSession = (User) request.getSession().getAttribute("user");
		int userId = userSession.getId();

		if ("update".equals(action)) {
			
			
		}

		if ("disconnection".equals(action)) {

			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/");
		}

		if ("delete".equals(action)) {

			if (request.getParameter("sure").equals("true")) {
				try {
					userManager.deleteById(userId);

					request.getSession().invalidate();
					response.sendRedirect(request.getContextPath() + "/");
				} catch (Exception e) {
					e.printStackTrace();
					doGet(request, response);
				}
			} else {
				request.setAttribute("message", "Êtes-vous sûr de vouloir supprimer votre compte ?");
				request.setAttribute("sure", "true");
				doGet(request, response);
			}
		}
	}
}
