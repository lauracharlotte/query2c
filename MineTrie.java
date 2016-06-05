

import java.util.ArrayList;

public class MineTrie {
	
	MineNode root = new MineNode();
	int wordCount = 0;
	int nodeCount = 1;
	public String VeryLongString  = "";

	public void add(String word, int mod) 
	{	
		MineNode CurNode = new MineNode();
		CurNode = root;
		int newNodeCount = 0;
		String newWord = word.toLowerCase();
		
		char[] allLetters = newWord.toCharArray();//found in stackoverflow, do I also need the Util??
		int makeAscii = 97;
		
		for(int i = 0; i < allLetters.length; i++)
		{
			int CurrentLetter = allLetters[i];
			int indexspot = CurrentLetter-makeAscii;
			if(allLetters[i] == ' ')
			{
				indexspot = 26;
			}
			if(CurNode.myArrayofNodes[indexspot] == null)
			{
				MineNode addThisNode = new MineNode();
				CurNode.myArrayofNodes[indexspot] = addThisNode;
				CurNode = CurNode.myArrayofNodes[indexspot];
				nodeCount++;
				if(i == allLetters.length-1)
				{
					CurNode.addValue();
					wordCount++;
					if(mod ==1)
					{
						CurNode.addMod();
					}
				}
			}
			else
			{
				CurNode = CurNode.myArrayofNodes[indexspot];
				if(i == allLetters.length-1)
				{
					CurNode.addValue();
					if(mod ==1)
					{
						CurNode.addMod();
					}
				}
			}
		}
	}


	public MineNode find(String word) 
	{
		String newWord = word.toLowerCase(); //found in informit.com
		char[] thechar = newWord.toCharArray();//found in stackoverflow, do I also need the Util??
		MineNode CurNode = new MineNode();
		CurNode = root;
		for(int i = 0; i<thechar.length; i++)
		{
			int getAscii = 97;
			int b = thechar[i];
			int indexspot = b-getAscii;
			if(thechar[i]==' ')
			{
				indexspot = 26;
			}
			if(CurNode.myArrayofNodes[indexspot] != null)
			{
				if(i < thechar.length)
				{
					CurNode = CurNode.myArrayofNodes[indexspot]; //got this idea from TA
				}
			}
			else
			{
				return null;
			}
		}
		if(CurNode.getValue() > 0)
		{
			System.out.println("here?");
			return CurNode;
		}
		else
		{
			return null;
		}
	}
	
	public MineNode find2(String word)  //Repeat of find so my suggestSimilarWord can use MineNodes instead of just "Node."
	{
		String newWord = word.toLowerCase(); //found in informit.com
		char[] thechar = newWord.toCharArray();//found in stackoverflow
		MineNode CurNode = new MineNode();
		CurNode = root;
		for(int i = 0; i<thechar.length; i++)
		{
			int getAscii = 97;
			int b = thechar[i];
			int indexspot = b-getAscii;
			if(thechar[i]==' ')
			{
				indexspot = 26;
			}
			if(CurNode.myArrayofNodes[indexspot] != null)
			{
				if(i < thechar.length)
				{
					CurNode = CurNode.myArrayofNodes[indexspot]; //got this idea from TA
				}
			}
			else
			{
				return null;
			}
		}
		if(CurNode.getValue() > 0)
		{
			System.out.println("here?");
			System.out.println(CurNode.getValue());
			System.out.println(CurNode.getMod());
			return CurNode;
		}
		else
		{
			return null;
		}
	}
	
	public int getWordCount() 
	{
		return wordCount;
	}

	public int getNodeCount() 
	{
		return nodeCount;
	}

	public String toString()
	{
		VeryLongString = "";	
		addToVeryLongString(VeryLongString, root);
		return VeryLongString;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 17;
		int result = 1;
		result = prime * result + nodeCount;
		result = prime * result + wordCount;
		return result;
	}

