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
	private int previousResponseIndex;
	
	private int gameSubject;
	private int chatBotAsksQuestion;
	
	private String[] chatBotQuestions;
	
	private int badAnswerMeter;
	private int liarMeter;
	
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
		String[] prwords = {"yes","okay","fine","right","yeah"};
		positiveResponseWords = prwords;
		String[] nrwords = {"no","never"};
		negativeResponseWords = nrwords;
		
		responseIndex = 0;
		loopResponsesData = false;
		responseNumber = 0;
		
		gameSubject = -1;
		
		String[] questions = {"Singleplayer games? Which one?","What do you think of ___?", "Why do you think ____ is NEG?", "Why do you think ___ is POS?", "User is being a little","No subject given, so give me one."};
		chatBotQuestions = questions;
		
		badAnswerMeter = 0;
		liarMeter = 0;
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
			responseNumber ++;
			chatBotAsksQuestion = 0;
		}
		else if(gameSubject > -1 && responseNumber == 0)
		{
			ChatbotMain.print("I know " + specificGames[gameSubject] + " tell me more on what you think about it.");
			responseNumber++;
			chatBotAsksQuestion = 1;
		}
		
		chatting = true;
		
		while(chatting)
		{
			response = ChatbotMain.getInput();
			String printLine = "";
			recordResponse(response);
			
			int[] rData = analyzeResponse(response);
			
			if(previousResponseIndex==-1 && !loopResponsesData)
			{
				previousResponseIndex = 0;
			}
			ChatbotMain.print(rData[0] + "," + rData[1] + "," + rData[2]);
			ChatbotMain.print(responsesData[previousResponseIndex][0] + "," + responsesData[previousResponseIndex][1] + "," + responsesData[previousResponseIndex][2]);
			ChatbotMain.print(responseIndex + "," + previousResponseIndex + "," + chatBotAsksQuestion);
			
			//LOGIC SECTION
			if(rData[0] == -1)
			{
				printLine = respondToComment(response);
			}
			if(rData[0] == 0) //checks which question the user is responding to
			{
				printLine = respondToQuestion0(rData);
			}
			if(rData[0] == 1) //responding to what they think of said game
			{
				printLine = respondToQuestion1(rData);
			}
			if(rData[0] == 2) //why they find the game negative
			{
				
			}
			if(rData[0] == 3) //why they find the game positive
			{
				
			}
			if(rData[0] == 4) //user is not answering the questions appropriately
			{
				printLine = respondToQuestion4(response);
			}
			
			gameSubject = checkDoubleArray(response, specificGamesKeywords);
			if(!printLine.equals(""))
			{
				ChatbotMain.print(printLine);

				responseIndex ++;
				responseNumber ++;
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
	/*------------ MOST IMPORTANT METHOD -------------*/
	//check comment/answer, check subject, check positive/negative
	//Index
	/*  0     -1 - COMMENT, >-1 - ANSWER;
	 *  1     -1 - UNKOWN SUBJECT, >-1 - GAME SUBJECT;
	 *  2     0 - NONE, 1 - NEG, 2 - POS
	 *  3     0 - NONE, 1 - GAMEPLAY, 2 - STORY, 3 - ART*/
	public int[] analyzeResponse(String response)
	{
		int[] returnIntArray = {-1,-1,-1};
		
		int commentOrAnswer = -1; //records if user is responding to a chatbot question, and which type of question was asked
		if(chatBotAsksQuestion > -1)
		{
			commentOrAnswer = chatBotAsksQuestion;
		}
		
		int noSubjectOrGameSubject = -1; //checks if the user is still talking about a game or not
		if(checkArray(response, subjectWords) > -1 && gameSubject > -1)
		{
			noSubjectOrGameSubject = gameSubject;
		}
		else if(checkDoubleArray(response, specificGamesKeywords) > -1)
		{
			noSubjectOrGameSubject = checkDoubleArray(response, specificGamesKeywords);
		}
		int noneOrNegativeOrPositive = -1; //checks if what was said was positive or not
		if(checkArray(response, positiveWords) > -1)
		{
			noneOrNegativeOrPositive = 1;
		}
		if(checkArray(response, negativeWords) > -1)
		{
			noneOrNegativeOrPositive = 0;
		}
		
		returnIntArray[0] = commentOrAnswer;
		returnIntArray[1] = noSubjectOrGameSubject;
		returnIntArray[2] = noneOrNegativeOrPositive;
		return returnIntArray;
		
	}
	public void recordResponse(String response)
	{
		if(responseIndex > 9)
		{
			responseIndex = 0;
			loopResponsesData = true;
		}
		responsesData[responseIndex] = analyzeResponse(response);
		
		previousResponseIndex = responseIndex-1;
		if(previousResponseIndex == -1 && loopResponsesData)
		{
			previousResponseIndex = 9;
		}
	}
	public String respondToComment(String response)
	{
		return "";
	}
	public String respondToQuestion0(int[] rData)
	{
		if(rData[1] > -1 && rData[2] == -1) //user gives a game chatbot can talk about and doesn't give an opinion
		{
			chatBotAsksQuestion = 1;
			return "What did you think of " + specificGames[rData[1]] + "?";
		}
		else if(rData[1] > -1 && rData[2] > -1) //user  gives a game and an opinion
		{
			return respondToQuestion1(rData);
		}
		else //user doesn't answer the question
		{
			badAnswerMeter ++;
			chatBotAsksQuestion = -1;
			return "Then why'd you talk about it first...";
		}
	}
	public String respondToQuestion1(int[] rData)
	{
		String[] rQNegativeBeginning = {"Is there a reason you would think ", "Why do you think "};
		String[] rQNegativeEnding = {" is a bad game.", " is bad?"};
		if(rData[1] == responsesData[previousResponseIndex][1]) //user doesn't end up talking about another game
		{
			if(rData[2] > -1) //user gives an opinion chatbot can understand
			{
				if(rData[2] == 0) //user says something negative about the game
				{
					chatBotAsksQuestion = 2;
					int randomQuestion = randomInteger(0,rQNegativeBeginning.length);
					return rQNegativeBeginning[randomQuestion] + specificGames[gameSubject] + rQNegativeEnding[randomQuestion];
				}
				else if(rData[2] == 1)//user says something positive about the game
				{
					chatBotAsksQuestion = 3;
					return "Why do you think " + specificGames[gameSubject] + " is good?";
				}
			}
			else //user doesn't give an opinion, will see if user wants to answer the question or not
			{
				chatBotAsksQuestion = 4;
				badAnswerMeter ++;
				return "How about you answer the question.";
			}
		}
		if(rData[1] > -1) //user talks about a different game
		{
			chatBotAsksQuestion = -1;
			return "We were talking about " + specificGames[responsesData[previousResponseIndex][1]]+".";
		}
		return "Sieg heil";
	}
	public String respondToQuestion2(int[] rData)
	{
		return "NEIN MEIN FUHRER";
	}
	public String respondToQuestion3(int[] rData)
	{
		return "JA MEIN FUHRER";
	}
	public String respondToQuestion4(String response)
	{
		if(checkArray(response, positiveResponseWords) > -1)
		{
			//check which question they want to re-answer and asks them it
			int indexofLastQuestionResponse = lookForLastQuestion();
			if(responsesData[indexofLastQuestionResponse][0] == 1)
			{
				return respondToQuestion1(responsesData[responseIndex-1]);
			}
		}
		if (checkArray(response, negativeResponseWords) > -1)
		{
			chatBotAsksQuestion = -1;
			return "Alright, so let's talk about something else";
		}
		//user doesn't give a proper answer
		return "";
	}
	public int lookForLastQuestion() //goes through responsesData and looks for the last question asked and gives the index of the response
	{
		int a = 0;
		if(loopResponsesData)
		{
			a = responseIndex+1;
			if(a>9)
			{
				a = 0;
			}
		}
		for(int i = responseIndex; i != a; i--)
		{
			if(i == -1)
			{
				i = 9;
			}
			if(responsesData[i][0]!=-1)
			{
				return i;
			}
		}
		return -1;
	}
	public static int randomInteger(int low, int high)
	{
		int randomvalue = -1;
		if(low>high)
		{
			return -1;
		}
		high -= (low-1);
		randomvalue = (int) (Math.random() * high + low);
		return randomvalue;
	}
}