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

@WebServlet("/inscription")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager userManager = UserManager.getInstance();
		
		User user = new User(
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
      
        String confirmPassword = request.getParameter("confirmPassword");
        
		if(user.getPassword() != null && user.getPassword().equals(confirmPassword)) {
			try {
				userManager.insert(user);
	
				/* Log in the user and send them to the home page */
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/");
			} catch (Exception e) {
				e.printStackTrace();
				errorDirection(request, response, e.getMessage()); 
			}
		} else {
			errorDirection(request, response, MessageReader.getMessage(ErrorCode.ERROR_DIFF_PASSWORD)); 
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