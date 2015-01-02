package dialogueApp;

import java.util.Scanner;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.ORBPackage.InvalidName;

import dialogue.Dialogue;
import dialogue.DialogueHelper;
import dialogue.DialoguePackage.notExistingPseudo;

final class ClientDialogue {

	public static void main(String args[]) {

		java.util.Properties props = System.getProperties();

		int status = 0;
		org.omg.CORBA.ORB orb = null;

		try {
			orb = ORB.init(args, props);
			run(orb);
		} catch (Exception ex) {
			ex.printStackTrace();
			status = 1;
		}

		if (orb != null) {
			try {
				orb.destroy();
			} catch (Exception ex) {
				ex.printStackTrace();
				status = 1;
			}
		}

	//	System.exit(status);
	}

	static void run(ORB orb)
{
    org.omg.CORBA.Object obj = null;
	
	try
	{
	
	obj=orb.resolve_initial_references("NameService");
	}
	catch(InvalidName e)
	{
		e.printStackTrace();
		System.exit(1);
	}
	
	
	NamingContext ctx = NamingContextHelper.narrow(obj);
	
	
	if (ctx==null)
	{
		System.out.println("Le composant NameService n'est pas un repertoire");
		System.exit(1);
	}
	
	NameComponent[] name = new NameComponent[1];
	
	name[0]=new NameComponent("Dialogue","");
	
	try
	{
	obj = ctx.resolve(name);
	}
	catch (Exception e)
	{
		System.out.println("Composant inconnu");
		e.printStackTrace();
		System.exit(1);
	}
	
  /*
    String refFile = "Hello.ref";
    java.io.BufferedReader in = new java.io.BufferedReader(
    new java.io.FileReader(refFile));
    String ref = in.readLine();
    System.out.println("IOR :"+ref);
    obj = orb.string_to_object(ref);
    //obj = orb.string_to_object("relfile:/Hello.ref");*/
	
String pseudo = "";
	

Dialogue dialogue = DialogueHelper.narrow(obj);


Scanner cs = new Scanner(System.in);
System.out.println("Veuillez saisir votre pseudo :");
pseudo = cs.nextLine();

dialogue.connect(pseudo);
System.out.println("liste des clients");
String[] listClients = dialogue.getClients();

for (int i =0; i < listClients.length;i++){
	System.out.println(listClients[i]);
}
System.out.println("Entrer un destinataire");
String destinataire = cs.nextLine();
System.out.println("Entrer votre message");
String message = cs.nextLine();
try {
	dialogue.sendMessage(pseudo, destinataire, message);
} catch (notExistingPseudo e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

//System.out.println(dialogue.getMessage(pseudo));
cs.close();

MsgGetter mg = new MsgGetter(dialogue,pseudo);
mg.start();
while(true){

}

}
}
