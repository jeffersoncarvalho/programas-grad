/* file name  : VisibleByteArrayOutputStream.java
 * authors    : Ricardo Corrêa (correa@lia.ufc.br)
 * created    : Apr 7, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.workspace;

import java.io.ByteArrayOutputStream;


/**
 * TODO Describe this type.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public class VisibleByteArrayOutputStream extends ByteArrayOutputStream {
	
	/**
	 * 
	 */
	public VisibleByteArrayOutputStream() {
	}
	
	public byte[] getBuf() {
		return buf;
	}
}