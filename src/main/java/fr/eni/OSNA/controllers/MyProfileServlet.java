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
				request.setAttribute("message", MessageReader.getMessage(ErrorCode.ERROR_DIFF_PASSWORD));
				doGet(request, response);
					
			}  else {
			
				User user = new User(
						userId,
						request.getParameter("pseudo"),
						request.getParameter("firstName"),
					    request.getParameter("lastName"),
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
					request.setAttribute("pseudoValue", user.getPseudo());
					request.setAttribute("firstNameValue", user.getFirstName());
					request.setAttribute("lastNameValue", user.getLastName());
					request.setAttribute("mailValue", user.getMail());
					request.setAttribute("phoneValue", user.getPhone());
					request.setAttribute("streetValue", user.getStreet());
					request.setAttribute("postalCodeValue", user.getPostalCode());
					request.setAttribute("cityValue", user.getCity());
					        
					doGet(request, response);
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
}