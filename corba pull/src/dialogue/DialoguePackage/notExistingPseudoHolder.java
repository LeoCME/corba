package dialogue.DialoguePackage;

/**
* dialogue/DialoguePackage/notExistingPseudoHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /Users/leocorone/Documents/workspace/eclipse_luna/coba/src/dialogue.idl
* mercredi 15 octobre 2014 12 h 08 CEST
*/

public final class notExistingPseudoHolder implements org.omg.CORBA.portable.Streamable
{
  public dialogue.DialoguePackage.notExistingPseudo value = null;

  public notExistingPseudoHolder ()
  {
  }

  public notExistingPseudoHolder (dialogue.DialoguePackage.notExistingPseudo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = dialogue.DialoguePackage.notExistingPseudoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    dialogue.DialoguePackage.notExistingPseudoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return dialogue.DialoguePackage.notExistingPseudoHelper.type ();
  }

}
