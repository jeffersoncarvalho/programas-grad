package br.ufc.lia.es.solar.dao;

 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.ufc.lia.es.solar.model.UsuarioModel;

 

 

public class UsuarioDAO extends DAO{

	public UsuarioDAO(DataSource ds) {
		super(ds);
		
	}
	
	public int countAdm( ) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
	      conn = ds.getConnection();
	      String sql = " SELECT COUNT(*)" +
	      			   " FROM USUARIO_USR "+
	      			   " WHERE nivel = 1";
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	        
	        return rs.getInt(1);
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	      rollback(conn);
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
	        return -1;
	  }
	
	public UsuarioModel retrieveByLogin(String login) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UsuarioModel usuarioModel = new UsuarioModel();
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT nome, login, senha, nivel, email, site, area, acessos " +
	      			   " FROM USUARIO_USR "+
	      			   "WHERE login = '" + login +"'";
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	        usuarioModel.setNome( rs.getString(1));
	        usuarioModel.setLogin(rs.getString(2));
	        usuarioModel.setSenha(rs.getString(3));
	        usuarioModel.setNivel(rs.getString(4));
	        usuarioModel.setEmail(rs.getString(5));
	        usuarioModel.setSite(rs.getString(6));
	        usuarioModel.setArea(rs.getString(7));
	        usuarioModel.setAcessos(rs.getInt(8));
	        return usuarioModel;
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	      rollback(conn);
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
	        return null;
	  }
	 
	public List list( ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<UsuarioModel> list = new ArrayList<UsuarioModel>() ;
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT nome, login, senha, nivel, email, site, area, acessos " +
	      			   " FROM USUARIO_USR ";
	      			   
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	UsuarioModel usuarioModel = new UsuarioModel();
	    	usuarioModel.setNome( rs.getString(1));
	        usuarioModel.setLogin(rs.getString(2));
	        usuarioModel.setSenha(rs.getString(3));
	        usuarioModel.setNivel(rs.getString(4));
	        usuarioModel.setEmail(rs.getString(5));
	        usuarioModel.setSite(rs.getString(6));
	        usuarioModel.setArea(rs.getString(7));
	        usuarioModel.setAcessos(rs.getInt(8));
	        list.add(usuarioModel);
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	      rollback(conn);
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
	        return list;
	  }
	
	public List listLogins( ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<UsuarioModel> list = new ArrayList<UsuarioModel>() ;
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT login" +
	      			   " FROM USUARIO_USR ";
	      			   
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	UsuarioModel usuarioModel = new UsuarioModel();
	    	 
	        usuarioModel.setLogin(rs.getString(1));
	         
	        
	        list.add(usuarioModel);
	      }
	      close(rs);
	      close(pstmt);
	    } catch (SQLException e) {
	      close(rs);
	      close(pstmt);
	      rollback(conn);
	      e.printStackTrace();
	    } finally {
	    	close(conn);
	    }
	        return list;
	  }


	public void insert(UsuarioModel usuario) throws SQLException {
	    String sql;
	    sql = "INSERT INTO USUARIO_USR (login, senha, nivel, nome, site, email, area) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	     
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, usuario.getLogin());
	      pstmt.setString(2, usuario.getSenha());
	      pstmt.setString(3, usuario.getNivel());
	      pstmt.setString(4, usuario.getNome());
	      pstmt.setString(5, usuario.getSite());
	      pstmt.setString(6, usuario.getEmail());
	      pstmt.setString(7, usuario.getArea());
	      pstmt.executeUpdate();
	      pstmt.close();
	      conn.commit();
	    } catch (SQLException sqle) {
	       
	      close(pstmt);
	      rollback(conn);
	      sqle.printStackTrace();
	      throw sqle;
	    } finally {
	    	close(conn);
	    }
	}
	
	public void updateAcessos(UsuarioModel usuario) throws SQLException {
	    String sql;
	    sql = "UPDATE USUARIO_USR SET acessos=? WHERE login LIKE '"+usuario.getLogin()+"'";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	     
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, usuario.getAcessos().intValue()); 
	      
	      pstmt.executeUpdate();
	     
	      pstmt.close();
	      conn.commit();
	    } catch (SQLException sqle) {
	    	sqle.printStackTrace();
	      close(pstmt);
	      rollback(conn);
	      
	      throw sqle;
	    } finally {
	    	close(conn);
	    }
	}
	
	public void update(UsuarioModel usuario) throws SQLException {
	    String sql;
	    sql = "UPDATE USUARIO_USR SET senha=?, nivel=?, nome=?, site=?, email=?, area=? WHERE login = '"+usuario.getLogin()+"'";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	     
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	     
	      pstmt.setString(1, usuario.getSenha());
	      pstmt.setString(2, usuario.getNivel());
	      pstmt.setString(3, usuario.getNome());
	      pstmt.setString(4, usuario.getSite());
	      pstmt.setString(5, usuario.getEmail());
	      pstmt.setString(6, usuario.getArea()); 
	      //System.out.println(pstmt.toString());
	      pstmt.executeUpdate();
	     
	      pstmt.close();
	      conn.commit();
	    } catch (SQLException sqle) {
	    	sqle.printStackTrace();
	      close(pstmt);
	      rollback(conn);
	      
	      throw sqle;
	    } finally {
	    	close(conn);
	    }
	}
	
	 
}
