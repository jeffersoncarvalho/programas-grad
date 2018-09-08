import java.util.zip.*;

public class Zip
{
	private int compressedDataLength;
	
	public byte[] zip(String msg)
	{
		byte[] output = new byte[100];
		try{
			byte[] input = msg.getBytes("UTF-8");
 			Deflater compresser = new Deflater();
 			compresser.setInput(input);
 			compresser.finish();
 			compressedDataLength = compresser.deflate(output);
 		}
 		catch(Exception exc)
 		{
 			System.out.println (exc.toString());
 		}
 		
 		return output;
	}
	
	public String unzip(byte output[], int compressed)
	{
		String outputString ="";
		Inflater decompresser = new Inflater();
 		decompresser.setInput(output, 0, compressed);
 		byte[] result = new byte[1500];
 		try {
 			int resultLength = decompresser.inflate(result);
 			decompresser.end();

 			// Decode the bytes into a String
 			outputString = new String(result, 0, resultLength, "UTF-8");
		}
		catch (Exception ex) {
		}
		
		return outputString;
 		
	}
	
	public int getcompressedDataLength()
	{
		return compressedDataLength;
	}
}