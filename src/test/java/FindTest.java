import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;


public class FindTest {
    private ArrayList<String> list = new ArrayList<String>();

    @Test
    public void Find1() {
        String fileName = "aloha";
        String dirName = "Directory/Directory1";
        Find f = new Find(dirName, fileName, false);
        list.add("C:\\Users\\Julia\\Find\\Directory\\Directory1\\aloha");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find2() {
        String fileName = "kek";
        String dirName = "Directory/Directory2";
        Find f = new Find(dirName, fileName, true);
        list.add("C:\\Users\\Julia\\Find\\Directory\\Directory2\\zaz\\kek");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find3() {
        String fileName = "kek";
        String dirName = "";
        Find f = new Find(dirName, fileName, true);
        list.add("C:\\Users\\Julia\\Find\\.\\Directory\\Directory2\\zaz\\kek");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find4() {
        String fileName = "kek";
        String dirName = "";
        Find f = new Find(dirName, fileName, false);
        list.add("Need search in subfolders. File not found");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find5() {
        String fileName = "pp";
        String dirName = "";
        Find f = new Find(dirName, fileName, true);
        list.add("File not found");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find6() {
        String fileName = "pp";
        String dirName = "Directory/Dir";
        Find f = new Find(dirName, fileName, true);
        list.add("Directory not found");
        Assert.assertEquals(list, f.getFilePath());
    }

}