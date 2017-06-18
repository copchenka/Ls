    import org.kohsuke.args4j.Argument;
    import org.kohsuke.args4j.CmdLineException;
    import org.kohsuke.args4j.CmdLineParser;
    import org.kohsuke.args4j.Option;

    import java.io.File;
    import java.io.IOException;

    public class Launcher {
        @Option(name = "-l", metaVar = "l", usage = "Long")
        private boolean l;

        @Option(name = "-h", metaVar = "h", usage = "human-readable")
        private boolean h;

        @Option(name = "-r", metaVar = "r", usage = "reverse")
        private boolean r;

        @Option(name = "-o", metaVar = "o", usage = "output")
        private String o;

        @Argument(required = true, metaVar = "InputName", usage = "Input file name")
        private String inputFileName;


    public static void main(String[] args) {
            new Launcher().launch(args);
        }

        private void launch(String[] args) {
            CmdLineParser parser = new CmdLineParser(this);

            try {
                parser.parseArgument(args);
            } catch (CmdLineException e) {
                System.err.println(e.getMessage());
                System.err.println("ls [-l] [-h] [-r] [-o output.file] directory_or_file");
                parser.printUsage(System.err);
                return;
            }
            Ls ls = new Ls(new File(inputFileName));
            try {
                ls.output(o, ls.ls(l, h, r));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
       }
    }


