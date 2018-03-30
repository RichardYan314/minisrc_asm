import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class MiniSRCasm
{
    public static void main(String[] args)
    {
        Option help = Option.builder("h")
                .longOpt("help")
                .numberOfArgs(0)
                .required(false)
                .desc("print this message")
                .build();

        Option verbose = Option.builder("v")
                .longOpt("verbose")
                .numberOfArgs(0)
                .required(false)
                .desc("be super verbose (you probably do not want this)")
                .build();

        Option oFile = Option.builder("o")
                .numberOfArgs(1)
                .argName("file")
                .required(false)
                .desc("output file")
                .build();

        Option iFile = Option.builder("i")
                .numberOfArgs(1)
                .argName("file")
                .required(false)
                .desc("source file")
                .build();

        Options options = new Options();
        options.addOption(help);
        options.addOption(verbose);
        options.addOption(oFile);
        options.addOption(iFile);

        CommandLineParser parser = new DefaultParser();
        try
        {
            CommandLine cmd = parser.parse(options, args);
            HelpFormatter formatter;
            if (cmd.hasOption("help"))
            {
                formatter = new HelpFormatter();
                formatter.printHelp("asmb {[options]}", options);
            }
            else if (cmd.hasOption("i"))
            {
                System.out.println(cmd.getOptionValue("i"));
                try
                {
                    try (InputStream is = new FileInputStream(cmd.getOptionValue("i"));)
                    {
                        Scanner s = new Scanner(is);
                        Parser p = new Parser(s);
                        if (cmd.hasOption("v"))
                            p.debug = true;

                        String outfile;
                        if (cmd.hasOption("o"))
                            outfile = (cmd.getOptionValue("o"));
                        else
                            outfile = cmd.getOptionValue("i").replace(".s", ".mif");

                        p.Parse();

                        BufferedWriter bw = null;
                        FileWriter fw = null;
                        try {
                            fw = new FileWriter(outfile);
                            bw = new BufferedWriter(fw);

                            String str =
                                    "WIDTH=32;\n" +
                                            "DEPTH=";

                            int size = 1;
                            while (size < p.mem.getSize())
                            {
                                size <<= 1;
                            }

                            str +=
                                    size +
                                            ";\n" +
                                            "\n" +
                                            "ADDRESS_RADIX=HEX;\n" +
                                            "DATA_RADIX=HEX;\n" +
                                            "\n" +
                                            "CONTENT BEGIN\n";

                            bw.write(str);
                            bw.write(p.mem.toString());

                            str = "END;\n";
                            bw.write(str);

                            System.out.println("Done");

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (bw != null) bw.close();
                                if (fw != null) fw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            else
            {
                System.err.println("Please specify input file");
                return;
            }
        }
        catch (ParseException e)
        {
            System.err.println("Argument parsing failed. Reason: " + e.getMessage());
            e.printStackTrace();
        }
    }
}