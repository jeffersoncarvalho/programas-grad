// Skeleton class generated by rmic, do not edit.
// Contents subject to change without notice.

public final class NimbusImpl_Skel
    implements java.rmi.server.Skeleton
{
    private static final java.rmi.server.Operation[] operations = {
	new java.rmi.server.Operation("java.lang.String getAllSnakes()"),
	new java.rmi.server.Operation("void msgReceived(int, java.lang.String)")
    };
    
    private static final long interfaceHash = 2529378837228077576L;
    
    public java.rmi.server.Operation[] getOperations() {
	return (java.rmi.server.Operation[]) operations.clone();
    }
    
    public void dispatch(java.rmi.Remote obj, java.rmi.server.RemoteCall call, int opnum, long hash)
	throws java.lang.Exception
    {
	if (opnum < 0) {
	    if (hash == 380253322980507009L) {
		opnum = 0;
	    } else if (hash == 1896962944882604813L) {
		opnum = 1;
	    } else {
		throw new java.rmi.UnmarshalException("invalid method hash");
	    }
	} else {
	    if (hash != interfaceHash)
		throw new java.rmi.server.SkeletonMismatchException("interface hash mismatch");
	}
	
	NimbusImpl server = (NimbusImpl) obj;
	switch (opnum) {
	case 0: // getAllSnakes()
	{
	    call.releaseInputStream();
	    java.lang.String $result = server.getAllSnakes();
	    try {
		java.io.ObjectOutput out = call.getResultStream(true);
		out.writeObject($result);
	    } catch (java.io.IOException e) {
		throw new java.rmi.MarshalException("error marshalling return", e);
	    }
	    break;
	}
	    
	case 1: // msgReceived(int, String)
	{
	    int $param_int_1;
	    java.lang.String $param_String_2;
	    try {
		java.io.ObjectInput in = call.getInputStream();
		$param_int_1 = in.readInt();
		$param_String_2 = (java.lang.String) in.readObject();
	    } catch (java.io.IOException e) {
		throw new java.rmi.UnmarshalException("error unmarshalling arguments", e);
	    } catch (java.lang.ClassNotFoundException e) {
		throw new java.rmi.UnmarshalException("error unmarshalling arguments", e);
	    } finally {
		call.releaseInputStream();
	    }
	    server.msgReceived($param_int_1, $param_String_2);
	    try {
		call.getResultStream(true);
	    } catch (java.io.IOException e) {
		throw new java.rmi.MarshalException("error marshalling return", e);
	    }
	    break;
	}
	    
	default:
	    throw new java.rmi.UnmarshalException("invalid method number");
	}
    }
}
