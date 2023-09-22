package fr.eni.OSNA.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.OSNA.bll.ArticleManager;
import fr.eni.OSNA.bo.Article;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = ArticleManager.getInstance();

		Article article = null;

		try {
			article = articleManager.selectById(Integer.valueOf(request.getParameter("articleId")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Blob imageBlob = article.getImage();

		InputStream inputStream = null;
		try {
			inputStream = imageBlob.getBinaryStream();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("image/jpeg");

		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
