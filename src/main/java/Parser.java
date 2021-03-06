

import java.util.ArrayList;



public class Parser {
	public static final int _EOF = 0;
	public static final int _pNumber = 1;
	public static final int _pLabel = 2;
	public static final int _pLd = 3;
	public static final int _pLdi = 4;
	public static final int _pSt = 5;
	public static final int _pAdd = 6;
	public static final int _pSub = 7;
	public static final int _pAnd = 8;
	public static final int _pOr = 9;
	public static final int _pShr = 10;
	public static final int _pShra = 11;
	public static final int _pShl = 12;
	public static final int _pRor = 13;
	public static final int _pRol = 14;
	public static final int _pAddi = 15;
	public static final int _pAndi = 16;
	public static final int _pOri = 17;
	public static final int _pMul = 18;
	public static final int _pDiv = 19;
	public static final int _pNeg = 20;
	public static final int _pNot = 21;
	public static final int _pBrzr = 22;
	public static final int _pBrnz = 23;
	public static final int _pBrpl = 24;
	public static final int _pBrmi = 25;
	public static final int _pJr = 26;
	public static final int _pJal = 27;
	public static final int _pIn = 28;
	public static final int _pOut = 29;
	public static final int _pMfhi = 30;
	public static final int _pMflo = 31;
	public static final int _pNop = 32;
	public static final int _pHalt = 33;
	public static final int _pOrg = 34;
	public static final int _pWord = 35;
	public static final int _pByte = 36;
	public static final int _pSkip = 37;
	public static final int maxT = 43;

	static final boolean _T = true;
	static final boolean _x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	public static int intValue (String str) {
  return str.toLowerCase().startsWith("0b")? Integer.parseUnsignedInt(str.substring(2), 2) : Integer.decode(str);
}

Mem mem = new Mem();
boolean debug = false;



	public Parser(Scanner scanner) {
		this.scanner = scanner;
		errors = new Errors();
	}

	void SynErr (int n) {
		if (errDist >= minErrDist) errors.SynErr(la.line, la.col, n);
		errDist = 0;
	}

	public void SemErr (String msg) {
		if (errDist >= minErrDist) errors.SemErr(t.line, t.col, msg);
		errDist = 0;
	}
	
	void Get () {
		for (;;) {
			t = la;
			la = scanner.Scan();
			if (la.kind <= maxT) {
				++errDist;
				break;
			}

			la = t;
		}
	}
	
	void Expect (int n) {
		if (la.kind==n) Get(); else { SynErr(n); }
	}
	
	boolean StartOf (int s) {
		return set[s][la.kind];
	}
	
	void ExpectWeak (int n, int follow) {
		if (la.kind == n) Get();
		else {
			SynErr(n);
			while (!StartOf(follow)) Get();
		}
	}
	
	boolean WeakSeparator (int n, int syFol, int repFol) {
		int kind = la.kind;
		if (kind == n) { Get(); return true; }
		else if (StartOf(repFol)) return false;
		else {
			SynErr(n);
			while (!(set[syFol][kind] || set[repFol][kind] || set[0][kind])) {
				Get();
				kind = la.kind;
			}
			return StartOf(syFol);
		}
	}
	
	void Minisrc() {
		while (StartOf(1)) {
			if (la.kind == 34) {
				orgDirect();
			} else if (StartOf(2)) {
				Instr();
			} else {
				data();
			}
			if (la.kind == 38) {
				Get();
			}
		}
	}

	void orgDirect() {
		Expect(34);
		Expect(1);
		int addr = intValue(t.val); 
		mem.advPtr(addr); 
	}

