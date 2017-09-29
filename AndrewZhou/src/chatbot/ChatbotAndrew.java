package chatbot;
//
public class ChatbotAndrew implements Topic{

	private String[] keywords;
	private String[] goodbyeWords;
	private String secretWord;
	private boolean chatting;
	
	public ChatbotAndrew() {
		String[] temp = {"depth"};
		keywords = temp;
		String[] temp2 = {"bye"};
		goodbyeWords = temp2;
		secretWord = "out";
	}

	@Override
	public boolean isTriggered(String response) {
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i],0) >= 0) {
				return true;
			}
		}
		return false;
	}

	public void startChatting(String response) {
		ChatbotMain.print("Hey! It sounds like you and I have a common interest! Let's talk some more!");
		chatting = true;
		while(chatting) {
			response = ChatbotMain.getInput();
			if(ChatbotMain.findKeyword(response, goodbyeWords[0], 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}else if(ChatbotMain.findKeyword(response,  secretWord,  0) >= 0) {
				ChatbotMain.print("Sieg to you too. Go to Brazil for the meet up");
			}else {
				ChatbotMain.print("Huh, you are a heathen, I'll give you another chance.");
			}
			
		}
	}
}
