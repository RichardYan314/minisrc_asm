public class Word
{
    private int word = 0;

    public void op(int op)
    {
        this.word |= op << 27;
    }

    public void ra(int ra)
    {
        this.word |= ra << 23;
    }

    public void rb(int rb)
    {
        this.word |= rb << 19;
    }

    public void rc(int rc)
    {
        this.word |= rc << 15;
    }

    public void c2(int c2)
    {
        this.word |= c2 << 19;
    }

    public void imm(int c)
    {
        this.word |= c & 0x7FFFF;
    }

    public int get()
    {
        return this.word;
    }
}