package fr.eni.OSNA.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.OSNA.bll.ArticleManager;
import fr.eni.OSNA.bll.UserManager;
import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.bo.User;



@WebServlet("/article")
public class ArticleC extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleManager articleManager = ArticleManager.getInstance();
	private UserManager userManager = UserManager.getInstance();
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int  articleId = Integer.parseInt(request.getParameter("articleId"));
		Article article = null;
		
			try {
				article = articleManager.selectById(articleId);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				String nameSeller = userManager.getPseudo(article.getIdSeller());
				article.setNameSeller(nameSeller);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		request.setAttribute("article", article);
		request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User userSession = (User) request.getSession().getAttribute("user");
		
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int points = Integer.parseInt(request.getParameter("points"));
		
		try {
			articleManager.updateOffer(points, userSession.getId(), articleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		doGet(request, response);
		
		
		
	}

}
