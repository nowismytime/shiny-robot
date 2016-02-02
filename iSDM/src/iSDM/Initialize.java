package iSDM;
import java.util.*;
import java.io.*;
public class Initialize {
	public static void inAll (HashMap<String, Users> UserList, HashMap<String, Books> BookList, ArrayList<Issues> IssueList, ArrayList<Returns> ReturnList) 
			throws IOException, ClassNotFoundException
	{		
		FileInputStream fis = null;
	    try {
	    	//System.out.println(Initialize.class.getResourceAsStream("Users.txt"));
	    	//fis = Initialize.class.getResourceAsStream("Users.txt");
	        fis = new FileInputStream("Users.txt");
	        while (true) {
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            Users nuser = new Users();
	            nuser = (Users) ois.readObject();
	            UserList.put(nuser.rNum, nuser);
	        }
	    } catch (EOFException ignored) {
	        // as expected
	    } finally {
	        if (fis != null)
	            fis.close();
	    }
	    
	    fis = null;
	    try {
	    	//fis = Initialize.class.getResourceAsStream("Books.txt");
	        fis = new FileInputStream("Books.txt");
	        while (true) {
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            Books nBook = new Books();
	            nBook = (Books) ois.readObject();
	            BookList.put(nBook.BookId, nBook);
	        }
	    } catch (EOFException ignored) {
	        // as expected
	    } finally {
	        if (fis != null)
	            fis.close();
	    }
	    
	    fis =null;
	    try {
	    	//fis = Initialize.class.getResourceAsStream("Issues.txt");
	        fis = new FileInputStream("Issues.txt");
	        while (true) {
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            Issues nIssue = new Issues();
	            nIssue = (Issues) ois.readObject();
	            IssueList.add(nIssue);
	            
	        }
	    } catch (EOFException ignored) {
	        // as expected
	    } finally {
	        if (fis != null)
	            fis.close();
	    }
	    
	    fis = null;
	    try {
	    	//fis = Initialize.class.getResourceAsStream("Returns.txt");
	        fis = new FileInputStream("Returns.txt");
	        while (true) {
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            Returns nReturn = new Returns();
	            nReturn = (Returns) ois.readObject();
	            ReturnList.add(nReturn);
	        }
	    } catch (EOFException ignored) {
	        // as expected
	    } finally {
	        if (fis != null)
	            fis.close();
	    }
	}	

}
