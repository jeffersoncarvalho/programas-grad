/* Generated by RMICompiler.java -- do not edit */

package br.ufc.lia.es.solar.rme;

import rme.*;
import arcademis.*;

public class SolarServerServiceImpl_Stub extends MultiServerStub implements br.ufc.lia.es.solar.rme.ISolarServerService {
  public br.ufc.lia.es.solar.model.UsuarioModel efetuarLogin(java.lang.String param0, java.lang.String param1) throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.model.UsuarioModel resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      args.write(param1);
      int op = 0;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.model.UsuarioModel)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public br.ufc.lia.es.solar.util.MarshalableVector listarDisciplinasPorUsuario(java.lang.String param0) throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.util.MarshalableVector resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 1;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.util.MarshalableVector)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public br.ufc.lia.es.solar.model.DisciplinaModel retornaDisciplina(java.lang.String param0) throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.model.DisciplinaModel resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 2;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.model.DisciplinaModel)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public br.ufc.lia.es.solar.model.UsuarioModel retornaUsuario(java.lang.String param0) throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.model.UsuarioModel resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 3;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.model.UsuarioModel)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public br.ufc.lia.es.solar.util.MarshalableVector listarParticipantes(java.lang.String param0) throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.util.MarshalableVector resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 4;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.util.MarshalableVector)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public void incrementaAcessos(java.lang.String param0) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 5;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  public void enviaMensagemForum(br.ufc.lia.es.solar.model.MensagemModel param0) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 6;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  public br.ufc.lia.es.solar.util.MarshalableVector listarMensagemsForum(java.lang.String param0) throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.util.MarshalableVector resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 7;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.util.MarshalableVector)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public void enviaMensagemEmail(br.ufc.lia.es.solar.model.MensagemModel param0, java.lang.String param1) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      args.write(param1);
      int op = 8;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  public br.ufc.lia.es.solar.util.MarshalableVector listarAulas(java.lang.String param0) throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.util.MarshalableVector resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 9;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.util.MarshalableVector)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public void inserirUsuario(br.ufc.lia.es.solar.model.UsuarioModel param0) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 10;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  public void inserirMatricula(br.ufc.lia.es.solar.model.UsuarioDisciplinaModel param0) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 11;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  public void inserirDisciplina(br.ufc.lia.es.solar.model.DisciplinaModel param0) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 12;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  public br.ufc.lia.es.solar.util.MarshalableVector listarDisciplinasLite() throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.util.MarshalableVector resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      int op = 13;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.util.MarshalableVector)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public br.ufc.lia.es.solar.util.MarshalableVector listarUsuariosLite() throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.util.MarshalableVector resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      int op = 14;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.util.MarshalableVector)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public br.ufc.lia.es.solar.util.MarshalableVector listarProfessores() throws arcademis.ArcademisException {
    br.ufc.lia.es.solar.util.MarshalableVector resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      int op = 15;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = (br.ufc.lia.es.solar.util.MarshalableVector)future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
    return resp;
  }

  public void inserirAula(br.ufc.lia.es.solar.model.AulaModel param0) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 16;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  public void updateUsuario(br.ufc.lia.es.solar.model.UsuarioModel param0) throws arcademis.ArcademisException {
    Object resp = null;
    try {
      Stream args = OrbAccessor.getStream();
      args.write(param0);
      int op = 17;
      Stream future = invoke(args, op, '?', 0);
      if(future.isException()) {
        Exception e = (Exception)future.readObject();
        throw e;
      }
      resp = future.readObject();
    } catch (arcademis.ArcademisException e) {
      throw e;
    } catch (Exception e) {
      throw new arcademis.UnspecifiedException(e.toString());
    }
  }

  // End of generated code
}