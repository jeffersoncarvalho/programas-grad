/* file name  : TimingPolicy.java
 * authors    : Ricardo Corrêa (correa@lia.ufc.br)
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
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public interface TimingPolicy {
	void send();
	Object receive();

}
