/* Generated by RMICompiler.java -- do not edit */

package br.ufc.lia.es.solar.rme;

import rme.*;
import rme.server.*;

import arcademis.*;

public class SolarServerServiceImpl_Skeleton extends arcademis.server.Skeleton {

  public Stream dispatch(RemoteCall r) throws Exception {

    RmeRemoteCall remoteCall = (RmeRemoteCall)r;
    Stream returnStr = OrbAccessor.getStream();
    Stream args = remoteCall.getArguments();

    switch (remoteCall.getOperationCode()) {
      case 0: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        java.lang.String param1 = (java.lang.String)args.readObject();
        br.ufc.lia.es.solar.model.UsuarioModel retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).efetuarLogin(param0, param1);
        returnStr.write(retValue);
      }
      break;
      case 1: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        br.ufc.lia.es.solar.util.MarshalableVector retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).listarDisciplinasPorUsuario(param0);
        returnStr.write(retValue);
      }
      break;
      case 2: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        br.ufc.lia.es.solar.model.DisciplinaModel retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).retornaDisciplina(param0);
        returnStr.write(retValue);
      }
      break;
      case 3: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        br.ufc.lia.es.solar.model.UsuarioModel retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).retornaUsuario(param0);
        returnStr.write(retValue);
      }
      break;
      case 4: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        br.ufc.lia.es.solar.util.MarshalableVector retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).listarParticipantes(param0);
        returnStr.write(retValue);
      }
      break;
      case 5: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).incrementaAcessos(param0);
        returnStr.write(retValue);
      }
      break;
      case 6: {
        br.ufc.lia.es.solar.model.MensagemModel param0 = (br.ufc.lia.es.solar.model.MensagemModel)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).enviaMensagemForum(param0);
        returnStr.write(retValue);
      }
      break;
      case 7: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        br.ufc.lia.es.solar.util.MarshalableVector retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).listarMensagemsForum(param0);
        returnStr.write(retValue);
      }
      break;
      case 8: {
        br.ufc.lia.es.solar.model.MensagemModel param0 = (br.ufc.lia.es.solar.model.MensagemModel)args.readObject();
        java.lang.String param1 = (java.lang.String)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).enviaMensagemEmail(param0, param1);
        returnStr.write(retValue);
      }
      break;
      case 9: {
        java.lang.String param0 = (java.lang.String)args.readObject();
        br.ufc.lia.es.solar.util.MarshalableVector retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).listarAulas(param0);
        returnStr.write(retValue);
      }
      break;
      case 10: {
        br.ufc.lia.es.solar.model.UsuarioModel param0 = (br.ufc.lia.es.solar.model.UsuarioModel)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).inserirUsuario(param0);
        returnStr.write(retValue);
      }
      break;
      case 11: {
        br.ufc.lia.es.solar.model.UsuarioDisciplinaModel param0 = (br.ufc.lia.es.solar.model.UsuarioDisciplinaModel)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).inserirMatricula(param0);
        returnStr.write(retValue);
      }
      break;
      case 12: {
        br.ufc.lia.es.solar.model.DisciplinaModel param0 = (br.ufc.lia.es.solar.model.DisciplinaModel)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).inserirDisciplina(param0);
        returnStr.write(retValue);
      }
      break;
      case 13: {
        br.ufc.lia.es.solar.util.MarshalableVector retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).listarDisciplinasLite();
        returnStr.write(retValue);
      }
      break;
      case 14: {
        br.ufc.lia.es.solar.util.MarshalableVector retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).listarUsuariosLite();
        returnStr.write(retValue);
      }
      break;
      case 15: {
        br.ufc.lia.es.solar.util.MarshalableVector retValue = ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).listarProfessores();
        returnStr.write(retValue);
      }
      break;
      case 16: {
        br.ufc.lia.es.solar.model.AulaModel param0 = (br.ufc.lia.es.solar.model.AulaModel)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).inserirAula(param0);
        returnStr.write(retValue);
      }
      break;
      case 17: {
        br.ufc.lia.es.solar.model.UsuarioModel param0 = (br.ufc.lia.es.solar.model.UsuarioModel)args.readObject();
        Marshalable retValue = null;
        ((br.ufc.lia.es.solar.rme.SolarServerServiceImpl)super.remoteObject).updateUsuario(param0);
        returnStr.write(retValue);
      }
      break;
      default: {
        throw new ArcademisException("Invalid operation in remote method request");
      }
    }    // end switch

    return returnStr;
  }    // end dispatch

}
