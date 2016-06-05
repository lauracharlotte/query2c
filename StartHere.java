

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartHere {
	public static void main(String [] args){
		System.out.println("Hello I'm working");
		
		//Open all files, this goes through each of your files
		File allFiles = new File("docs/");
		File[] listOfFiles = allFiles.listFiles();
		int tester = 0;
		TheMiddleWoman middleWoman = new TheMiddleWoman();
		for(File curFile: listOfFiles)
		{
			System.out.println("Got1");
			try {
				Scanner scans = new Scanner(curFile);
				int firstSixWordSkip = 0;
				listOfFiles.toString();
				while(scans.hasNext())
				{
					if(firstSixWordSkip >2)
					{
						int curID =0;
						String query = "";
						String date = "";
						String time = "";
						if(scans.hasNextInt())
						{
							curID = scans.nextInt();
						}
						int i = 1;
						while(scans.hasNext())
						{
							String curentSpot = scans.next();
	
							if(curentSpot.startsWith("2006-"))
							{	
								date = curentSpot;
								break;
							}
							else
							{
								if(i==1)
								{
									query += curentSpot;
								}
								else
								{
									query += " " + curentSpot;
								}
							}
							i++;
							//middleWoman.pleaseAddIntoTrie(query, date, time, curID);
							
						}
						
						time = scans.next();
						System.out.println(query);
						middleWoman.pleaseAddIntoTrie(query, date, time, curID);
					}
					else
					{
						scans.next();
						firstSixWordSkip++;
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String answer = "";
		while(!answer.equals("-1"))
		{
			System.out.println("Hello! Please type in what you would like to search for");
			Scanner user = new Scanner(System.in);
			answer = user.nextLine();
			middleWoman.pleaseCheckforQueryInTrie(answer);
		}
		
		
		
		
		
		
		
		//TESTING TRIE
		String trieTest = "hello laura";
		String trieTest2 = "A bunny SAID hello!!";
		String trieTest3 = "today is a good day";
		String trieTest4 = "A happy owl is a good owl";
		String trieTest5 = "hello Laura";

		String time = "12:33:15";
		String timeArray[] = time.split(":");
		String hour = timeArray[0];
		String minute = timeArray[1];
		String minute2 = "20";
		if(Integer.valueOf(minute)-Integer.valueOf(minute2)<=10)
		{
			//System.out.println("Within 10 minutes!");	
		}

	}
}
