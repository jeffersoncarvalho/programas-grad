package br.ufc.lia.es.solar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

 
 

public class DAO {

	protected DataSource ds;
	
	 

	public DAO(DataSource ds) {
		this.ds = ds;
	}

	public DataSource getDataSource() {
		return ds;
	}

	public void setDataSource(DataSource dataSource) {
		this.ds = dataSource;
	}

	protected void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			rs = null;
		}
	}

	protected void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
			pstmt = null;
		}
	}

	protected void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	protected void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	/**
	 * MÉTODO EXTREMAMENTE IMPORTANTE! ALTERE AS STRINGS:
	 * "localhost" caso o local físico do MySql mude!
	 * "ti_np1" caso o nome da Base de Dados mude!
	 * "root" caso o usuário mude!
	 * "root" caso a senha mude!
	 * @return
	 * @throws SQLException
	 */
	public static DataSource setupDataSource() throws SQLException {
         
		FonteDeDados ds = new FonteDeDados();
		
		//Properties jdbcProperties = CreateProperties.createProperties();
		
		
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/solarmenew");
        ds.setUserName("root");
        ds.setPassword("root");
        return ds;
        
    }
	
	 
}
