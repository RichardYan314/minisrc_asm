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
	public void testScan_01inst_07add() throws UnsupportedEncodingException {
		String sContent = "add r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00011000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.addInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_08sub() throws UnsupportedEncodingException {
		String sContent = "sub r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00100000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.subInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_09and() throws UnsupportedEncodingException {
		String sContent = "and r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00101000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.andInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_10or() throws UnsupportedEncodingException {
		String sContent = "or r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00110000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.orInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_11shr() throws UnsupportedEncodingException {
		String sContent = "shr r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("00111000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.shrInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_12shra() throws UnsupportedEncodingException {
		String sContent = "shra r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01000000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.shraInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_13shl() throws UnsupportedEncodingException {
		String sContent = "shl r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01001000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.shlInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_14ror() throws UnsupportedEncodingException {
		String sContent = "ror r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01010000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.rorInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_15rol() throws UnsupportedEncodingException	{
		String sContent = "rol r1, r2, r3";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01011000100100011000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.rolInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_16addi() throws UnsupportedEncodingException {
		String sContent = "addi r1, r2, 0xff";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01100000100100000000000011111111", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.addiInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_17andi() throws UnsupportedEncodingException {
		String sContent = "andi r1, r2, 0xff";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01101000100100000000000011111111", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.andiInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
			}

	@org.junit.Test
	public void testScan_01inst_18ori()
		throws UnsupportedEncodingException
	{
		String sContent = "ori r1, r2, 0xff";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01110000100100000000000011111111", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.oriInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_19mul() throws UnsupportedEncodingException {
		String sContent = "mul r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("01111000100100000000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.mulInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_20div() throws UnsupportedEncodingException {
		String sContent = "div r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10000000100100000000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.divInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_21neg() throws UnsupportedEncodingException {
		String sContent = "neg r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10001000100100000000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.negInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_22not() throws UnsupportedEncodingException {
		String sContent = "not r1, r2";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10010000100100000000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.notInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_23brzr() throws UnsupportedEncodingException {
		String sContent = "brzr r1, 10";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10011000100000000000000000001010", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brzrInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_24brnz() throws UnsupportedEncodingException {
		String sContent = "brnz r1, 0xa";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10011000100010000000000000001010", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brnzInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_25brpl() throws UnsupportedEncodingException {
		String sContent = "brpl r1, -1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10011000100101111111111111111111", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brplInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_26brmi() throws UnsupportedEncodingException {
		String sContent = "brmi r1, -0x1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10011000100111111111111111111111", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.brmiInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_27jr() throws UnsupportedEncodingException {
		String sContent = "jr r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10100000100000000000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.jrInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_28jal() throws UnsupportedEncodingException {
		String sContent = "jal r1";
		System.out.println(sContent);
		InputStream is = new ByteArrayInputStream(sContent.getBytes("UTF-8"));
		Scanner scanner = new Scanner(is);
		int expected = Integer.parseUnsignedInt("10101000100000000000000000000000", 2);

		Parser parser = new Parser(scanner);
		parser.la = new Token();
		parser.la.val = "";
		parser.Get();
		Word w = parser.jalInstr();
		parser.Expect(0);

		Assert.assertEquals(w.get(), expected);
	}

	@org.junit.Test
	public void testScan_01inst_29in() throws UnsupportedEncodingException {
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
	public void testScan_01inst_30out() throws UnsupportedEncodingException {
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
	public void testScan_01inst_31mfhi() throws UnsupportedEncodingException {
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
	public void testScan_01inst_32mflo() throws UnsupportedEncodingException {
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
	public void testScan_01inst_33nop() throws UnsupportedEncodingException {
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
	public void testScan_01inst_34halt() throws UnsupportedEncodingException {
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