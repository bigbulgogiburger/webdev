package lib;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class BookFileManager {
	
	public static List<Book> readFile(File file) throws IOException{
		FileReader filereader=null;
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			filereader = new FileReader(file);
			bookList= readReader(filereader);
		}finally {
			if(filereader!=null) {
				filereader.close();
			}
		}
		return bookList;
	}
	
	public static void writeFile(String dir, String name,List<Book> bookList) throws IOException {
		OutputStream out = null;
		try {
			
			File dirent= new File(dir);
			
			if(!dirent.exists()) {
				dirent.mkdir();
			}
			
			File outFile = new File(dir,name);
			
			if(outFile.exists()) {
				outFile.delete();
			}
			
			out=new BufferedOutputStream(new FileOutputStream(outFile));
			for(int idx=0; idx<bookList.size();idx++) {
				String writeStr = bookList.get(idx).getTitle()+","+bookList.get(idx).getAuthor()+","+bookList.get(idx).getBook_id();
				
				
				byte[] b = writeStr.getBytes();
				
				out.write(b);
			}
		}catch(IOException e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if(out != null) {
					out.close();
				}
			}catch(Exception e) {
				
			}
		}
	}
	
	public static List<Book> readReader(Reader input) throws IOException{
		try {
			BufferedReader in = new BufferedReader(input);
			String line;
			List<Book> bookList = new ArrayList<Book>();
			
			while((line=in.readLine())!=null) {
				
				String[] writeStr=line.split(",");
				
				if(writeStr.length != 3) {
					continue;
				}
				
				Book book = new Book();
				book.setTitle(writeStr[0]);
				book.setAuthor(writeStr[1]);
				book.setBook_id(writeStr[2]);
				bookList.add(book);
			}
			return bookList;
		}finally {
			input.close();
		}
	}
}


