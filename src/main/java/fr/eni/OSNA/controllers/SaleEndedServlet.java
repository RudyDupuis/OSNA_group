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

@WebServlet("/vente-terminee")
public class SaleEndedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = ArticleManager.getInstance();
		UserManager userManager = UserManager.getInstance();
		
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
		
		request.getRequestDispatcher("/WEB-INF/saleEnded.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleId = Integer.valueOf(request.getParameter("articleId"));
		ArticleManager articleManager = ArticleManager.getInstance();
		
		try {
			articleManager.updatePickedUp(articleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
