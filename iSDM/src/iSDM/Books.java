package iSDM;
import java.util.*;
import java.io.*;
public class Books implements Serializable{
	private static final long serialVersionUID = 1L;
	String ISBN;
	String BookId;
	String title;
	ArrayList<String> authors = new ArrayList<String>();
	int edition;
	String subject;
	int days;
	String Publisher;
	// testing push funtion
}
