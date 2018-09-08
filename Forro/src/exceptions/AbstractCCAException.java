package exceptions;



/** Most CCA interfaces throw this exception */   
abstract class AbstractCCAException /*extends SIDL.BaseException*/ {
       abstract CCAExceptionType getCCAExceptionType();
   } // end class CCAException