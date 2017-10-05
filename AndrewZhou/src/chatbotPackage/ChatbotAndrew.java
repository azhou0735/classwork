package chatbotPackage;

import java.util.ArrayList;

public class ChatbotAndrew implements Topic{

	
	public ChatbotAndrew() {
	}

	public boolean isTriggered(String response)
	{
	}
	
	public void startChatting(String response) 
	{
		ChatbotMain.chatbot.continueTalking(response);
	}

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
	 * categorize said opinion under art, gameplay, and story
	 * ***CANT RECORD PAST 9 RESPONSES, RELOOP AND REMOVE OLDEST TO ADD NEWEST
	 */
	public void recordResponse(String response)
	{
	}
	
	/* --------------------- METHODS THAT ARE USED TO CREATE A RESPONSE TO USER WHEN A CERTAIN DATA CONDITIONS OF USER RESPONSES ARE MET -----------------------*/
	
	public static String firstResponse(String response, String[][] largeArray)
	{
	}
}
