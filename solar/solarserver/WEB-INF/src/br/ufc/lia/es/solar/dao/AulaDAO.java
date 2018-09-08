package br.ufc.lia.es.solar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.ufc.lia.es.solar.model.AulaModel;

public class AulaDAO extends DAO{

	public AulaDAO(DataSource ds) {
		super(ds);
		
	}
	
	public List listByDisciplinaCodigo(String disciplinaCodigo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<AulaModel> list = new ArrayList<AulaModel>() ;
		try {
	      conn = ds.getConnection();
	      String sql = "SELECT disciplina_codigo, assunto, descricao, tarefa, data " +
	      			   " FROM AULA_AUL " +
	      			   " WHERE disciplina_codigo = '" + disciplinaCodigo +"'";
	      			   
	      			    
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	AulaModel aulaModel = new AulaModel();
	    	aulaModel.setDisciplinaCodigo(disciplinaCodigo);
	    	aulaModel.setAssunto(rs.getString(2));
	    	aulaModel.setDescricao(rs.getString(3));
	    	aulaModel.setTarefa(rs.getString(4));
	    	aulaModel.setData(rs.getString(5));
	        list.add(aulaModel);
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
	
	public void insert(AulaModel aulaModel) throws SQLException {
	    String sql;
	    sql = "INSERT INTO AULA_AUL (disciplina_codigo, assunto, descricao, tarefa, data) VALUES (?, ?, ?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	     
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, aulaModel.getDisciplinaCodigo());
	      pstmt.setString(2, aulaModel.getAssunto());
	      pstmt.setString(3, aulaModel.getDescricao());
	      pstmt.setString(4, aulaModel.getTarefa());
	      pstmt.setString(5, aulaModel.getData());
	      
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
