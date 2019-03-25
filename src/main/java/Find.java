import java.io.File;
import java.util.ArrayList;

public class Find {
    private ArrayList<String> filePath = new ArrayList<String>();
    private boolean r;

    public Find(String dirName, String fileName, boolean r) {
        this.r = r;
        //если не указана директория, то ищем файл сразу в C:\Users\Julia\Find
        if (dirName.equals("")) findFile(new File(".").getAbsolutePath(), fileName);
        else {
            //если указана, то нам нужно найти ее полный путь, начиная от корневой папки
            findDir(dirName);
            //я использую только один список, куда сначала помещаю найденный путь директории, затем удаляю
            // и помещаю уже весь искомый путь
            String dirPath = filePath.get(0);
            filePath.remove(0);
            findFile(dirPath, fileName);
        }
        if (filePath.isEmpty()) filePath.add("Not found");
    }

    private void findFile(String dirName, String fileName) {
        String folderName = fileName.replaceAll("(.txt)$", "");
        ArrayList<File> subFolders = new ArrayList<File>();
        File dir = new File(dirName);
        File[] list = dir.listFiles();
        if (list != null) {
            for (File f: list) {
                if (f.getName().equals(folderName))
                    filePath.add(f.getAbsolutePath());
                //я собираю еще в отдельный список поддиректории,
                // чтобы пройтись по ним, если ключ r будет true
                if (f.isDirectory())subFolders.add(f);
            }
           if (filePath.isEmpty() && !subFolders.isEmpty() && r) {
               for (File f: subFolders)
               findFile(f.getAbsolutePath(), folderName);
           }
            }

        }

        //метод нужен, чтобы найти путь до любой дирректории, начиная с корневой
    private void findDir(String dirName) {
        File defaultDir = new File(".");
        File[] list = defaultDir.listFiles();
        if (list != null) {
            for (File f: list) {
                if (dirName.equals(f.getName())) {
                    filePath.add(f.getAbsolutePath());
                } else if (!dirName.equals(f.getName()) && f.isDirectory() && r)
                    findFile(f.getAbsolutePath(), dirName);
            }
        }
    }



    public ArrayList<String> getFilePath() {
        return filePath;
    }
}

