package br.ufc.lia.es.solar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.ufc.lia.es.solar.model.UsuarioDisciplinaModel;

public class UsuarioDisciplinaDAO extends DAO{

	public UsuarioDisciplinaDAO(DataSource ds) {
		super(ds);
		
	}

	public List listByUsuarioLogin(String usuario_login){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<UsuarioDisciplinaModel> list = new ArrayList<UsuarioDisciplinaModel>() ;
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT disciplina_codigo" +
	      			   " FROM USUARIO_DISCIPLINA_UDS" +
	      			   " WHERE usuario_login = '" + usuario_login +"'";
	      			   
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	UsuarioDisciplinaModel usuarioModel = new UsuarioDisciplinaModel();
	    	usuarioModel.setDisciplina_codigo( rs.getString(1));
	        usuarioModel.setUsuario_login(usuario_login);
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
	
	public List listByDisciplinaCodigo(String disciplinaCodigo){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<UsuarioDisciplinaModel> list = new ArrayList<UsuarioDisciplinaModel>() ;
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT usuario_login" +
	      			   " FROM USUARIO_DISCIPLINA_UDS" +
	      			   " WHERE disciplina_codigo = '" + disciplinaCodigo +"'";
	      			   
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	UsuarioDisciplinaModel usuarioModel = new UsuarioDisciplinaModel();
	    	usuarioModel.setUsuario_login( rs.getString(1));
	        usuarioModel.setDisciplina_codigo(disciplinaCodigo);
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
	
	public void insert(UsuarioDisciplinaModel usuarioDisciplinaModel) throws SQLException {
	    String sql;
	    sql = "INSERT INTO USUARIO_DISCIPLINA_UDS (usuario_login, disciplina_codigo) VALUES (?, ?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	     
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, usuarioDisciplinaModel.getUsuario_login());
	      pstmt.setString(2, usuarioDisciplinaModel.getDisciplina_codigo());
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
}
