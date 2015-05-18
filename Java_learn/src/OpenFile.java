import java.io.*;

public class OpenFile {
	public static void main(String[] args){
		File file = new File("./a.txt");
		try{
		InputStream in = new FileInputStream(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
