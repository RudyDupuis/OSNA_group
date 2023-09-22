package fr.eni.OSNA.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.OSNA.bll.UserManager;
import fr.eni.OSNA.bo.User;

@WebServlet("/inscription")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 UserManager userManager = UserManager.getInstance();
		
		User user = new User(
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
      
        String confirmPassword = request.getParameter("confirmPassword");
        
		if(user.getPassword() != null && user.getPassword().equals(confirmPassword)) {
			try {
				userManager.insert(user);
				// TODO connecter l'utilisateur et le retourner vers la page d'acceuil
			} catch (Exception e) {
				e.printStackTrace();
				// TODO re écrire les données dans les champs si mdp mauvais 
			}
			request.setAttribute("user", user);
			this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		} else {
			request.setAttribute("erreurMdp", "Erreur sur le mot de passe et sa confirmation");
			this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
			// TODO  re écrire les données dans les champs si mdp mauvais 
		}
	}
	
	private void errorDirection(HttpServletRequest request,  HttpServletResponse response, String message) 
			throws ServletException, IOException {
	}
}
