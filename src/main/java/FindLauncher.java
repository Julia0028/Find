import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.List;

public class FindLauncher {


    @Option(name = "-r", metaVar = "SubFold", usage = "We can look in the subfolders")
    private Boolean r = false;

    @Option(name = "-d", metaVar = "Directory", usage = "Directory Search")
    private File dir = new File(".");

    @Argument(required = true, metaVar = "File name", usage = "Input file name")
    private File fileName;


    public static void main(String[] args) {
        new FindLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try { parser.parseArgument(args);

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar find.jar -r -d directory filename.txt");
            parser.printUsage(System.err);
            return;

        }

        Find file = new Find(dir, fileName, r);

        try {
            List<String> result = file.getFilePath();
            for (String s: result) System.out.println(s);

        } catch (Exception e) {

            System.err.println(e.getMessage());

        }

    }

}

