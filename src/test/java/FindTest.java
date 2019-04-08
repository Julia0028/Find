import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.ArrayList;


public class FindTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ArrayList<String> list = new ArrayList<String>();

    @Test
    public void Find1() {
        File dir = new File("Directory/Directory1");
        File fileName = new File(dir, "aloha");
        Find f = new Find(dir, fileName, false);
        list.add("C:\\Users\\Julia\\Find\\Directory\\Directory1\\aloha");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find2() {
        File dir = new File("Directory/Directory2");
        File fileName = new File(dir, "kek");
        Find f = new Find(dir, fileName, true);
        list.add("C:\\Users\\Julia\\Find\\Directory\\Directory2\\zaz\\kek");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find3() {
        File dir = new File(".");
        File fileName = new File(dir, "kek");
        Find f = new Find( dir, fileName, true);
        list.add("C:\\Users\\Julia\\Find\\.\\Directory\\Directory2\\zaz\\kek");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find4() {
        File dir = new File(".");
        File fileName = new File(dir, "kek");
        Find f = new Find(dir, fileName, false);
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find5() {
        File dir = new File("Directory");
        File fileName = new File(dir, "kek");
        Find f = new Find(dir, fileName, true);
        list.add("C:\\Users\\Julia\\Find\\Directory\\Directory1\\kek");
        list.add("C:\\Users\\Julia\\Find\\Directory\\Directory2\\zaz\\kek");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void Find6() {
        File dir = new File("Directory/Dir");
        File fileName = new File(dir, "pp");
        Find f = new Find(dir, fileName, true);
        list.add("Directory not found");
        Assert.assertEquals(list, f.getFilePath());
    }

    @Test
    public void FindException(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Directory not found");
        File dir = new File("Directory/Dir");
        File fileName = new File(dir, "pp");
        Find f = new Find(dir, fileName, true);
    }

}