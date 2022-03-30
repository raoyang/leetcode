package org.sunshine.lc.test.mmap;

import org.sunshine.lc.test.proto.TimeTaskCacheProto;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeTaskCache implements Serializable {

    private Map<String, TimeTask> cache;

    public TimeTaskCache() {
        cache = new HashMap<>();
    }

    public void addTimeTask(TimeTask timeTask) {
        cache.put(timeTask.getTaskId(), timeTask);
    }

    public Map<String, TimeTask> getCache() {
        return cache;
    }

    public void setCache(Map<String, TimeTask> cache) {
        this.cache = cache;
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        /*
        TimeTaskCache timeTaskCache = new TimeTaskCache();
        long time = System.currentTimeMillis();
        for(int i = 0 ; i < 1000000 ; i ++){
            TimeTask timeTask = new TimeTask(getTaskId(i));
            timeTask.setStartTime(time);
            timeTask.setEndTime(time);
            timeTaskCache.addTimeTask(timeTask);
            System.out.println("写入:" + timeTask.getTaskId());
        }
        long end = System.currentTimeMillis();
        System.out.println("插入耗时:" + (end - time));
        writeObject2FileWithProto(timeTaskCache);
        System.out.println("转换并写入耗时:" + (System.currentTimeMillis() - end));*/
        long time = System.currentTimeMillis();
        readObjectFromFileWithProto();
        System.out.println("读取耗时:" + (System.currentTimeMillis() - time));
    }

    private static void writeObject2FileWithProto(TimeTaskCache timeTaskCache) throws IOException {
        TimeTaskCacheProto.TimeTaskCache.Builder ttc = TimeTaskCacheProto.TimeTaskCache.newBuilder();
        for(TimeTask task : timeTaskCache.cache.values()){
            TimeTaskCacheProto.TimeTask.Builder tt = TimeTaskCacheProto.TimeTask.newBuilder();
            tt.setTaskId(task.getTaskId());
            tt.setStartTime(task.getStartTime());
            tt.setEndTime(task.getEndTime());
            ttc.putCache(task.getTaskId(), tt.build());
        }
        byte[] stream = ttc.build().toByteArray();
        System.out.println("content len:" + stream.length);
        Memory2FileMap memory2FileMap = new Memory2FileMap("C:\\D\\object.txt");
        memory2FileMap.init();
        memory2FileMap.fileWrite(stream);
    }

    private static void readObjectFromFileWithProto() throws IOException {
        Memory2FileMap memory2FileMap = new Memory2FileMap("C:\\D\\object.txt");
        memory2FileMap.init();
        byte[] content = memory2FileMap.fileRead(99777780);

        TimeTaskCacheProto.TimeTaskCache ttc = TimeTaskCacheProto.TimeTaskCache.parseFrom(content);
        TimeTaskCache timeTaskCache = new TimeTaskCache();
        for(TimeTaskCacheProto.TimeTask taskProto : ttc.getCacheMap().values()) {
            TimeTask timeTask = new TimeTask(taskProto.getTaskId());
            timeTask.setStartTime(taskProto.getStartTime());
            timeTask.setEndTime(taskProto.getEndTime());
            timeTaskCache.addTimeTask(timeTask);
        }
        System.out.println(timeTaskCache);
    }

    private static void writeObject2File(TimeTaskCache timeTaskCache) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(timeTaskCache);
        byte[] stream = byteArrayOutputStream.toByteArray();
        System.out.println("content len:" + stream.length);
        Memory2FileMap memory2FileMap = new Memory2FileMap("C:\\D\\object.txt");
        memory2FileMap.init();
        memory2FileMap.fileWrite(stream);
    }

    private static void readObjectFromFile() throws IOException, ClassNotFoundException {
        Memory2FileMap memory2FileMap = new Memory2FileMap("C:\\D\\object.txt");
        memory2FileMap.init();
        byte[] content = memory2FileMap.fileRead(68889191);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        TimeTaskCache timeTaskCache = (TimeTaskCache) objectInputStream.readObject();
        System.out.println(timeTaskCache);
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

    private static String getTaskId(int i){
        String uuid = UUID.randomUUID().toString().replace("-","");
        StringBuilder sb = new StringBuilder();
        sb.append(uuid)
                .append('-')
                .append(i);
        return sb.toString();
    }
}