	void Instr() {
		Word word = new Word(); 
		switch (la.kind) {
		case 3: {
			word = ldInstr();
			break;
		}
		case 4: {
			word = ldiInstr();
			break;
		}
		case 5: {
			word = stInstr();
			break;
		}
		case 6: {
			word = addInstr();
			break;
		}
		case 7: {
			word = subInstr();
			break;
		}
		case 8: {
			word = andInstr();
			break;
		}
		case 9: {
			word = orInstr();
			break;
		}
		case 10: {
			word = shrInstr();
			break;
		}
		case 11: {
			word = shraInstr();
			break;
		}
		case 12: {
			word = shlInstr();
			break;
		}
		case 13: {
			word = rorInstr();
			break;
		}
		case 14: {
			word = rolInstr();
			break;
		}
		case 15: {
			word = addiInstr();
			break;
		}
		case 16: {
			word = andiInstr();
			break;
		}
		case 17: {
			word = oriInstr();
			break;
		}
		case 18: {
			word = mulInstr();
			break;
		}
		case 19: {
			word = divInstr();
			break;
		}
		case 20: {
			word = negInstr();
			break;
		}
		case 21: {
			word = notInstr();
			break;
		}
		case 22: {
			word = brzrInstr();
			break;
		}
		case 23: {
			word = brnzInstr();
			break;
		}
		case 24: {
			word = brplInstr();
			break;
		}
		case 25: {
			word = brmiInstr();
			break;
		}
		case 26: {
			word = jrInstr();
			break;
		}
		case 27: {
			word = jalInstr();
			break;
		}
		case 28: {
			word = inInstr();
			break;
		}
		case 29: {
			word = outInstr();
			break;
		}
		case 30: {
			word = mfhiInstr();
			break;
		}
		case 31: {
			word = mfloInstr();
			break;
		}
		case 32: {
			word = nopInstr();
			break;
		}
		case 33: {
			word = haltInstr();
			break;
		}
		default: SynErr(44); break;
		}
		mem.addWord(word.get()); 
		if(debug) {
		System.out.println(String.format("%32s", Integer.toBinaryString(word.get())).replace(' ', '0')); 
		System.out.println("|   ||  ||  ||                 |"); 
		} 
	}

	void data() {
		if (la.kind == 35) {
			Word();
		} else if (la.kind == 36) {
			Byte();
		} else SynErr(45);
	}

