import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class LsTest {
    @Test
    public void ls() throws Exception {
        List<List<String>> workList = new ArrayList<>();
        List<String> sublist = new ArrayList<>();
        sublist.add("Dir0");
        workList.add(sublist);

        List<String> sublist1 = new ArrayList<>();
        sublist1.add("Dir1");
        workList.add(sublist1);

        List<String> sublist2 = new ArrayList<>();
        sublist2.add("Dir2");
        workList.add(sublist2);

        List<String> sublist5 = new ArrayList<>();
        sublist5.add("File");
        workList.add(sublist5);

        List<String> sublist8 = new ArrayList<>();
        sublist8.add("File3");
        workList.add(sublist8);

        Ls ls = new Ls(new File("DirTest"));
        assertEquals(workList, ls.ls(false, false, false));
        Collections.reverse(workList);
        assertEquals(workList, ls.ls(false, false, true));
        workList.clear();
        List<String> sublist007 = new ArrayList<>();
        sublist007.add("File1");
        workList.add(sublist007);
        ls = new Ls(new File("DirTest/Dir0"));
        assertEquals(workList, ls.ls(false, false, false));

        workList.clear();
        sublist.clear();
        sublist.add("Dir0");
        sublist.add("111");
        sublist.add(String.format("%12s", "19717"));
        sublist.add("18.06.2017 02:27:26");
        workList.add(sublist);

        sublist1.clear();
        sublist1.add("Dir1");
        sublist1.add("111");
        sublist1.add(String.format("%12s", "52319"));
        sublist1.add("18.06.2017 02:27:26");
        workList.add(sublist1);

        sublist2.clear();
        sublist2.add("Dir2");
        sublist2.add("111");
        sublist2.add(String.format("%12s", "45323"));
        sublist2.add("18.06.2017 02:27:26");
        workList.add(sublist2);

        sublist5.clear();
        sublist5.add("File");
        sublist5.add("111");
        sublist5.add(String.format("%12s", "17011"));
        sublist5.add("18.06.2017 02:27:26");
        workList.add(sublist5);

        sublist8.clear();
        sublist8.add("File3");
        sublist8.add("111");
        sublist8.add(String.format("%12s", "13737"));
        sublist8.add("18.06.2017 02:27:26");
        workList.add(sublist8);

        ls = new Ls(new File("DirTest"));
        assertEquals(workList, ls.ls( true, false, false));
        Collections.reverse(workList);
        assertEquals(workList, ls.ls(true, false, true));
        workList.clear();
        workList.add(sublist8);
        ls = new Ls(new File("DirTest/File3"));
        assertEquals(workList, ls.ls(true, false, false));

        workList.clear();
        sublist.clear();
        sublist.add("Dir0");
        sublist.add("rwx");
        sublist.add(String.format("%8s", "19.3Kb"));
        sublist.add("18.06.2017 02:27:26");
        workList.add(sublist);

        sublist1.clear();
        sublist1.add("Dir1");
        sublist1.add("rwx");
        sublist1.add(String.format("%8s", "51.1Kb"));
        sublist1.add("18.06.2017 02:27:26");
        workList.add(sublist1);

        sublist2.clear();
        sublist2.add("Dir2");
        sublist2.add("rwx");
        sublist2.add(String.format("%8s", "44.3Kb"));
        sublist2.add("18.06.2017 02:27:26");
        workList.add(sublist2);

        sublist5.clear();
        sublist5.add("File");
        sublist5.add("rwx");
        sublist5.add(String.format("%8s", "16.6Kb"));
        sublist5.add("18.06.2017 02:27:26");
        workList.add(sublist5);

        sublist8.clear();
        sublist8.add("File3");
        sublist8.add("rwx");
        sublist8.add(String.format("%8s", "13.4Kb"));
        sublist8.add("18.06.2017 02:27:26");
        workList.add(sublist8);

        ls = new Ls(new File("DirTest"));
        assertEquals(workList, ls.ls(true, true, false));
        Collections.reverse(workList);
        assertEquals(workList, ls.ls(true, true, true));
        workList.clear();
        workList.add(sublist8);
        ls = new Ls(new File("DirTest/File3"));
        assertEquals(workList, ls.ls(true, true, false));
    }

    @Test
    public void output() throws Exception {
        Ls ls = new Ls(new File("DirTest"));
        ls.output("output.txt", ls.ls(false, false, false));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        String st;
        while ((st = br.readLine()) != null) {
            sb.append(st);
            sb.append("\n");
        }
        assertEquals("Dir0\nDir1\nDir2\nFile\n" + "File3\n", sb.toString());
    }
}