package ccacore;

/**
 * some standard (required) CCA ports.
 */
    
/**
* Go, component, go!
*/ 
public interface GoPort extends Port {
        /** 
         * Execute some encapsulated functionality on the component. 
         * Return 0 if ok, -1 if internal error but component may be 
         * used further, and -2 if error so severe that component cannot
         * be further used safely.
         */
        int go();
    }
