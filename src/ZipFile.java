import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//разорхивируем файл(файлы приходят аргументами в мейн, начиная со второго(первый аргумент - имя результирующего файла))
public class ZipFile {
    public static void main(String[] args) throws IOException {
        File result = new File(args[0]); //файд результата, по совместительству имя этого файда мы ищем в архиве
        if (!result.exists()){
            result.createNewFile();
        }
        List<FileInputStream> fileInputStreams = new ArrayList<>(); // список входящих стримов из разных кусков архива

       // раставляем имена файлов архива в нужном нам порядке
        List<String> fileNames = new ArrayList<>();
        fileNames.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(fileNames);

        // создаем входящий стрим для каждого куска архива
        for (String name : fileNames){
            fileInputStreams.add(new FileInputStream(name));
        }
        // входящий стрим общего архива
        try(ZipInputStream is = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fileInputStreams)))){
            while (true){
                ZipEntry entry = is.getNextEntry();
                if (entry == null){
                    break;
                }
                try(OutputStream os = new BufferedOutputStream(new FileOutputStream(result))){
                    final int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    for (int readBytes; (readBytes = is.read(buffer, 0, bufferSize)) > -1;){
                        os.write(buffer, 0, readBytes);
                    }
                    os.flush();
                }
            }
        }
    }
}
