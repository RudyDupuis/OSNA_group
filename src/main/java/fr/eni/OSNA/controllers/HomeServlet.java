package fr.eni.OSNA.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.OSNA.bll.ArticleManager;
import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.bo.User;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = ArticleManager.getInstance();
	    
		if(request.getAttribute("articles") != null) {
			request.setAttribute("articles", request.getAttribute("articles"));
		} else {
			try {
				List<Article> articles = articleManager.selectAll();
				setExpiredDate(articles);
				request.setAttribute("articles", articles);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = ArticleManager.getInstance();
		List<Article> articles = null;
		
		String action = request.getParameter("action");
		
		if(action.equals("firstFilter")){
			if(!request.getParameter("filterSearch").isEmpty()) {
				try {
					articles = articleManager.selectByKeyword(request.getParameter("filterSearch"));
					setExpiredDate(articles);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				if(!request.getParameter("categorie").equals("all")) {
					try {
						articles = articleManager.selectByCategorie(request.getParameter("categorie"));
						setExpiredDate(articles);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(!action.equals("firstFilter")) {
				User userSession = (User) request.getSession().getAttribute("user");
				int userId = userSession.getId();
				
				if(action.equals("currentAuction")){
					try {
						articles = articleManager.selectUserCurrentAuction(userId);
						setExpiredDate(articles);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if(action.equals("wonAuction")){
					try {
						articles = articleManager.selectUserWonAuction(userId);
						setExpiredDate(articles);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if(action.equals("mySales")){
					try {
						articles = articleManager.selectUserSales(userId);
						setExpiredDate(articles);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if(action.equals("myEndedSales")){
					try {
						articles = articleManager.selectUserEndedSales(userId);
						setExpiredDate(articles);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
		
		request.setAttribute("articles", articles);
		
		doGet(request, response);
	}
	
	private void setExpiredDate(List<Article> articles) {
		for (Article article : articles) {
			boolean isBeforeNow = article.getEndDate().isBefore(LocalDate.now());
			article.setExpiredDate(isBeforeNow);
		}
	}

}
