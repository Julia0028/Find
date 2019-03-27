import java.io.File;
import java.util.ArrayList;

public class Find {
    private ArrayList<String> filePath = new ArrayList<String>();
    private boolean r;
    private String fileName;

    public Find(String dirName, String fileName, boolean r) {
        this.r = r;
        this.fileName = fileName;
        //если не указана директория, то ищем файл сразу в C:\Users\Julia\Find
        if (isDefault(dirName)) findFile(new File(".").getAbsolutePath());
        else {
            //если указана, то нам нужно найти ее полный путь, начиная от корневой папки
            File dirPath = new File(dirName);
            if (!dirPath.exists()) filePath.add("Directory not found");
            else findFile(dirPath.getAbsolutePath());
        }
        if (filePath.isEmpty() && !r) filePath.add("Need search in subfolders. File not found");
        if (filePath.isEmpty() && r) filePath.add("File not found");
    }

    public boolean isDefault(String dirName) {
        return dirName.equals("");
    }

    private void findFile(String dirPath) {
        ArrayList<File> subFolders = new ArrayList<File>();
        File dir = new File(dirPath);
        File[] list = dir.listFiles();
        if (list != null) {
            for (File f: list) {
                if (f.getName().equals(fileName))
                    filePath.add(f.getAbsolutePath());
                //я собираю еще в отдельный список поддиректории,
                // чтобы пройтись по ним, если ключ r будет true
                if (f.isDirectory())subFolders.add(f);
            }

            if (r) {
               for (File f: subFolders)
               findFile(f.getAbsolutePath());
            }
        }
    }

    public ArrayList<String> getFilePath() {
        return filePath;
    }
}

