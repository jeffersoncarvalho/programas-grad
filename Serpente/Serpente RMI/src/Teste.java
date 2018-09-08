import java.util.StringTokenizer;

public class Teste {
	public static void main(String args[])
	{
		String s = " COR 20 60 # COR 60 40 80 90 COR 85 20 30 45";
		StringTokenizer tokens = new StringTokenizer(s,"#",false);
		while(tokens.hasMoreTokens())
		{
			System.out.println (tokens.nextToken());
		}
		 
	}
}
