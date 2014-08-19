import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndexWords{
	private List<String[]> Documents;
	private Map<String,List<Integer>> Index;
	public  InvertedIndexWords(){
		this.Documents = new ArrayList<String[]>();
		this.Index = new HashMap<String,List<Integer>>();
	}
	
	public void setDocuments(List<String[]> doc){
		this.Documents=null;
		this.Documents.addAll(doc);
	}
	public void run(){
		for (int i=0;i<this.Documents.size();i++){
			for (String s:this.Documents.get(i)){
				if (Index.containsKey(s)){
					this.Index.get(s).add(i);
				}
				else{
					this.Index.put(s, new ArrayList<Integer>());
					this.Index.get(s).add(i);
				}
			}
		}
	}
	public Map<String,List<Integer>> getIndex(){
		return this.Index;
	}
}