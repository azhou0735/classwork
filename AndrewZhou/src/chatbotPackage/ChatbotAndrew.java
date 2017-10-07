package chatbotPackage;

import java.util.ArrayList;

public class ChatbotAndrew implements Topic{
	
	private boolean chatting;
	
	private String[] keywords;
	private String[] specificGames;
	private String[][] specificGamesKeywords;
	
	private int[][] responsesData;
	
	private String[] subjectWords; //vocab arrays
	private String[] positiveWords;
	private String[] negativeWords;
	private String[] positiveResponseWords;
	private String[] negativeResponseWords;
	
	private int responseIndex;
	private boolean loopResponsesData;
	private int responseNumber;
	
	private int gameSubject;
	private boolean chatBotAskedQuestion;
	
	public ChatbotAndrew()
	{
		String[] kwords = {"single", "solo", "sp", "single player", "singleplayer", "pve"};
		keywords = kwords;
		String[] sgames = {"Metro", "Grand Theft Auto", "Dishonored", "Portal", "Payday", "Skyrim", "Fallout", "Minecraft"};
		specificGames = sgames;
		
		String[] metro = {"2033", "last light", "redux", "metro"};
		String[] gta = {"grand theft", "theft auto", "gta"};
		String[] dishonored = {"dishonored"};
		String[] portal = {"portal"};
		String[] payday = {"payday", "payday: the heist", "payday 2", "payday: the", "the heist"};
		String[] skyrim = {"elder scroll", "skyrim"};
		String[] fallout = {"fallout"};
		String[] minecraft = {"minecraft", "mine"};
		String[][] sgameskwords = {metro, gta, dishonored, portal, payday, skyrim, fallout, minecraft};
		specificGamesKeywords = sgameskwords;
		
		int[] temp1 = {};int[] temp2 = {};int[] temp3 = {};int[] temp4 = {};int[] temp5 = {};
		int[] temp6 = {};int[] temp7 = {};int[] temp8 = {};int[] temp9 = {};int[] temp10 = {};
		int[][] rdata = {temp1,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9,temp10};
		responsesData = rdata;
		
		String[] swords = {"it","the game", "the gameplay", "the art", "the story", "its", "it's"};
		subjectWords = swords;
		String[] pwords = {"good", "well made", "developed", "great", "fun"};
		positiveWords = pwords;
		String[] nwords = {"bad", "buggy", "boring", "ugly"};
		negativeWords = nwords;
		String[] prwords = {"yes","okay","fine","right"};
		positiveResponseWords = prwords;
		String[] nrwords = {"no","never"};
		negativeResponseWords = nrwords;
		
		responseIndex = 0;
		loopResponsesData = false;
		responseNumber = 0;
		
		gameSubject = -1;
	}
	
	public boolean isTriggered(String response) {
		if(checkArray(response, keywords) > -1)
		{
			return true;
		}
		if(checkDoubleArray(response, specificGamesKeywords) > -1)
		{
			gameSubject = checkDoubleArray(response, specificGamesKeywords);
			return true;
		}
		return false;
	}

	public void startChatting(String response) {
		if(gameSubject == -1 && responseNumber == 0)
		{
			ChatbotMain.print("Singleplayer games? Which one?");
			chatBotAskedQuestion = true;
		}
		else if(gameSubject > -1 && responseNumber == 0)
		{
			ChatbotMain.print("I know " + specificGames[gameSubject] + " tell me more on what you think about it.");
		}
		
		chatting = true;
		
		while(chatting)
		{
			String printLine = "";
			
			//LOGIC SECTION
			if(gameSubject > -1)
			{
				printLine = "I like that game too.";
			}
			responsesData[responseIndex] = analyzeResponse(response);
			gameSubject = checkDoubleArray(response, specificGamesKeywords);
			if(!printLine.equals(""))
			{
				responseIndex ++;
				responseNumber ++;
				
				if(responseIndex > 9)
				{
					responseIndex = 0;
					loopResponsesData = true;
				}
				
				ChatbotMain.print(printLine);
			}
			else
			{
				chatting = false;
				ChatbotMain.chatbot.continueTalking(response);
			}
		}
		
	}
	//returns index of keyword in array
	public static int checkArray(String response, String[] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			if(ChatbotMain.findKeyword(response, array[i], 0) >= 0)
				return ChatbotMain.findKeyword(response,  array[i],  0);
		}
		return -1;
	}
	//returns array index with word
	public static int checkDoubleArray(String response, String[][] doubleArray)
	{
		for(int i = 0; i < doubleArray.length; i++)
		{
			if(checkArray(response, doubleArray[i]) >= 0)
				return i;
		}
		return -1;
	}
	//check comment/answer, check subject, check positive/negative,
	public int[] analyzeResponse(String response)
	{
		int[] returnIntArray = {1,-1,-1};
		
		int answerOrComment = 1;
		if(chatBotAskedQuestion)
		{
			answerOrComment = 0;
		}
		
		int noSubjectOrGameSubject = -1;
		if(checkArray(response, subjectWords) > -1 && gameSubject > -1)
		{
			noSubjectOrGameSubject = gameSubject;
		}
		
		int noneOrNegativeOrPositive = -1;
		if(checkArray(response, positiveWords) > -1)
		{
			noneOrNegativeOrPositive = 1;
		}
		if(checkArray(response, negativeWords) > -1)
		{
			noneOrNegativeOrPositive = 0;
		}
		
		returnIntArray[0] = answerOrComment;
		returnIntArray[1] = noSubjectOrGameSubject;
		returnIntArray[2] = noneOrNegativeOrPositive;
		return returnIntArray;
		
	}
	
}