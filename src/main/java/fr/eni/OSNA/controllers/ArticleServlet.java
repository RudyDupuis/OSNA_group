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
import fr.eni.OSNA.messages.InfoCode;
import fr.eni.OSNA.messages.MessageReader;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleManager articleManager = ArticleManager.getInstance();
	private UserManager userManager = UserManager.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = null;
		
		if(request.getParameter("articleId") != null) {
			int articleId = Integer.valueOf(request.getParameter("articleId"));
			
			try {
				article = articleManager.selectById(articleId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				article.setNameSeller(userManager.getPseudo(article.getIdSeller()));
			} catch (Exception e) {
				request.setAttribute("message", e.getMessage());
			}
			
			if(article.getIdUserBestOffer() != 0) {
				try {
					article.setNameUserBestOffer(userManager.getPseudo(article.getIdUserBestOffer()));
				} catch (Exception e) {
					request.setAttribute("message", e.getMessage());
				}
			}
		}
		
		request.setAttribute("article", article);
		
		request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userSession = (User) request.getSession().getAttribute("user");
		
		int points = Integer.valueOf(request.getParameter("points"));
		int articleId = Integer.valueOf(request.getParameter("articleId"));
		
		try {
			articleManager.updateOffer(points, userSession, articleId);
			request.setAttribute("message", MessageReader.getMessage(InfoCode.MAKE_OFFER));
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
		}
		
		doGet(request, response);
	}

}
