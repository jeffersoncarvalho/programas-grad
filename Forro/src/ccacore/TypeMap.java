package ccacore;

import exceptions.TypeMismatchException;



/**   
 *  A CCA map.  Maps a string key to a particular value. Types are
 *  strictly enforced.  For example, values places into the map
 *  using putInt can be retrieved only using getInt.  Calls to
 *  getLong, getString, getIntArray and other get methods will
 *  fail (i.e. return the default value). 
 */
public interface TypeMap {
	 

	 /** Keep the value */
    Object[] value = null;
	
	 /** Create an exact copy of this Map */
    TypeMap cloneTypeMap();

    /** Create a new Map with no key/value associations. */
    TypeMap cloneEmpty();
    
    /*
     * Get the scalar value associated with a given key.  If the key
     * was not found, return the default value. 
     */
    int      getInt(String key, int dflt) throws TypeMismatchException;
    long     getLong(String key, long dflt) throws TypeMismatchException;
    float    getFloat(String key, float dflt) throws TypeMismatchException;
    double   getDouble(String key, double dflt) throws TypeMismatchException;
//    fcomplex getFcomplex(String key, fcomplex dflt) throws TypeMismatchException;
//    dcomplex getDcomplex(String key, dcomplex dflt) throws TypeMismatchException;
    String   getString(String key, String dflt) throws TypeMismatchException;
    boolean     getBool(String key, boolean dflt) throws TypeMismatchException;
    
    /*
     * Get an array value associated with a given key.  If the key
     * was not found, return the dflt value. 
     */
     int[] getIntArray(String key, int[] dflt) throws TypeMismatchException;
     long[] getLongArray(String key, long[] dflt) throws TypeMismatchException;
     float[]    getFloatArray(String key, float[] dflt) throws TypeMismatchException;
     double[]   getDoubleArray(String key, double[] dflt) throws TypeMismatchException;
//     fcomplex[] getFcomplexArray(String key, fcomplex[] dflt) throws TypeMismatchException;
//     dcomplex[] getDcomplexArray(String key, dcomplex[] dflt) throws TypeMismatchException;
     String[]   getStringArray(String key, String[] dflt) throws TypeMismatchException; 
     boolean[]  getBoolArray(String key, boolean[] dflt) throws TypeMismatchException;
    
    /** 
     * Assign a key and value. Any value previously assigned
     * to the same key will be overwritten so long as it
     * is of the same type. If types conflict, an exception occurs.
     */
    void putInt(String key, int value) throws TypeMismatchException;
    void putLong(String key, long value) throws TypeMismatchException;
    void putFloat(String key, float value) throws TypeMismatchException;
    void putDouble(String key, double value) throws TypeMismatchException;
//    void putFcomplex(String key, fcomplex value) throws TypeMismatchException;
//    void putDcomplex(String key, dcomplex value) throws TypeMismatchException;
    void putString(String key, String value) throws TypeMismatchException;
    void putBool(String key, boolean value) throws TypeMismatchException;
    
    void putIntArray(String key, int value) throws TypeMismatchException;
    void putLongArray(String key, long value) throws TypeMismatchException;
    void putFloatArray(String key, float value) throws TypeMismatchException;
    void putDoubleArray(String key, double value) throws TypeMismatchException;
//    void putFcomplexArray(String key, fcomplex value) throws TypeMismatchException;
//    void putDcomplexArray(String key, dcomplex value) throws TypeMismatchException;
    void putStringArray(String key, String value) throws TypeMismatchException;
    void putBoolArray(String key, boolean value) throws TypeMismatchException;
    
    /** Make the key and associated value disappear from the object. */
    void remove (String key);
    
    /** 
     *  Get all the names associated with a particular type
     *  without exposing the data implementation details.  The keys
     *  will be returned an arbitrary order. If type specified is
     *  None (no specification) all keys of all types are returned.
     */
    String getAllKeys(Type t);
    
    /** Return true if the key exists this map */
    boolean hasKey(String key);
    
    /** Return the type of the value associated with this key */
    Type typeOf(String key);
    
}  // end interface TypeMap

