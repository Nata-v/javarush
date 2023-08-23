package archiver;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Path rootPath;
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.fileList = new ArrayList<>();
        collectFileList(rootPath);
    }
    public List<Path> getFileList(){
        return fileList;
    }
    public void collectFileList(Path path) throws IOException{
        // добавляем только файлы
        if (Files.isRegularFile(path)){
            Path relativePath = rootPath.relativize(path);
            fileList.add(relativePath);
        }
        // добавляем содержимое директории
        if (Files.isDirectory(path)){
            // рекурсивно проходимся по всему содержимому директории
            // чтобы не писать код по вызову close для DirectoryStream, обернем вызов newDirectoryStream в try-with-resources
            try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)){
                for (Path file : directoryStream){
                    collectFileList(file);
                }
            }
        }
    }
}
