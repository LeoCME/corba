package dialogueApp;

import javafx.application.Platform;
import controller.ClientViewController;
import dialogue.Dialogue;

public class MsgGetter extends Thread {
	Dialogue dialogue;
	ClientViewController controller;

	public MsgGetter(Dialogue dialogue,
			controller.ClientViewController controller) {
		this.dialogue = dialogue;
		this.controller = controller;
	}

	@Override
	public void run() {
		super.run();
		String[] msg;
		while(true)
		{
		try {
			Thread.sleep(5000);
			msg=dialogue.getMessage();
			if (msg.length != 0){
				for(String s:msg){
					Platform.runLater(() ->	controller.receiveMessage(s));				
}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
}
