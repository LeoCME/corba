package dialogue;


/**
* dialogue/_DialogueStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /Users/leocorone/Documents/workspace/eclipse_luna/corba pull avance/src/dialogue.idl
* mardi 4 novembre 2014 11 h 53 CET
*/

public class _DialogueStub extends org.omg.CORBA.portable.ObjectImpl implements dialogue.Dialogue
{

  public String[] getClients ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getClients", true);
                $in = _invoke ($out);
                String $result[] = dialogue.clientsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getClients (        );
            } finally {
                _releaseReply ($in);
            }
  } // getClients

  public void sendMessage (String to, String message) throws dialogue.notExistingPseudo
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendMessage", true);
                $out.write_string (to);
                $out.write_string (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:dialogue/notExistingPseudo:1.0"))
                    throw dialogue.notExistingPseudoHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sendMessage (to, message        );
            } finally {
                _releaseReply ($in);
            }
  } // sendMessage

  public String[] getMessage ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMessage", true);
                $in = _invoke ($out);
                String $result[] = dialogue.messagesHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMessage (        );
            } finally {
                _releaseReply ($in);
            }
  } // getMessage

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:dialogue/Dialogue:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _DialogueStub
