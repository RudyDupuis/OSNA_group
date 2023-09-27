package fr.eni.OSNA.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.OSNA.bll.UserManager;
import fr.eni.OSNA.bo.User;
import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

@WebServlet("/mon-profil")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/myProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = UserManager.getInstance();
		
		String action = request.getParameter("action");

		User userSession = (User) request.getSession().getAttribute("user");
		int userId = userSession.getId();

		if ("update".equals(action)) {
			if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
				errorDirection(request, response, MessageReader.getMessage(ErrorCode.ERROR_DIFF_PASSWORD));
					
			}  else {
			
				User user = new User(
						userId,
						request.getParameter("firstName"),
					    request.getParameter("lastName"),
					    request.getParameter("pseudo"),
					    request.getParameter("mail"),
					    request.getParameter("phone"),
					    request.getParameter("street"),
					    Integer.valueOf(request.getParameter("postalCode")),
					    request.getParameter("city"),
					    request.getParameter("password")
					    );
					
				try {
					userManager.update(user);
						
					/* Reconnect the user to retrieve the correct information in the session */
					User userConnected = userManager.login(user.getPseudo(), user.getPassword());
					request.getSession().setAttribute("user", userConnected);
						
					request.setAttribute("message", "Votre compte à été mis à jour");
					doGet(request, response);
	
				} catch (Exception e) {
					errorDirection(request, response, e.getMessage());
				}
			}
			
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
	
	private void errorDirection(HttpServletRequest request,  HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("firstNameSave", request.getParameter("firstName"));
		request.setAttribute("lastNameSave", request.getParameter("lastName"));
		request.setAttribute("pseudoSave", request.getParameter("pseudo"));
		request.setAttribute("mailSave", request.getParameter("mail"));
		request.setAttribute("phoneSave", request.getParameter("phone"));
		request.setAttribute("streetSave", request.getParameter("street"));
		request.setAttribute("postalCodeSave", request.getParameter("postalCode"));
		request.setAttribute("citySave", request.getParameter("city"));
		
		request.setAttribute("message", message);
		
		doGet(request, response);
	}
}