import java.util.ArrayList;
import java.util.List;

public class Learnmultithread implements Runnable{
	
	private List<Integer> contList;
	public Learnmultithread(){
		this.contList= new ArrayList<Integer>();
		for (int i= 0;i<100;i++)
			this.contList.add(i);
	}
	public void run(){
		System.out.println(new java.util.Date());
		Thread.yield();
	}
}