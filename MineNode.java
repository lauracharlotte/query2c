



public class MineNode {
	int count = 0;
	MineNode[] myArrayofNodes = new MineNode[27];//should it actually be 25 because of value zero?
	int mod = 0;


	public int getValue() 
	{
		return count;
	}
	
	//adds to Node Value when there is a new Node
	public void addValue()
	{
		count ++;
	}
	
	public int getMod() 
	{
		return mod;
	}
	
	//adds to Node Value when there is a new Node
	public void addMod()
	{
		mod ++;
	}

}
