package exceptions;
import ccacore.Type;


public abstract class TypeMismatchException extends CCAException {

	/** @return the enumerated value Type sought */
   public abstract Type getRequestedType();
   /** @return the enumerated value Type sought */
   public abstract Type getActualType();
}
