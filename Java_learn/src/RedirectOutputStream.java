import java.io.FileNotFoundException;
import java.io.PrintStream;

public class RedirectOutputStream {
	public static void main(String[] args){
		try{
			PrintStream out = System.out;			// ����ԭ�������
			PrintStream my = new PrintStream("./log.txt");
			System.setOut(my);
			int age = 18;
			System.out.println("age has been decleared sucessfully, init 18.");
			System.setOut(out); 					//�ָ�ԭ���������
			System.out.println("program over, check log file later.");
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
