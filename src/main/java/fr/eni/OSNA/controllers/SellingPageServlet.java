package fr.eni.OSNA.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import fr.eni.OSNA.bll.ArticleManager;
import fr.eni.OSNA.bo.Article;
import fr.eni.OSNA.bo.User;
import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

@WebServlet("/vendre-un-article")
@MultipartConfig(maxFileSize = 1024 * 1024)
public class SellingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = ArticleManager.getInstance();

		Article article = null;
		int articleId = 0;

		if (request.getAttribute("articleId") != null) { 							// comes from the doPost method
			articleId = Integer.valueOf((int) request.getAttribute("articleId"));
		} else {
			if (request.getParameter("articleId") != null && !request.getParameter("articleId").isEmpty()) { 						// comes from the page
				articleId = Integer.valueOf(request.getParameter("articleId"));
			}
		}

		if (articleId != 0) {
			try {
				article = articleManager.selectById(articleId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("article", article);

		request.getRequestDispatcher("/WEB-INF/sellingPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = ArticleManager.getInstance();

		User userSession = (User) request.getSession().getAttribute("user");
		int userId = userSession.getId();

		String action = request.getParameter("action");

		if (action.equals("delete")) {
			int articleId = Integer.valueOf(request.getParameter("articleId"));
			request.setAttribute("articleId", articleId);

			if (request.getParameter("sure").equals("true")) {
				try {
					articleManager.delete(articleId);
					doGet(request, response);
				} catch (Exception e) {
					request.setAttribute("message", e.getMessage());
					doGet(request, response);
				}

			} else {
				request.setAttribute("message", "Êtes-vous sûr de vouloir supprimer cet article ?");
				request.setAttribute("sure", "true");
				doGet(request, response);
			}

		} else {
			String name = request.getParameter("name");
			String categorie = request.getParameter("categorie");
			String description = request.getParameter("description");
			int startingPrice = Integer.valueOf(request.getParameter("startingPrice"));
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
			String street = request.getParameter("street");
			int postalCode = Integer.valueOf(request.getParameter("postalCode"));
			String city = request.getParameter("city");
			Blob imageBlob = null;
			
			try {
				imageBlob = imageToBlob(request.getPart("image"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			if (action.equals("create")) {
				Article article = new Article(userId, name, categorie, imageBlob, description, startingPrice, startDate,
						endDate, street, postalCode, city);

				try {
					articleManager.insert(article);
					request.setAttribute("articleId", article.getId());
					request.setAttribute("message", "Votre article a été ajouté");
					doGet(request, response);

				} catch (Exception e) {
					request.setAttribute("message", e.getMessage());
					doGet(request, response);
				}
			}

			if (action.equals("update")) {
				int articleId = Integer.valueOf(request.getParameter("articleId"));
				request.setAttribute("articleId", articleId);
				
				if(request.getPart("image").getSize() == 0) {
					try {
						imageBlob = articleManager.selectById(articleId).getImage();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Article article = new Article(articleId, userId, name, categorie, imageBlob, description, startingPrice,
						startDate, endDate, street, postalCode, city);

				try {
					articleManager.update(article);
					request.setAttribute("message", "Votre article a été modifié");
					doGet(request, response);

				} catch (Exception e) {
					request.setAttribute("message", e.getMessage());
					doGet(request, response);
				}
			}

		}

	}

	protected Blob imageToBlob(Part filePart) throws Exception {
		Blob imageBlob = null;
		
		long fileSize = filePart.getSize();
		
		if (fileSize > 200*1024) {
	        throw new Exception(MessageReader.getMessage(ErrorCode.ERROR_IMAGE_SIZE));
	    }

		byte[] fileBytes = new byte[(int) filePart.getSize()];

		try (InputStream inputStream = filePart.getInputStream()) {
			inputStream.read(fileBytes);
		}

		try {
			imageBlob = new SerialBlob(fileBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return imageBlob;
	}

}
