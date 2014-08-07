import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class PMI{
	//the data from the input
	private Map<String,List<Integer>> InvertedIndex;	// Articles vocabulary iniverted Index
	private List<HashMap<String,Double>> Dictionarys; 	//the Existing Dictionarys
	private List<String> Words;							//the Input vocabulary
	int DocNum=0;										//the number of document
	//the output data
	private List<Map<String ,Double>> NewWords;				//output the PMI of each new input vocabulary
	
	//the data used in the Process of PMI
	private Map<String,Double> DicWords;				//the input vocabulary which has already in the dictionarys
	private Map<String,Map<String,Double>> UnKWords;						//	the input vocabulary which is not in the dictionarys
	public PMI(int docnum){
		this.InvertedIndex = new HashMap<String,List<Integer>>();
		this.Dictionarys = new ArrayList<HashMap<String,Double>>();
		this.Dictionarys.add(new HashMap<String,Double>());
		this.Words = new ArrayList<String>();
		this.NewWords = new ArrayList<Map<String ,Double>>();
		this.DocNum=docnum;
	}
	
	public void inti(){

		this.DicWords = new HashMap<String,Double>();
		this.UnKWords= new HashMap<String,Double>();
		
		
		boolean tmpflag = false;
		for (String s:this.Words){
			tmpflag = false;
			for (int i=0;i<this.Dictionarys.size();i++){
				if (this.Dictionarys.get(i).containsKey(s)){
					this.DicWords.put(s,(double)this.InvertedIndex.get(s).size()/(double)DocNum);
					tmpflag=true;
				}
			}
			if(!tmpflag){
				this.UnKWords.put(s,(double)this.InvertedIndex.get(s).size()/(double)DocNum);
			}
		}
		
	}
	private double JointPro(String x,String y){
		//x from dictionary y from unknow word

		double cont=0;
		for (Integer i :this.InvertedIndex.get(x)){
			if(this.InvertedIndex.get(y).contains(i))
				cont+=1;
		}
		return cont/this.DocNum;
	}
	
	

	private void PMIvalue(){
		
	}
	public void ComputePMI(){
		this.inti();
		this.PMIvalue();
	}
	
	
	
	public void main(String []argvs){
		System.out.println(new java.util.Date());
	}
	
}