	Word  ldInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(3);
		word.op(0b00000); 
		ra = reg();
		word.ra(ra); 
		Expect(39);
		Expect(1);
		int imm = intValue(t.val); 
		word.imm(imm); 
		if (la.kind == 40) {
			Get();
			rb = reg();
			word.rb(rb); 
			Expect(41);
		}
		return word;
	}

	Word  ldiInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(4);
		word.op(0b00001); 
		ra = reg();
		word.ra(ra); 
		Expect(39);
		Expect(1);
		int imm = intValue(t.val); 
		word.imm(imm); 
		if (la.kind == 40) {
			Get();
			rb = reg();
			word.rb(rb); 
			Expect(41);
		}
		return word;
	}

	Word  stInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(5);
		word.op(0b00010); 
		Expect(1);
		int imm = intValue(t.val); 
		word.imm(imm); 
		if (la.kind == 40) {
			Get();
			rb = reg();
			word.rb(rb); 
			Expect(41);
		}
		Expect(39);
		ra = reg();
		word.ra(ra); 
		return word;
	}

	Word  addInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(6);
		word.op(0b00011); 
		word = rType(word);
		return word;
	}

	Word  subInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(7);
		word.op(0b00100); 
		word = rType(word);
		return word;
	}

	Word  andInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(8);
		word.op(0b00101); 
		word = rType(word);
		return word;
	}

	Word  orInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(9);
		word.op(0b00110); 
		word = rType(word);
		return word;
	}

	Word  shrInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(10);
		word.op(0b00111); 
		word = rType(word);
		return word;
	}

	Word  shraInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(11);
		word.op(0b01000); 
		word = rType(word);
		return word;
	}

	Word  shlInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(12);
		word.op(0b01001); 
		word = rType(word);
		return word;
	}

	Word  rorInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(13);
		word.op(0b01010); 
		word = rType(word);
		return word;
	}

	Word  rolInstr() {
		Word  word;
		int ra, rb; 
		word = new Word(); 
		Expect(14);
		word.op(0b01011); 
		word = rType(word);
		return word;
	}

	Word  addiInstr() {
		Word  word;
		word = new Word(); 
		Expect(15);
		word.op(0b01100); 
		word = iType(word);
		return word;
	}

	Word  andiInstr() {
		Word  word;
		word = new Word(); 
		Expect(16);
		word.op(0b01101); 
		word = iType(word);
		return word;
	}

	Word  oriInstr() {
		Word  word;
		word = new Word(); 
		Expect(17);
		word.op(0b01110); 
		word = iType(word);
		return word;
	}

	Word  mulInstr() {
		Word  word;
		word = new Word(); 
		Expect(18);
		word.op(0b01111); 
		word = r2Type(word);
		return word;
	}

	Word  divInstr() {
		Word  word;
		word = new Word(); 
		Expect(19);
		word.op(0b10000); 
		word = r2Type(word);
		return word;
	}

	Word  negInstr() {
		Word  word;
		word = new Word(); 
		Expect(20);
		word.op(0b10001); 
		word = r2Type(word);
		return word;
	}

	Word  notInstr() {
		Word  word;
		word = new Word(); 
		Expect(21);
		word.op(0b10010); 
		word = r2Type(word);
		return word;
	}

	Word  brzrInstr() {
		Word  word;
		int ra; 
		word = new Word(); 
		Expect(22);
		word.op(0b10011); 
		word.c2(0b0000); 
		ra = reg();
		word.ra(ra); 
		Expect(39);
		Expect(1);
		int imm = intValue(t.val); 
		word.imm(imm); 
		return word;
	}

	Word  brnzInstr() {
		Word  word;
		int ra; 
		word = new Word(); 
		Expect(23);
		word.op(0b10011); 
		word.c2(0b0001); 
		ra = reg();
		word.ra(ra); 
		Expect(39);
		Expect(1);
		int imm = intValue(t.val); 
		word.imm(imm); 
		return word;
	}

	Word  brplInstr() {
		Word  word;
		int ra; 
		word = new Word(); 
		Expect(24);
		word.op(0b10011); 
		word.c2(0b0010); 
		ra = reg();
		word.ra(ra); 
		Expect(39);
		Expect(1);
		int imm = intValue(t.val); 
		word.imm(imm); 
		return word;
	}

	Word  brmiInstr() {
		Word  word;
		int ra; 
		word = new Word(); 
		Expect(25);
		word.op(0b10011); 
		word.c2(0b0011); 
		ra = reg();
		word.ra(ra); 
		Expect(39);
		Expect(1);
		int imm = intValue(t.val); 
		word.imm(imm); 
		return word;
	}

	Word  jrInstr() {
		Word  word;
		word = new Word(); 
		int ra = 0; 
		Expect(26);
		word.op(0b10100); 
		ra = reg();
		word.ra(ra); 
		return word;
	}

	Word  jalInstr() {
		Word  word;
		word = new Word(); 
		int ra = 0; 
		Expect(27);
		word.op(0b10101); 
		ra = reg();
		word.ra(ra); 
		return word;
	}

	Word  inInstr() {
		Word  word;
		word = new Word(); 
		int ra = 0; 
		Expect(28);
		word.op(0b10110); 
		ra = reg();
		word.ra(ra); 
		return word;
	}

	Word  outInstr() {
		Word  word;
		word = new Word(); 
		int ra = 0; 
		Expect(29);
		word.op(0b10111); 
		ra = reg();
		word.ra(ra); 
		return word;
	}

	Word  mfhiInstr() {
		Word  word;
		word = new Word(); 
		int ra = 0; 
		Expect(30);
		word.op(0b11000); 
		ra = reg();
		word.ra(ra); 
		return word;
	}

	Word  mfloInstr() {
		Word  word;
		word = new Word(); 
		int ra = 0; 
		Expect(31);
		word.op(0b11001); 
		ra = reg();
		word.ra(ra); 
		return word;
	}

	Word  nopInstr() {
		Word  word;
		word = new Word(); 
		Expect(32);
		word.op(0b11010); 
		return word;
	}

	Word  haltInstr() {
		Word  word;
		word = new Word(); 
		Expect(33);
		word.op(0b11011); 
		return word;
	}

	int  reg() {
		int  regIdent;
		if (la.kind == 42) {
			Get();
		} else if (la.kind == 42) {
			Get();
		} else SynErr(46);
		Expect(1);
		regIdent = intValue(t.val); 
		return regIdent;
	}

	Word  rType(Word wordIn) {
		Word  word;
		int ra = 0; 
		int rb = 0; 
		int rc = 0; 
		ra = reg();
		wordIn.ra(ra); 
		Expect(39);
		rb = reg();
		wordIn.rb(rb); 
		Expect(39);
		rc = reg();
		wordIn.rc(rc); 
		word = wordIn; 
		return word;
	}

	Word  iType(Word wordIn) {
		Word  word;
		int ra = 0; 
		int rb = 0; 
		ra = reg();
		wordIn.ra(ra); 
		Expect(39);
		rb = reg();
		wordIn.rb(rb); 
		Expect(39);
		Expect(1);
		int imm = intValue(t.val); 
		wordIn.imm(imm); 
		word = wordIn; 
		return word;
	}

	Word  r2Type(Word wordIn) {
		Word  word;
		int ra = 0; 
		int rb = 0; 
		ra = reg();
		wordIn.ra(ra); 
		Expect(39);
		rb = reg();
		wordIn.rb(rb); 
		word = wordIn; 
		return word;
	}

	void Word() {
		Expect(35);
		Expect(1);
		mem.addWord(intValue(t.val)); 
		while (la.kind == 1) {
			Get();
			mem.addWord(intValue(t.val)); 
		}
	}

	void Byte() {
		int word = 0; 
		Expect(36);
		int cnt = 0; 
		Expect(1);
		word |= (intValue(t.val) & 0xff) << (cnt++ * 8); 
		while (la.kind == 1) {
			Get();
			word |= (intValue(t.val) & 0xff) << (cnt * 8); 
			if (++cnt == 4) { 
			mem.addWord(word); 
			cnt = 0; 
			word = 0; 
			} 
		}
		mem.addWord(word); 
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		Minisrc();
		Expect(0);

	}

	private static final boolean[][] set = {
		{_T,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x},
		{_x,_x,_x,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_x,_x,_x, _x,_x,_x,_x, _x},
		{_x,_x,_x,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_T,_T, _T,_T,_x,_x, _x,_x,_x,_x, _x,_x,_x,_x, _x}

	};
} // end Parser