	@Override //Add on to, compare the toStrings
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MineTrie other = (MineTrie) obj;
		if (nodeCount != other.nodeCount)
			return false;
		if (wordCount != other.wordCount)
			return false;
		if (toString().equals(other.toString()))
		{
			return true;
		}
		return false;
	}
	
	public void addToVeryLongString(String word, MineNode Current)
	{
		String LongString = word;
		for(int i = 0; i<26; i++)
		{
			if(Current.myArrayofNodes[i] == null)
			{}
			else
			{
				int asciiValue = 97 + i;
				char hellos = (char)asciiValue;
				LongString += hellos;
				if(Current.myArrayofNodes[i].getValue() > 0)
				{
					String StringAndValue = LongString + " " + Current.myArrayofNodes[i].getValue() + "\n";
					VeryLongString+=StringAndValue;
					addToVeryLongString(LongString, Current.myArrayofNodes[i]);
					StringBuilder sb = new StringBuilder(LongString);
					sb.deleteCharAt(sb.length()-1);
					String thissb = sb.toString();
					LongString = thissb;
				}
				else
				{
					addToVeryLongString(LongString, Current.myArrayofNodes[i]);
					StringBuilder sb = new StringBuilder(LongString);
					sb.deleteCharAt(sb.length()-1);
					String thissb = sb.toString();
					LongString = thissb;
				}
			}	
		}
	}
	
	public String CloseWord(String inputWord)
	{
		String Special = "";
		int frequency =0;
		ArrayList<String> LongList = new ArrayList<String>();
		String Word;
		Word = inputWord.toLowerCase();
		if(find2(Word)!=null)
		{
			return Word;
		}
		StringBuilder sb = new StringBuilder(Word);
		//Deletion 1 ---------------------------------------------------------
		for(int i = 0; i<sb.length(); i++)
		{
			StringBuilder hello = new StringBuilder(Word);
			hello.deleteCharAt(i);
			String thisWord = hello.toString();
			LongList.add(thisWord);
			if(find(thisWord) != null)
			{
				MineNode thisNode = new MineNode();
				thisNode = find2(thisWord);
				int thisfrequ = thisNode.count;
				if(thisfrequ > frequency)
				{
					frequency = thisfrequ;
					Special = thisWord;
				}
			}
		}
		//Deletion 2 ----------------------------------------------------------
		for(int j = 0; j<sb.length()-1; j++)
		{
			char char1= sb.charAt(j);
			char char2=sb.charAt(j+1);
			StringBuilder hi = new StringBuilder(Word);
			hi.setCharAt(j, char2);
			hi.setCharAt(j+1, char1);
			String thisWord1 = hi.toString();
			LongList.add(thisWord1);
			if(find(thisWord1) != null)
			{
				MineNode thisNode1 = new MineNode();
				thisNode1 = find2(thisWord1);
				int thisfrequ1 = thisNode1.count;
				if(thisfrequ1 > frequency)
				{
					frequency = thisfrequ1;
					Special = thisWord1;
				}
			}
		}
		//Deletion 3 ------------------------------------------------------------
		for(int k = 0; k<sb.length(); k++)
		{
			StringBuilder heyy = new StringBuilder(Word);
			for(int l = 0; l<26; l++)
			{ //check if the same value;
				int asciiValue = 97 + l;
				char hellos = (char)asciiValue;
				heyy.setCharAt(k, hellos);
				String thisWord2 = heyy.toString();
				LongList.add(thisWord2);
				if(find(thisWord2) != null)
				{
					MineNode thisNode2 = new MineNode();
					thisNode2 = find2(thisWord2);
					int thisfrequ2 = thisNode2.count;
					if(thisfrequ2 > frequency)
					{
						frequency = thisfrequ2;
						Special = thisWord2;
					}
				}
			}
		}
		//Deletion 4 ---------------------------------------------------------------
		for(int m =0; m<sb.length()+1; m++)
		{
			for(int n = 0; n <26; n++)
			{
				StringBuilder hola = new StringBuilder(Word);
				int asciiValue1 = 97 + n;
				char hellooo = (char)asciiValue1;
				hola.insert(m, hellooo);
				String thisWord3 = hola.toString();
				LongList.add(thisWord3);
				if(find(thisWord3) != null)
				{
					MineNode thisNode3 = new MineNode();
					thisNode3 = find2(thisWord3);
					int thisfrequ3 = thisNode3.count;
					if(thisfrequ3 > frequency)
					{
						frequency = thisfrequ3;
						Special = thisWord3;
					}
				}
			}
		}
		if(Special.equals(""))
		{
			//COPY AND PAST THE WHOLE THING OVER AGAIN EXCEPT WITHOUT THE LONG LIST WHEN ITS ALL WORKING
			for(int w =0; w<LongList.size(); w++)
			{
				Word=LongList.get(w);
				System.out.println(Word);
				StringBuilder sb2 = new StringBuilder(Word);
				//<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
				for(int i = 0; i<sb2.length(); i++)
				{
					StringBuilder hello = new StringBuilder(Word);
					hello.deleteCharAt(i);
					String thisWord = hello.toString();
					if(find(thisWord) != null)
					{
						MineNode thisNode = new MineNode();
						thisNode = find2(thisWord);
						int thisfrequ = thisNode.count;
						if(thisfrequ > frequency)
						{
							frequency = thisfrequ;
							Special = thisWord;
						}
					}
				}
				//Deletion 2 ----------------------------------------------------------
				for(int j = 0; j<sb2.length()-1; j++)
				{
					char char1= sb2.charAt(j);
					char char2=sb2.charAt(j+1);
					StringBuilder hi = new StringBuilder(Word);
					hi.setCharAt(j, char2);
					hi.setCharAt(j+1, char1);
					String thisWord1 = hi.toString();
					if(find(thisWord1) != null)
					{
						MineNode thisNode1 = new MineNode();
						thisNode1 = find2(thisWord1);
						int thisfrequ1 = thisNode1.count;
						if(thisfrequ1 > frequency)
						{
							frequency = thisfrequ1;
							Special = thisWord1;
						}
					}
				}
				//Deletion 3 ------------------------------------------------------------
				for(int k = 0; k<sb2.length(); k++)
				{
					StringBuilder heyy = new StringBuilder(Word);
					for(int l = 0; l<26; l++)
					{ //check if the same value;
						int asciiValue = 97 + l;
						char hellos = (char)asciiValue;
						heyy.setCharAt(k, hellos);
						String thisWord2 = heyy.toString();
						if(find(thisWord2) != null)
						{
							MineNode thisNode2 = new MineNode();
							thisNode2 = find2(thisWord2);
							int thisfrequ2 = thisNode2.count;
							if(thisfrequ2 > frequency)
							{
								frequency = thisfrequ2;
								Special = thisWord2;
							}
						}
					}
				}
				//Deletion 4 ---------------------------------------------------------------
				for(int m =0; m<sb2.length()+1; m++)
				{
					for(int n = 0; n <26; n++)
					{
						StringBuilder hola = new StringBuilder(Word);
						int asciiValue1 = 97 + n;
						char hellooo = (char)asciiValue1;
						hola.insert(m, hellooo);
						String thisWord3 = hola.toString();
						if(find(thisWord3) != null)
						{
							MineNode thisNode3 = new MineNode();
							thisNode3 = find2(thisWord3);
							int thisfrequ3 = thisNode3.count;
							if(thisfrequ3 > frequency)
							{
								frequency = thisfrequ3;
								Special = thisWord3;
							}
						}
					}
				}
				//<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
			}	
		}
		if(Special.equals(""))
		{
			return null;
		}
		else
		{
			return Special;
		}
	}
}
