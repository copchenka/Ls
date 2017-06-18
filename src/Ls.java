import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Ls {
    private List<File> workList;

    public Ls(File file) {
        workList = new ArrayList<>();
        File[] list = file.listFiles();
        if (file.isDirectory()) {
            assert list != null;
            Collections.addAll(this.workList, list);
        } else if (file.isFile()) this.workList.add(file);
    }

    public List<List<String>> ls(final Boolean l, final Boolean h, final Boolean r) {
        List<List<String>> result = new ArrayList<>();

        for (File element : workList) {
            List<String> doresult = new ArrayList<>();
            doresult.add(element.getName());
            if (l && h) {
                doresult.add(rwx(element));
                doresult.add(String.format("%8s", normalsize(size(element))));
                doresult.add(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(element.lastModified()));
            } else if (l) {
                doresult.add(mask(element));
                doresult.add(String.format("%12s", size(element)));
                doresult.add(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(element.lastModified()));
            }
            result.add(doresult);
        }
        if (r) Collections.reverse(result);
        return result;
    }

    public void output(final String o, final List<List<String>> list) throws IOException {
        PrintStream printStream;
        if (o == null) printStream = System.out;
        else printStream = new PrintStream(new File(o));
        for (final List<String> sublist : list) {
            for (int i = 0; i < sublist.size(); i++) {
                printStream.print(sublist.get(i));
                if (i < sublist.size() - 1) printStream.print("  ");
            }
            printStream.println();
        }
    }

    private String rwx(File file) {
        String result = "";
        if (file.canRead()) result += "r";
        else result += "-";
        if (file.canWrite()) result += "w";
        else result += "-";
        if (file.canExecute()) result += "x";
        else result += "-";
        return result;
    }

    private String mask(File file) {
        String result = "";
        if (file.canRead()) result += "1";
        else result += "0";
        if (file.canWrite()) result += "1";
        else result += "0";
        if (file.canExecute()) result += "1";
        else result += "0";
        return result;
    }

    private String normalsize(long s) {
        String sizeS = "";
        if (s > 1024 * 1024 * 1024) {
            sizeS = String.format(Locale.ENGLISH, "%.1f", (float) s / (1024 * 1024 * 1024)) + "Gb";
        } else if (s > 1024 * 1024) {
            sizeS = String.format(Locale.ENGLISH, "%.1f", (float) s / (1024 * 1024)) + "Mb";
        } else if (s > 1024) {
            sizeS = String.format(Locale.ENGLISH, "%.1f", (float) s / 1024) + "Kb";
        } else sizeS = String.format(Locale.ENGLISH, "%.1f", (float) s) + "b";
        return sizeS;
    }

    private long size(File file) {
        long a = 0;
        if (file.isFile()) a = file.length();
        else {
            for (final File b : file.listFiles()) {
                if (file.isFile()) a += b.length();
                else a += size(b);
            }
        }
        return a;
    }
}