class Errors {
	public int count = 0;                                    // number of errors detected
	public java.io.PrintStream errorStream = System.out;     // error messages go to this stream
	public String errMsgFormat = "-- line {0} col {1}: {2}"; // 0=line, 1=column, 2=text
	
	protected void printMsg(int line, int column, String msg) {
		StringBuffer b = new StringBuffer(errMsgFormat);
		int pos = b.indexOf("{0}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, line); }
		pos = b.indexOf("{1}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, column); }
		pos = b.indexOf("{2}");
		if (pos >= 0) b.replace(pos, pos+3, msg);
		errorStream.println(b.toString());
	}
	
	public void SynErr (int line, int col, int n) {
		String s;
		switch (n) {
			case 0: s = "EOF expected"; break;
			case 1: s = "pNumber expected"; break;
			case 2: s = "pLabel expected"; break;
			case 3: s = "pLd expected"; break;
			case 4: s = "pLdi expected"; break;
			case 5: s = "pSt expected"; break;
			case 6: s = "pAdd expected"; break;
			case 7: s = "pSub expected"; break;
			case 8: s = "pAnd expected"; break;
			case 9: s = "pOr expected"; break;
			case 10: s = "pShr expected"; break;
			case 11: s = "pShra expected"; break;
			case 12: s = "pShl expected"; break;
			case 13: s = "pRor expected"; break;
			case 14: s = "pRol expected"; break;
			case 15: s = "pAddi expected"; break;
			case 16: s = "pAndi expected"; break;
			case 17: s = "pOri expected"; break;
			case 18: s = "pMul expected"; break;
			case 19: s = "pDiv expected"; break;
			case 20: s = "pNeg expected"; break;
			case 21: s = "pNot expected"; break;
			case 22: s = "pBrzr expected"; break;
			case 23: s = "pBrnz expected"; break;
			case 24: s = "pBrpl expected"; break;
			case 25: s = "pBrmi expected"; break;
			case 26: s = "pJr expected"; break;
			case 27: s = "pJal expected"; break;
			case 28: s = "pIn expected"; break;
			case 29: s = "pOut expected"; break;
			case 30: s = "pMfhi expected"; break;
			case 31: s = "pMflo expected"; break;
			case 32: s = "pNop expected"; break;
			case 33: s = "pHalt expected"; break;
			case 34: s = "pOrg expected"; break;
			case 35: s = "pWord expected"; break;
			case 36: s = "pByte expected"; break;
			case 37: s = "pSkip expected"; break;
			case 38: s = "\"\\n\" expected"; break;
			case 39: s = "\",\" expected"; break;
			case 40: s = "\"(\" expected"; break;
			case 41: s = "\")\" expected"; break;
			case 42: s = "\"r\" expected"; break;
			case 43: s = "??? expected"; break;
			case 44: s = "invalid Instr"; break;
			case 45: s = "invalid data"; break;
			case 46: s = "invalid reg"; break;
			default: s = "error " + n; break;
		}
		printMsg(line, col, s);
		count++;
	}

	public void SemErr (int line, int col, String s) {	
		printMsg(line, col, s);
		count++;
	}
	
	public void SemErr (String s) {
		errorStream.println(s);
		count++;
	}
	
	public void Warning (int line, int col, String s) {	
		printMsg(line, col, s);
	}
	
	public void Warning (String s) {
		errorStream.println(s);
	}
} // Errors


class FatalError extends RuntimeException {
	public static final long serialVersionUID = 1L;
	public FatalError(String s) { super(s); }
}
