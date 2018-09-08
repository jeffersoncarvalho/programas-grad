/* file name  : TimingPolicy.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 18, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package timing;

/**
 * Interface to define the timing characteristics of the communications
 * between models.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public interface TimingPolicy {
	void send();
	Object receive();

}
