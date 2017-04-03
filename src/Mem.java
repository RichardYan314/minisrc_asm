import java.util.ArrayList;

public class Mem
{
  ArrayList<String> mem = null;
  int pointer = 0;
  
  public Mem()
  {
    this.mem = new ArrayList();
    this.pointer = 0;
  }
  
  public void advPtr(int ptr)
    throws FatalError
  {
    if (ptr < this.pointer) {
      throw new FatalError("Cannot go back in memory");
    }
    if (ptr > this.pointer)
    {
      String str = "";
      if (ptr > this.pointer + 1)
      {
        str = str + "[";
        str = str + String.format("%04x", new Object[] { Integer.valueOf(this.pointer) });
        str = str + "..";
        str = str + String.format("%04x", new Object[] { Integer.valueOf(ptr - 1) });
        str = str + "] : ";
      }
      else
      {
        str = str + String.format("%04x", new Object[] { Integer.valueOf(ptr) });
        str = str + " : ";
      }
      str = str + String.format("%08x", new Object[] { Integer.valueOf(0) });
      str = str + ";";
      this.mem.add(str);
      this.pointer = ptr;
    }
  }
  
  public void addWord(int word)
  {
    String str = "";
    str = str + String.format("%04x", new Object[] { Integer.valueOf(this.pointer++) });
    str = str + " : ";
    str = str + String.format("%08x", new Object[] { Integer.valueOf(word) });
    str = str + ";";
    this.mem.add(str);
  }
  
  public int getSize () {
	  return pointer;
  }
  
  public String toString()
  {
    String str = "";
    for (String i : this.mem) {
      str = str + i + "\n";
    }
    return str;
  }
}
