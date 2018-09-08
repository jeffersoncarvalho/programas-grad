package ccacore;

/** 
 * The minimum kinds of events needed. List to be extended
 * in the future.  Clearly, SMP architectures and threads may
 * violate the simple assumptions.
 */

public interface EventType {

        static final int Error = -1;                // Someone got a bogus event object somehow.
        static final int ALL = 0;                   // Component wants to receive all event notices. 
                               					     // ALL itself never received.
        static final int ConnectPending = 1;     // A connection is about to be attempted.
        static final int Connected = 2;           // A connection has been made.
        static final int DisconnectPending = 3; // A disconnection is about to be attempted.
        static final int Disconnected = 4;       // A disconnection has been made.

}
