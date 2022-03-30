package org.sunshine.lc.test.mmap;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/***
 * 如果文件大于2G，需要分片映射
 */
public class Memory2FileMap {

    private String filePath;
    private File file;
    private RandomAccessFile randomAccessFile;
    private MappedByteBuffer mappedByteBuffer;
    private int readIndex;
    private int writeIndex;

    public Memory2FileMap(String filePath){
        this.filePath = filePath;
    }

    public void init() throws IOException {
        this.file = new File(filePath);
        this.randomAccessFile = new RandomAccessFile(file, "rw");
        this.mappedByteBuffer = this.randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
    }

    /***
     * 顺序写
     * @param content 写入内容
     * @return
     */
    public void fileWrite(byte[] content) {
        this.mappedByteBuffer.position(this.writeIndex);
        this.mappedByteBuffer.put(content);
        this.writeIndex = this.mappedByteBuffer.position();
        System.out.println(this.writeIndex);
    }

    public void writeByte(byte b) {
        this.mappedByteBuffer.position(this.writeIndex);
        this.mappedByteBuffer.put(b);
        this.writeIndex = this.mappedByteBuffer.position();
        System.out.println(this.writeIndex);
    }


    /***
     * 顺序读
     * @param len 读的长度
     * @return
     */
    public byte[] fileRead(int len) {
        byte[] content = new byte[len];
        this.mappedByteBuffer.position(this.readIndex);
        this.mappedByteBuffer.get(content, 0, len);
        this.readIndex = this.readIndex + len;
        return content;
    }

    public void clear(){
        this.writeIndex = 0;
        this.readIndex = 0;
        this.mappedByteBuffer.clear();
    }

    public static void main(String args[]) throws IOException {

        /*
        Memory2FileMap memory2FileMap = new Memory2FileMap("C:\\D\\test.txt");
        memory2FileMap.init();
        byte[] goland = getFileContent("C:\\D\\GoLand 2020.1.zip");
        for(int i = 0 ; i < 4 ; i ++) {
            memory2FileMap.fileWrite(goland);
        }
        byte[] res = memory2FileMap.fileRead(1024 * 1024 * 1024);
        byte[] res1 = memory2FileMap.fileRead(1024 * 1024 * 1023);
        System.out.println(res);*/

        Memory2FileMap memory2FileMap = new Memory2FileMap("C:\\D\\test.txt");
        memory2FileMap.init();
        byte[] goland = getFileContent("C:\\D\\GoLand 2020.1.zip");
        memory2FileMap.fileWrite(goland);
        byte[] content = memory2FileMap.fileRead(10);

        memory2FileMap.clear();

        byte[] microsoft = getFileContent("C:\\D\\Microsoft_Office2013.zip");

        long time = System.currentTimeMillis();
        memory2FileMap.fileWrite(microsoft);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - time));
        byte[] content2 = memory2FileMap.fileRead(10);
        System.out.println(content2);
    }

    private static byte[] getFileContent(String filePath){
        File file = new File(filePath);
        BufferedInputStream bis;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            int len = bis.available();
            byte[] result = new byte[len];
            bis.read(result, 0, len);
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
