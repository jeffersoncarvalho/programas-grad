package br.ufc.lia.es.solar.dao;



import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class FonteDeDados implements DataSource{

	private String driverClassName;
	private String userName;
	private String password;
	private String url;
	
	 
	
	public Connection getConnection() throws SQLException {
		
		try {
			
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, userName,password);
			conn.setAutoCommit(false);
			return conn;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        
        return null;

	}

	public Connection getConnection(String arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassForName) {
		this.driverClassName = driverClassForName;
	}

	 

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
