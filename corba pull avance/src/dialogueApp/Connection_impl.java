package dialogueApp;

import java.util.ArrayList;
import java.util.HashMap;

import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import dialogue.Connection;
import dialogue.ConnectionPOA;
import dialogue.Dialogue;
import dialogue.DialogueHelper;
import dialogue.DialoguePackage.notExistingPseudo;

public class Connection_impl extends ConnectionPOA {
    HashMap<String,Dialogue_impl> mapDialogues = new HashMap<String,Dialogue_impl>();


	@Override
	public Dialogue connect(String pseudo) {
		
		Dialogue_impl mailbox = new Dialogue_impl(this,pseudo);
        Dialogue dialogue=null;
		try {
			dialogue = DialogueHelper.narrow(_default_POA().servant_to_reference(mailbox));
		} catch (ServantNotActive e) {
			e.printStackTrace();
		} catch (WrongPolicy e) {
			e.printStackTrace();
		}
		
        mapDialogues.put(pseudo, mailbox);
		return dialogue;
	}

	@Override
	public void disconnect(String pseudo) throws dialogue.notExistingPseudo {
		if(mapDialogues.containsKey(pseudo)){
			mapDialogues.remove(pseudo);
		}else{
			throw new dialogue.notExistingPseudo(pseudo);
		}
	}
	
	public HashMap<String, Dialogue_impl> getMapDialogues() {
		return mapDialogues;
	}
	
	public ArrayList<String> getListClients (){
		ArrayList<String> listClients = new ArrayList<String>();
		
		for ( String key : mapDialogues.keySet() ) {
			listClients.add(key);
		}
			return listClients;
		}
	}

