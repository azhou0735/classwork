package chatbotPackage;

import java.util.ArrayList;

public class ChatbotAndrew implements Topic{
	//
	private String[] keywords; //words that trigger my chatbot to take up a conversation
	private String[] specificGames; //the actual games the chatbot is able to chat about
	private String[][] masterArray; //used to find other keywords that refer to things in specificGames
	
	//create strings that keep stats of the coinciding indexed games in specificgames such as rating of art, gameplay, story, etc.
	//will be changed based of response of user
	/*private String[] storyRatings;
	private String[] gameplayRatings;
	private String[] artRatings;*/ //commented to be used as an expansion of overallRatings
	
	private String[] overallRatings;//string array that keeps track of user ratings of said games (not sure of)
	
	private boolean chatting;
	
	private String[] responsesArray;//string array that keeps the actual responses (not really necessary)
	private String[] responsesData;//string array that keeps the data of the responses said
	
	private String[] subjectWords;//list of words to determine if the user is still talking about a game
	private String[] positiveWords;//list of positive words
	private String[] negativeWords;//list of negative words
	
	private boolean askedQuestion;//records if my chatbot asked a question in chabot's previous response
	
	private boolean loopingResponse; //will tell if the user exceeded 10 responses and the string response array has to start from index 0
	private int responseIndex; //will have to loop between 0-9 since String[] for responses have only 10 indexes
	private int lastResponseIndex;
	private int responseNumber; //will just record how many responses that have been made
	private int currentGame; //int of the game that is currently the topic of the conversation
	
	private String printLine;
	
	public ChatbotAndrew() {
		String[] temp = {"single", "solo", "sp", "single player", "singleplayer", "pve",};
		keywords = temp;
		
		/*EACH GAME GETS THEIR OWN INDEX metro - 0, gta - 1, dishonored - 2, etc..*/
		String[] temp2 = {"Metro", "Grand Theft Auto", "Dishonored", "Portal", "Payday", "Skyrim", "Fallout", "Minecraft"};
		specificGames = temp2;
		
		//words that tell the chatbot that they are talking about one of the specific games
		String[] metro = {"2033", "last light", "redux", "metro"};
		String[] gta = {"grand theft", "theft auto", "gta"};
		String[] dishonored = {"dishonored"};
		String[] portal = {"portal"};
		String[] payday = {"payday", "payday: the heist", "payday 2", "payday: the", "the heist"};
		String[] skyrim = {"elder scroll", "skyrim"};
		String[] fallout = {"fallout"};
		String[] minecraft = {"minecraft", "mine"};
		String[][] temp5 = {metro, gta, dishonored, portal, payday, skyrim, fallout, minecraft};
		masterArray = temp5;
		
		/*String[] sRatings = {"5","5","5","5","5","5","5","5"};
		String[] gRatings = {"5","5","5","5","5","5","5","5"};
		String[] aRatings = {"5","5","5","5","5","5","5","5"};*/
		String[] oRatings = {"5","5","5","5","5","5","5","5"};
		/*storyRatings = sRatings;
		gameplayRatings = gRatings;
		artRatings = aRatings;*/
		overallRatings = oRatings;
		
		loopingResponse = false;
		responseIndex = 0;
		responseNumber = 0;
		currentGame = -1; //will be heavily connected to the masterArray
		
		String[] temp3 = {"","","","","","","","","",""};
		responsesArray = temp3;
		String[] temp4 = {"","","","","","","","","",""};
		responsesData = temp4;
		
		String[] subject = {"it","the game", "the gameplay", "the art", "the story", "its", "it's"}; //right now I am assuming the secondary subject is OVERALL so art/gameplay/story doesn't influence response YET
		subjectWords = subject;
		String[] positive = {"good", "well made", "developed","great", "fun"};
		positiveWords = positive;
		String[] negative = {"bad", "buggy", "boring", "ugly"};
		negativeWords = negative;
		
		askedQuestion = false;
		}

	public boolean isTriggered(String response) {
		for(int i = 0; i < keywords.length; i++)
		{
			if(ChatbotMain.findKeyword(response, keywords[i],0) >= 0)
			{
				return true;
			}
		}
		/*if(checkAllKeywordArrays(response, masterArray) >= 0)
		{
			responseNumber ++;
			return true;
		}*/
		return false;
	}
	
