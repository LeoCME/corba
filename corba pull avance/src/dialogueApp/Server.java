package dialogueApp;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import dialogue.Connection;



public class Server {

 public static void main(String args[])
{
	

java.util.Properties props = System.getProperties();


int status = 0;


ORB orb = null;

try
{


orb = ORB.init(args, props);
run(orb);
}
catch(Exception ex)
{
ex.printStackTrace();
status = 1;
}

if(orb != null)
{
try
{
orb.destroy();
}
catch(Exception ex)
{
ex.printStackTrace();
status = 1;
}
}

System.exit(status);
}


static int run(ORB orb) throws Exception
{
	org.omg.CORBA.Object obj;
org.omg.PortableServer.POA rootPOA =
org.omg.PortableServer.POAHelper.narrow(
orb.resolve_initial_references("RootPOA"));

org.omg.PortableServer.POAManager manager = rootPOA.the_POAManager();

Connection_impl connectionImpl = new Connection_impl();
Connection connection = connectionImpl._this(orb);

obj=orb.resolve_initial_references("NameService");

NamingContext ctx = NamingContextHelper.narrow(obj);

if (ctx==null)
{
	System.out.println("Le composant NameService n'est pas un repertoire");
	return 0;
}

NameComponent[] name = new NameComponent[1];

name[0]=new NameComponent("Connection","");

ctx.rebind(name,connection);


/*
String ref = orb.object_to_string(hello);
String refFile = "Hello.ref";
java.io.PrintWriter out = new java.io.PrintWriter(
new java.io.FileOutputStream(refFile));
out.println(ref);
out.close();*/


System.out.println("Objet cree et reference");


manager.activate();
orb.run();

return 0;
}
}
