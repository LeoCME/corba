package dialogue;

import java.util.ArrayList;
import java.util.HashMap;

import dialogue.DialoguePackage.notExistingPseudo;

public class Dialogue_impl extends DialoguePOA {
    ArrayList<String> listMessages = new ArrayList<String>();
    ArrayList<String> listClients = new ArrayList<String>();
    HashMap<String,ArrayList<String>> mapMessages = new HashMap<String,ArrayList<String>>();

    @Override
	public void connect(String pseudo) {
		listClients.add(pseudo);
		mapMessages.put(pseudo, new ArrayList<String>());
	}

	@Override
	public void disconnect(String pseudo) {
		int i = 0;
		while(!pseudo.equals(listClients.get(i))){
		i++;
		}
		listClients.remove(i);
	}

	@Override
	public String[] getClients() {
		String[] tableauClients = listClients.toArray(new String[listClients.size()]);
		return tableauClients;
	}

	@Override
	public void sendMessage(String from, String to, String message) throws notExistingPseudo {
//		listMessages.add(message);
		ArrayList<String> messagesFromTo = mapMessages.get(to);
		if (messagesFromTo != null){
			messagesFromTo.add(message);
			mapMessages.put(to, messagesFromTo);
		}else{
			throw new notExistingPseudo ("this pseudo is not valid: "+ to);

		}
		
		
}


	@Override
	public String getMessage(String pseudo) {
//	    return listMessages.get(listMessages.size()-1);
		ArrayList<String> listMessages = mapMessages.get(pseudo);
		String retours="";
		for (String string : listMessages) {
			retours = retours+ string+"\n";
		}
		mapMessages.get(pseudo).clear();
		return retours;
	}

}