	public void startChatting(String response) {
		ChatbotMain.print("Singleplayer games? Which one?");
		askedQuestion = true;
		chatting = true;
		
		while(chatting)
		{
			currentGame = checkAllKeywordArrays(response, masterArray);
			
			response = ChatbotMain.getInput(); //response stuff
			recordResponse(response);
			lastResponseIndex = responseIndex - 1;
			if(responseIndex - 1 == -1)
			{
				lastResponseIndex = 9;
			}

			printLine = "";
			
			//int a = checkAllKeywordArrays(response, masterArray); //checks response to see which game the user is talking about <-- Already recorded in recordResponse
			if(responseNumber == 0)
			{
				if(Integer.parseInt(responsesData[responseIndex].substring(0,1)) >= 0) 
				{
					currentGame = Integer.parseInt(responsesData[responseIndex].substring(0,1));
					printLine = firstResponse(response, currentGame);
				}
			}
			else if(responsesData[responseIndex].substring(0,1).equals("-1"))
			{
				printLine = "What are you talking about?";
			}
			else if(analyzeResponse(response).substring(1,2).equals("1"))
			{
				printLine = "Why do you think " + specificGames[Integer.parseInt(analyzeResponse(response).substring(0, 1))] + " is a good game?";
			}
			else if(responsesData[responseIndex].substring(1,2).equals("2"))
			{
				printLine = "I don't think so.";
			}
			else if(!responsesData[responseIndex].substring(0,1).equals(responsesData[lastResponseIndex].substring(0,1)))
			{
				printLine = "We were talking about " + specificGames[Integer.parseInt(responsesData[lastResponseIndex].substring(0, 1))];
			}
			
			if(!printLine.equals(""))
			{
				responseNumber ++;
				responseIndex ++;
				if(responseIndex > 9)
				{
					responseIndex = 0;
					loopingResponse = true;
				}
				
				ChatbotMain.print(printLine);
			}
			else
			{
				chatting = false;
				ChatbotMain.chatbot.continueTalking(response);
			}//
		}
	}
	
	//cycles through a keyword array and returns the index of the specific keyword within that array
	public static int checkKeywordArray(String response, String[] keywordsArray)
	{
		for(int i = 0; i < keywordsArray.length; i++)
		{
			if(ChatbotMain.findKeyword(response, keywordsArray[i], 0) >= 0) 
			{
				return ChatbotMain.findKeyword(response, keywordsArray[i], 0);
			}
		}
		return -1;
	}
	//returns index of array where keyword was found <- only for identifying the specific game
	public static int checkAllKeywordArrays(String response, String[][] largeArray)
	{
		for(int i = 0; i < largeArray.length; i++)
		{
			if(checkKeywordArray(response, largeArray[i]) >= 0)
			{
				return i;
			}
		}
		return -1;
	}
	/*will record user response, categorize said response based off:
	 * if user is talking/ in a conversation about a specificGame
	 * if user said something positive or negative about said game
	 * not done - categorize said opinion under art, gameplay, and story
	 * ***CANT RECORD PAST 9 RESPONSES, RELOOP AND REMOVE OLDEST TO ADD NEWEST
	 */
	public void recordResponse(String response)
	{
		responsesArray[responseIndex] = response;
		responsesData[responseIndex] = analyzeResponse(response);
	}
	//data will be split into:
	//NOSUBJECT/GAME(substring(0,1)) NONE/POS/NEG(substring(1,2)) NONE/OVERALL/STORY/GAMEPLAY/ART(substring(2,3)) COMMENT/ANSWER(substring(3,4))
	public String analyzeResponse(String response)
	{
		// -1 - NOSUBJECT, 0 thru 7 - GAME(their assigned index)
		int noSubectOrCurrentGame = -1;
		if(checkKeywordArray(response, subjectWords) >= 0 && currentGame > 0)
		{
			noSubectOrCurrentGame = currentGame;
		}
		else if (checkAllKeywordArrays(response, masterArray) >= 0)
		{
			noSubectOrCurrentGame = checkAllKeywordArrays(response, masterArray);
		}
		// 0 - NONE, 1 - POSITIVE, 2 - NEGATIVE
		int noneOrPosOrNeg;
		if(checkKeywordArray(response, negativeWords) >= 0)
		{
			noneOrPosOrNeg = 2;
		}
		else if(checkKeywordArray(response, positiveWords) >= 0)
		{
			noneOrPosOrNeg = 1;
		}
		else
		{
			noneOrPosOrNeg = 0;
		}
		/*for now assume overall, more complicated version will check for words related to gameplay, art, and story <- for that
		the checkKeyWordArray(response, subjectWords) would be used as that has more specific subjects*/
		// 1 - STORY, 2 - GAMEPLAY, 3 - ART, 0 - OVERALL, -1 - NONE
		int partOfGameDiscussed = 0;
		//checks if the user is responding to a question asked by the chatbot
		int commentOrAnswer = 0;
		if(askedQuestion)
		{
			commentOrAnswer = 1;
		}
		return "" + noSubectOrCurrentGame + noneOrPosOrNeg + partOfGameDiscussed + commentOrAnswer;
	}
	//method to check previous responses and find any similarities to the current response <- MOST IMPORTANT
	public String checkPreviousResponses(String response)
	{	
		if(!loopingResponse) //do I need this?
		{
			for(int i = 0; i < responseIndex; i++)
			{
				if(analyzeResponse(response).equals(responsesData[i]))
				{
					return "We already talked about that";
				}
			}
		}
		else
		{
			for(int i = responseIndex; i == lastResponseIndex; i++)
			{
				if(i == 10)
				{
					i = 0;
				}
				if(analyzeResponse(response).equals(responsesData[i])) 
				{
					return "We already talked about that";
				}
			}
		}
		return "";
	}
	
	/* --------------------- METHODS THAT ARE USED TO CREATE A RESPONSE TO USER WHEN A CERTAIN DATA CONDITIONS(LOGIC) OF USER RESPONSES ARE MET -----------------------*/
	
	public String firstResponse(String response, int gameIndex) //response to when they answer about what single player game
	{
		if(gameIndex == 0)
		{
			return "I also like metro.";
		}
		return "";
	}
}
