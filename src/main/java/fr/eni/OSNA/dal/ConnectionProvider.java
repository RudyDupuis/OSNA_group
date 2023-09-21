package fr.eni.OSNA.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni.OSNA.messages.ErrorCode;
import fr.eni.OSNA.messages.MessageReader;

public abstract class ConnectionProvider {
	private static DataSource dataSource;

	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			new Exception(MessageReader.getMessage(ErrorCode.ERROR_CONNECTION));
		}
	}

	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dataSource.getConnection();
	}

}
