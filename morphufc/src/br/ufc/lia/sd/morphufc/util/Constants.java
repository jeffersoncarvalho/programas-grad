package br.ufc.lia.sd.morphufc.util;

public abstract class Constants {

	public static int SERVER_PORT = 1021;
	public final static String SEPARATOR = "#";
	public final static int DATA = 1;
	public final static int REQUIRE_FILE = 2;
	public final static int ACK = 3;
	public final static int NACK = 4;
	public final static int BYE = 5;
	public final static int HAS_FILE_LOCAL = 6;
	public final static int HAS_FILE_REMOTE = 12;
	public final static int DOWNLOAD = 7;
	public final static int ACK_HAS_FILE_LOCAL = 8;
	
	
	public final static int CRAWLER_SEARCH = 9;
	public final static int NACK_CRAWLER_SEARCH = 10;
	public final static int CRAWLER_HAS_FILE = 11;
	
	public final static int FILE_NOT_FOUND = 13;
}
