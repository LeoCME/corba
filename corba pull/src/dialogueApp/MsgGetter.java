package dialogueApp;

import dialogue.Dialogue;

public class MsgGetter extends Thread {
Dialogue dialogue;
String pseudo;
	public MsgGetter(Dialogue dialogue, String pseudo) {
		this.dialogue = dialogue;
		this.pseudo = pseudo;
	}

	@Override
	public void run() {
		super.run();
		String msg;
		while(true)
		{
		try {
			Thread.sleep(5000);
			msg=dialogue.getMessage(pseudo);
			if (!msg.equals("")){
			System.out.println(msg);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}

}
