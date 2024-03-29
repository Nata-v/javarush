package archiver;

public class FileProperties {
    private String name;
    private long size;
    private  long compressedSize;
    private  int compressionMethod;

    public FileProperties(String name, long size, long compressedSize, int compressionMethod) {
        this.name = name;
        this.size = size;
        this.compressedSize = compressedSize;
        this.compressionMethod = compressionMethod;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getCompressedSize() {
        return compressedSize;
    }

    public int getCompressionMethod() {
        return compressionMethod;
    }
    public long getCompressionRatio(){
        // вычисляем степень сжатия
        return 100 - ((compressedSize * 100) / size);
    }
    @Override
    public String toString(){
        //строим красивую стройку из свойств
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        if (size > 0){
            builder.append("\t");
            builder.append(size / 1024);
            builder.append(" KB (");
            builder.append(compressedSize / 1024);
            builder.append(" Kb) сжатие: ");
            builder.append(getCompressionRatio());
            builder.append("%");
        }
        return builder.toString();
    }

}
