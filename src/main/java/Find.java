import java.io.File;
import java.util.ArrayList;

public class Find {
    private ArrayList<String> filePath = new ArrayList<String>();
    private boolean r;
    private File fileName;

    public Find(File dir, File fileName, boolean r) {
        this.r = r;
        this.fileName = fileName;
        if (dir.exists()) findFile(dir);
        else throw new IllegalArgumentException("Directory not found");
    }


    private void findFile(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            for (File f: list) {
                if (f.getName().equals(fileName.getName()))
                    filePath.add(f.getAbsolutePath());
                //я собираю еще в отдельный список поддиректории,
                // чтобы пройтись по ним, если ключ r будет true
                if (f.isDirectory() && r) {
                        findFile(f);
                }
            }
        }
    }

    public ArrayList<String> getFilePath() {
        return filePath;
    }
}

