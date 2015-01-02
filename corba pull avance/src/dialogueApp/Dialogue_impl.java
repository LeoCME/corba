package dialogueApp;

import java.util.ArrayList;

import dialogue.DialoguePOA;

public class Dialogue_impl extends DialoguePOA {
	private Connection_impl connection;
	private ArrayList<String> messagesStack = new ArrayList<String>() ;
	private String pseudo;



	public Dialogue_impl(Connection_impl connection, String pseudo) {
		super();
		this.pseudo = pseudo;
		this.connection = connection;
	}

	@Override
	public String[] getClients() {
		ArrayList<String> listClients = connection.getListClients();
		String[] tableauClients = listClients.toArray(new String[listClients.size()]);
		return tableauClients;
	}

	@Override
	public void sendMessage(String to, String message) throws dialogue.notExistingPseudo {
		ArrayList<String> listClients = connection.getListClients();
		if ( listClients.contains(to)){
			Dialogue_impl receiver = connection.getMapDialogues().get(to);
			receiver.receiveMessage(pseudo+": "+message);
		 }else{
		 throw new dialogue.notExistingPseudo ("this pseudo is not valid: "+ to);
		
		 }
	}

	@Override
	public String[] getMessage() {
		String[] messages = {};
		if (!messagesStack.isEmpty()){
		messages = messagesStack.toArray(new String[messagesStack.size()]);
		messagesStack.clear();
		}
		return messages;

	}
	public void receiveMessage(String messageAgregate){
		messagesStack.add(messageAgregate);
	}
}
