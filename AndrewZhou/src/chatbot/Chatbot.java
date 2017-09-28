package chatbot;

public class Chatbot {
	
	private String userName;
	private Topic andrew;
	private boolean chatting;
	
	public Chatbot() {
		andrew = new ChatbotAndrew();
		userName = "unknown user";
		chatting = true;
	}
	
	public void startTalking() {
		ChatbotMain.print("Welcome to our chatbot! What is your name?");
		userName = ChatbotMain.getInput();
		while(chatting) {
			ChatbotMain.print("What do you want to talk about?");
			String response = ChatbotMain.getInput();
			if(andrew.isTriggered(response)) {
				chatting = false;
				andrew.startChatting();
			}else {
				ChatbotMain.print("I'm sorry. I don't understand.");
			}
		}
	}
}
