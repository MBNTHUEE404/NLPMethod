import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class PMI{
	//the data from the input
	private Map<String,List<Integer>> InvertedIndex;	// Articles vocabulary iniverted Index
	private List<HashMap<String,Double>> Dictionarys; 	//the Existing Dictionarys
	private List<String> Words;							//the Input vocabulary
	private int DocNum=0;										//the number of document
	private List<Double> Threshold;
	//the output data
	private List<Map<String ,Double>> NewWords;				//output the PMI of each new input vocabulary
	
	//the data used in the Process of PMI
	private Map<String,Double> DicWords;				//the input vocabulary which has already in the dictionarys
	private Map<String,Double> UnKWords;						//	the input vocabulary which is not in the dictionarys
	private Map<String,Map<String,Double>> NewWordsPMIValue;
	public PMI(int docnum,List<Double> threshold){
		this.InvertedIndex = new HashMap<String,List<Integer>>();
		this.Dictionarys = new ArrayList<HashMap<String,Double>>();
		this.Dictionarys.add(new HashMap<String,Double>());
		this.Words = new ArrayList<String>();
		this.NewWords = new ArrayList<Map<String ,Double>>();
		this.DocNum=docnum;
		this.Threshold.addAll(threshold);
	}
	
	public void inti(){

		this.DicWords = new HashMap<String,Double>();
		this.UnKWords= new HashMap<String,Double>();
		this.NewWordsPMIValue = new Hashtable<String,Map<String,Double>>();
		
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
				this.NewWordsPMIValue.put(s, new Hashtable<String,Double>());
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
		//x is the dictionary words y is the unknow words
		double P_y=0.0;
		double P_x=0.0;
		String Y="";
		String X="";
		Iterator iter_y = this.NewWordsPMIValue.entrySet().iterator();
		while (iter_y.hasNext()){
			Map.Entry entry_y = (Map.Entry) iter_y.next();
			 P_y=this.UnKWords.get(entry_y.getKey()).doubleValue();
			 Y = (String)entry_y.getKey();
			
			Iterator iter_x = this.DicWords.entrySet().iterator();
			while (iter_x.hasNext()){
				Map.Entry entry_x = (Map.Entry) iter_x.next();
				P_x = (Double)entry_x.getValue();
				X =(String) entry_x.getKey();
				this.NewWordsPMIValue.get(Y).put(X, Double.valueOf(this.JointPro(X, Y)/(P_x*P_y)));
			
			}
					
		}
		
	}
	private void GetNewWords(){
		for 
	}
	
	public void ComputePMI(){
		this.inti();
		this.PMIvalue();
		
	}
	
	
	
	public static void main(String []argvs){
		System.out.println(new java.util.Date());
	}
	
}