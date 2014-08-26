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
	private Map<String,Double> Dictionarys; 	//the Existing Dictionarys
	private List<String> Words;							//the Input vocabulary
	private int DocNum=0;								//the number of document
	private double Threshold;
	//the output data
	private Map<String ,Double> NewWords;			//output the PMI of each new input vocabulary
	private List<String> NewWordList;
	// output Words for different dictionary;
	// NewWordList.get(i) is the new input word list for the i-th dictionary;
	
	//the data used in the Process of PMI
	private Map<String,Double> DicWords;	
		//the input vocabulary which has already in the dictionarys	P(x)
	private Map<String,Double> UnKWords;	
		//the input vocabulary which is not in the dictionarys	P(y)
	private Map<String,Map<String,Double>> NewWordsPMIValue;
		//the input vocabulary which is not in the dictionayrs P(x,y)/(P(x)*P(y))

	public PMI(){
		this.InvertedIndex=new HashMap<String,List<Integer>>();
		this.Dictionarys= new HashMap<String,Double>();

		this.Words = new ArrayList<String>();
		this.DocNum=0;
		this.Threshold=0;

		this.DicWords= new HashMap<String,Double>();
		this.UnKWords = new HashMap<String,Double>();
		
		this.NewWordsPMIValue = new HashMap<String,Map<String,Double>>();
		
		this.NewWords = new HashMap<String,Double>();
		this.NewWordList = new ArrayList<String>();


	}
	
	public PMI(Map<String,List<Integer>> invertedindex,
			HashMap<String,Double> dictionary,
			List<String> wordlist,			
			int docnum,
			double threshold){
		this.InvertedIndex.putAll(invertedindex);
		this.Dictionarys.putAll(dictionary);
		this.Words.addAll(wordlist);
		this.DocNum=docnum;
		this.Threshold=threshold;
		
		this.DicWords= new HashMap<String,Double>();
		this.UnKWords = new HashMap<String,Double>();
		
		this.NewWordsPMIValue = new HashMap<String,Map<String,Double>>();
		this.NewWords = new HashMap<String,Double>();
		this.NewWordList = new ArrayList<String>();


	}
	
	public void SetInvertedIndex( Map<String,List<Integer>> invertedindex){
		this.InvertedIndex.clear();
		this.InvertedIndex.putAll(invertedindex);
	}
	public void SetDictionary(Map<String,Double> dictionary){
		this.Dictionarys.clear();
		this.Dictionarys.putAll( dictionary);
	}
	public void SetInputWords(List<String> wordlist){
		this.Words.clear();
		this.Words.addAll(wordlist);
	}
	public void setDocNum(int docNum){
		this.DocNum = docNum;
	}
	public void setThreshold( double threshold){
		this.Threshold=threshold;
	}
	
	public void inti(){
		    //赋初值，
		boolean tmpflag = false;

		for (String s:this.Words){

			if (this.Dictionarys.containsKey(s)){
				this.DicWords.put(s,(double)this.InvertedIndex.get(s).size()/(double)DocNum);

			}
			else
			{
				this.UnKWords.put(s,(double)this.InvertedIndex.get(s).size()/(double)DocNum);
				this.NewWordsPMIValue.put(s, new Hashtable<String,Double>());

			}
		}
		
	}
	
	private double JointPro(String x,String y){
		//x from dictionary y from unknow word calculate the P(x.y)

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
				double tmpPMIValue = this.JointPro(X, Y)/(P_x*P_y);
				this.NewWordsPMIValue.get(Y).put(X, Double.valueOf(tmpPMIValue));
				if (tmpPMIValue>this.Threshold){
					if (!this.NewWordList.contains(Y)){
						this.NewWordList.add(Y);
						this.NewWords.put(Y,this.wordweight(tmpPMIValue));
					}
				}
			}
		}				
	}
		
	
	private double wordweight(double pmivalue){
	    return pmivalue;
	}
	public List<String> GetNewWords(){
		return this.NewWordList;
	}
	
	public Map<String ,Double> GetNewWordValue(){
		return this.NewWords;
	}
	
	public void run(){
		this.inti();
		this.PMIvalue();
		
	}
	
	
	
	public static void main(String []argvs){
		System.out.println(new java.util.Date());
	}
	
}