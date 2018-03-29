import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test {
	@org.junit.Test
	public void testScan_01inst_01ld() throws UnsupportedEncodingException {
		// Initialize
		String sContent = "ld r2, 1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00000001000000000000000000000001", 2);
		// Test
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.ldInstr();
		parser.Expect(0);
		// Validation
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_02ld2() throws UnsupportedEncodingException {
		String sContent = "ld r2, 1(r3)";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00000001000110000000000000000001", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.ldInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_03ldi() throws UnsupportedEncodingException {
		String sContent = "ldi r15, 10";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00001111100000000000000000001010", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.ldiInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_04ldi2() throws UnsupportedEncodingException {
		String sContent = "ldi r15, 10(r14)";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00001111111100000000000000001010", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.ldiInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_05st() throws UnsupportedEncodingException {
		String sContent = "st 1, r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00010000100000000000000000000001", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.stInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_06st2() throws UnsupportedEncodingException {
		String sContent = "st 1(r3), r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00010000100110000000000000000001", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.stInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_07ldr() throws UnsupportedEncodingException {
		String sContent = "ldr r15, 10";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00011111100000000000000000001010", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.ldrInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_08str() throws UnsupportedEncodingException {
		String sContent = "str 10, r15";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00100111100000000000000000001010", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.strInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_09add() throws UnsupportedEncodingException {
		String sContent = "add r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00101000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.addInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_10sub() throws UnsupportedEncodingException {
		String sContent = "sub r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00110000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.subInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_11and() throws UnsupportedEncodingException {
		String sContent = "and r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00111000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.andInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_12or() throws UnsupportedEncodingException {
		String sContent = "or r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01000000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.orInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_13shr() throws UnsupportedEncodingException {
		String sContent = "shr r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01001000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.shrInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_14shl() throws UnsupportedEncodingException {
		String sContent = "shl r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01010000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.shlInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_15ror() throws UnsupportedEncodingException {
		String sContent = "ror r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01011000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.rorInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_16rol() throws UnsupportedEncodingException	{
		String sContent = "rol r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01100000100100011000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.rolInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_17addi() throws UnsupportedEncodingException {
		String sContent = "addi r1, r2, 0xff";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01101000100100000000000011111111", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.addiInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_18andi() throws UnsupportedEncodingException {
		String sContent = "andi r1, r2, 0xff";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01110000100100000000000011111111", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.andiInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
			}
			
	@org.junit.Test
	public void testScan_01inst_19ori()
		throws UnsupportedEncodingException
	{
		String sContent = "ori r1, r2, 0xff";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01111000100100000000000011111111", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.oriInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_20mul() throws UnsupportedEncodingException {
		String sContent = "mul r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10000000100100000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.mulInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_21div() throws UnsupportedEncodingException {
		String sContent = "div r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10001000100100000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.divInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_22neg() throws UnsupportedEncodingException {
		String sContent = "neg r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10010000100100000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.negInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_23not() throws UnsupportedEncodingException {
		String sContent = "not r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10011000100100000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.notInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_24brzr() throws UnsupportedEncodingException {
		String sContent = "brzr r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10100000100100000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brzrInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_25brnz() throws UnsupportedEncodingException {
		String sContent = "brnz r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10100000100100000000000000000001", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brnzInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_26brpl() throws UnsupportedEncodingException {
		String sContent = "brpl r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10100000100100000000000000000010", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brplInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_27brmi() throws UnsupportedEncodingException {
		String sContent = "brmi r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10100000100100000000000000000011", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brmiInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_28jr() throws UnsupportedEncodingException {
		String sContent = "jr r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10101000100000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.jrInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_29jal() throws UnsupportedEncodingException {
		String sContent = "jal r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10110000100000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.jalInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_30in() throws UnsupportedEncodingException {
		String sContent = "in r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10111000100000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.inInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_31out() throws UnsupportedEncodingException {
		String sContent = "out r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("11000000100000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.outInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_32mfhi() throws UnsupportedEncodingException {
		String sContent = "mfhi r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("11001000100000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.mfhiInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_33mflo() throws UnsupportedEncodingException {
		String sContent = "mflo r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("11010000100000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.mfloInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_34nop() throws UnsupportedEncodingException {
		String sContent = "nop";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("11011000000000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.nopInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_01inst_35halt() throws UnsupportedEncodingException {
		String sContent = "halt";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("11100000000000000000000000000000", 2);
		
		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.haltInstr();
		parser.Expect(0);
		
		Assert.assertEquals(w.get(), expected);
	}
	
	@org.junit.Test
	public void testScan_02prog_01() throws UnsupportedEncodingException {
		String sContent = "ld r2, 1\n"
				+ "ld r2, 1(r3)\n"
				+ "ldi r15, 10\n"
				+ "ldi r15, 10(r14)\n"
				+ "st 1, r1\n"
				+ "st 1(r3), r1\n"
				+ "ldr r15, 10\n"
				+ "str 10, r15\n"
				+ ".org 0x10\n"
				+ "add r1, r2, r3\n"
				+ "sub r1, r2, r3\n"
				+ "and r1, r2, r3\n"
				+ "or r1, r2, r3\n"
				+ "shr r1, r2, r3\n"
				+ "shl r1, r2, r3\n"
				+ "ror r1, r2, r3\n"
				+ "rol r1, r2, r3\n"
				+ "addi r1, r2, 0xff\n"
				+ "andi r1, r2, 0xff\n"
				+ "ori r1, r2, 0xff\n"
				+ "mul r1, r2\n"
				+ "div r1, r2\n"
				+ "neg r1, r2\n"
				+ "not r1, r2\n"
				+ "brzr r1, r2\n"
				+ "brnz r1, r2\n"
				+ "brpl r1, r2\n"
				+ "brmi r1, r2\n"
				+ "jr r1\n"
				+ "jal r1\n"
				+ "in r1\n"
				+ "out r1\n"
				+ "mfhi r1\n"
				+ "mflo r1\n"
				+ "nop\n"
				+ "halt\n";
		
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		String expected = 
			"0000 : 01000001;\n"
			+ "0001 : 01180001;\n"
			+ "0002 : 0f80000a;\n"
			+ "0003 : 0ff0000a;\n"
			+ "0004 : 10800001;\n"
			+ "0005 : 10980001;\n"
			+ "0006 : 1f80000a;\n"
			+ "0007 : 2780000a;\n"
			+ "[0008..000f] : 00000000;\n"
			+ "0010 : 28918000;\n"
			+ "0011 : 30918000;\n"
			+ "0012 : 38918000;\n"
			+ "0013 : 40918000;\n"
			+ "0014 : 48918000;\n"
			+ "0015 : 50918000;\n"
			+ "0016 : 58918000;\n"
			+ "0017 : 60918000;\n"
			+ "0018 : 689000ff;\n"
			+ "0019 : 709000ff;\n"
			+ "001a : 789000ff;\n"
			+ "001b : 80900000;\n"
			+ "001c : 88900000;\n"
			+ "001d : 90900000;\n"
			+ "001e : 98900000;\n"
			+ "001f : a0900000;\n"
			+ "0020 : a0900001;\n"
			+ "0021 : a0900002;\n"
			+ "0022 : a0900003;\n"
			+ "0023 : a8800000;\n"
			+ "0024 : b0800000;\n"
			+ "0025 : b8800000;\n"
			+ "0026 : c0800000;\n"
			+ "0027 : c8800000;\n"
			+ "0028 : d0800000;\n"
			+ "0029 : d8000000;\n"
			+ "002a : e0000000;\n";
		
		Parser parser = new Parser(scanner);
		parser.Parse();
		
		Assert.assertEquals(parser.mem.toString(), expected);
	}
	
	@org.junit.Test
	public void testScan_03dir_01word() throws UnsupportedEncodingException {
		String sContent = ".word 0x0 0x7FFFffff 10";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		String expected = 
			"0000 : 00000000;\n"
			+ "0001 : 7fffffff;\n"
			+ "0002 : 0000000a;\n";
		
		Parser parser = new Parser(scanner);
		parser.Parse();
		
		Assert.assertEquals(parser.mem.toString(), expected);
	}
	
	@org.junit.Test
	public void testScan_03dir_02byte() throws UnsupportedEncodingException {
		String sContent = ".byte 0x0 0x0a 15 0b0111 0b11111111";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		String expected = 
			"0000 : 070f0a00;\n"
			+ "0001 : 000000ff;\n";
		
		Parser parser = new Parser(scanner);
		parser.Parse();
		
		Assert.assertEquals(parser.mem.toString(), expected);
	}
	
	@org.junit.Test
	public void testScan_02prog_02() throws UnsupportedEncodingException {
		String sContent = ".org 0\n"
				+ "ldi	R3, 0x87\n"
				+ "ldi	R3, 1(R3)\n"
				+ "ld	R2, 0x66\n"
				+ "ld	R2, -1(R2) \n"
				+ "ldr	R7, 0x61 \n"
				+ "str	0x60, R7 \n"
				+ "ld	R1, 0(R2) \n"
				+ "ldi	R14, 1\n"
				+ "nop\n"
				+ "add	R3, R2, R3 \n"
				+ "addi	R7, R7, 2 \n"
				+ "neg	R7, R7 \n"
				+ "not	R7, R7 \n"
				+ "andi	R7, R7, 0x0F \n"
				+ "ori	R7, R1, 3 \n"
				+ "shr	R2, R3, R14 \n"
				+ "st	0x56, R2 \n"
				+ "ror	R1, R1, R14 \n"
				+ "rol	R2, R2, R14 \n"
				+ "or	R2, R3, R14 \n"
				+ "and	R1, R2, R1 \n"
				+ "st	0x4C(R1), R3 \n"
				+ "sub	R3, R2, R3 \n"
				+ "shl	R1, R2, R14 \n"
				+ "ldi	R4, 5 \n"
				+ "ldi	R5, 0x1F \n"
				+ "mul	R5, R4 \n"
				+ "mfhi	R7 \n"
				+ "mflo	R6 \n"
				+ "div	R5, R4 \n"
				+ "ldi	R10, 0(R4) \n"
				+ "ldi	R11, 0(R5) \n"
				+ "ldi	R12, 0(R6) \n"
				+ "ldi	R13, 0(R7) \n"
				+ "jal	R12 \n"
				+ "\n"
				+ "in	R4 \n"
				+ "st	0x90, R4 \n"
				+ "ldi	R1, 0x2A \n"
				+ "ldi	R2, 0x2E \n"
				+ "ldi	R3, 0x35 \n"
				+ "ldi	R7, 1 \n"
				+ "ldi	R5, 40 \n"
				+ "out	R4 \n"
				+ "ldi	R5, -1(R5) \n"
				+ "brzr	R5, R3 \n"
				+ "ld	R6, 0xF0 \n"
				+ "ldi	R6, -1(R6) \n"
				+ "nop\n"
				+ "brnz	R6, R2 \n"
				+ "shr	R4, R4, R7 \n"
				+ "brnz	R4, R1 \n"
				+ "ld	R4, 0x90 \n"
				+ "jr	R1 \n"
				+ "ldi	R4, 0xA5 \n"
				+ "out	R4 \n"
				+ "halt\n"
				+ ".org 0x56\n"
				+ ".word 0x34\n"
				+ ".org 0x66\n"
				+ ".word 0x57\n"
				+ ".org 0x90\n"
				+ ".word 0x80\n"
				+ "\n"
				+ ".ORG 0x9B \n"
				+ "add	R9, R10, R12 \n"
				+ "sub	R8, R11, R13 \n"
				+ "sub	R9, R9, R8\n"
				+ "jr	R15\n"
				+ ".org 0xf0\n"
				+ ".word 0x0fff";
		
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		String expected = 
			"0000 : 09800087;\n"
			+ "0001 : 09980001;\n"
			+ "0002 : 01000066;\n"
			+ "0003 : 0117ffff;\n"
			+ "0004 : 1b800061;\n"
			+ "0005 : 23800060;\n"
			+ "0006 : 00900000;\n"
			+ "0007 : 0f000001;\n"
			+ "0008 : d8000000;\n"
			+ "0009 : 29918000;\n"
			+ "000a : 6bb80002;\n"
			+ "000b : 93b80000;\n"
			+ "000c : 9bb80000;\n"
			+ "000d : 73b8000f;\n"
			+ "000e : 7b880003;\n"
			+ "000f : 491f0000;\n"
			+ "0010 : 11000056;\n"
			+ "0011 : 588f0000;\n"
			+ "0012 : 61170000;\n"
			+ "0013 : 411f0000;\n"
			+ "0014 : 38908000;\n"
			+ "0015 : 1188004c;\n"
			+ "0016 : 31918000;\n"
			+ "0017 : 50970000;\n"
			+ "0018 : 0a000005;\n"
			+ "0019 : 0a80001f;\n"
			+ "001a : 82a00000;\n"
			+ "001b : cb800000;\n"
			+ "001c : d3000000;\n"
			+ "001d : 8aa00000;\n"
			+ "001e : 0d200000;\n"
			+ "001f : 0da80000;\n"
			+ "0020 : 0e300000;\n"
			+ "0021 : 0eb80000;\n"
			+ "0022 : b6000000;\n"
			+ "0023 : ba000000;\n"
			+ "0024 : 12000090;\n"
			+ "0025 : 0880002a;\n"
			+ "0026 : 0900002e;\n"
			+ "0027 : 09800035;\n"
			+ "0028 : 0b800001;\n"
			+ "0029 : 0a800028;\n"
			+ "002a : c2000000;\n"
			+ "002b : 0aafffff;\n"
			+ "002c : a2980000;\n"
			+ "002d : 030000f0;\n"
			+ "002e : 0b37ffff;\n"
			+ "002f : d8000000;\n"
			+ "0030 : a3100001;\n"
			+ "0031 : 4a238000;\n"
			+ "0032 : a2080001;\n"
			+ "0033 : 02000090;\n"
			+ "0034 : a8800000;\n"
			+ "0035 : 0a0000a5;\n"
			+ "0036 : c2000000;\n"
			+ "0037 : e0000000;\n"
			+ "[0038..0055] : 00000000;\n"
			+ "0056 : 00000034;\n"
			+ "[0057..0065] : 00000000;\n"
			+ "0066 : 00000057;\n"
			+ "[0067..008f] : 00000000;\n"
			+ "0090 : 00000080;\n"
			+ "[0091..009a] : 00000000;\n"
			+ "009b : 2cd60000;\n"
			+ "009c : 345e8000;\n"
			+ "009d : 34cc0000;\n"
			+ "009e : af800000;\n"
			+ "[009f..00ef] : 00000000;\n"
			+ "00f0 : 00000fff;\n";
		
		Parser parser = new Parser(scanner);
		parser.Parse();
		
		Assert.assertEquals(parser.mem.toString(), expected);
	}
	
	@org.junit.Test(expected=FatalError.class)
	public void testScan_04excp_01bck() throws UnsupportedEncodingException {
		String sContent = 
			".org 2\n.org 1\n";
		
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		
		Parser parser = new Parser(scanner);
		parser.Parse();
	}
}
