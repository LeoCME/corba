package dialogue;


/**
* dialogue/DialoguePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /Users/leocorone/Documents/workspace/eclipse_luna/coba/src/dialogue.idl
* mercredi 15 octobre 2014 12 h 08 CEST
*/

public abstract class DialoguePOA extends org.omg.PortableServer.Servant
 implements dialogue.DialogueOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("connect", new java.lang.Integer (0));
    _methods.put ("disconnect", new java.lang.Integer (1));
    _methods.put ("getClients", new java.lang.Integer (2));
    _methods.put ("sendMessage", new java.lang.Integer (3));
    _methods.put ("getMessage", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // dialogue/Dialogue/connect
       {
         String pseudo = in.read_string ();
         this.connect (pseudo);
         out = $rh.createReply();
         break;
       }

       case 1:  // dialogue/Dialogue/disconnect
       {
         String pseudo = in.read_string ();
         this.disconnect (pseudo);
         out = $rh.createReply();
         break;
       }

       case 2:  // dialogue/Dialogue/getClients
       {
         String $result[] = null;
         $result = this.getClients ();
         out = $rh.createReply();
         dialogue.clientsHelper.write (out, $result);
         break;
       }

       case 3:  // dialogue/Dialogue/sendMessage
       {
         try {
           String from = in.read_string ();
           String to = in.read_string ();
           String message = in.read_string ();
           this.sendMessage (from, to, message);
           out = $rh.createReply();
         } catch (dialogue.DialoguePackage.notExistingPseudo $ex) {
           out = $rh.createExceptionReply ();
           dialogue.DialoguePackage.notExistingPseudoHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // dialogue/Dialogue/getMessage
       {
         String pseudo = in.read_string ();
         String $result = null;
         $result = this.getMessage (pseudo);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:dialogue/Dialogue:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Dialogue _this() 
  {
    return DialogueHelper.narrow(
    super._this_object());
  }

  public Dialogue _this(org.omg.CORBA.ORB orb) 
  {
    return DialogueHelper.narrow(
    super._this_object(orb));
  }


} // class DialoguePOA
