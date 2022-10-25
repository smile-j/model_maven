package com.dong.base.test.io;

import cn.hutool.core.lang.UUID;
import org.apache.pdfbox.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownDemo {
    private static final int BUFFER_SIZE = 2 * 1024;
    static String fileLocalPath = "d://";

    public static void downByte(HttpServletResponse response, List<String> paths){
        //一个
        if (paths.size() == 1) {
            downOne(response, paths.get(0));
        }else {//多个
            downMore(response, paths);

        }
    }

    private static void downMore(HttpServletResponse response, List<String> paths) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("multipart/form-data;charset=utf-8");
            String fileName = MessageFormat.format("排除人群包{0}.zip", paths.size());
            response.setHeader("Content-disposition", checkDownFileNameHeader(fileName));
            response.flushBuffer();

            String filePath = fileLocalPath+UUID.randomUUID().toString().replace("-", "");
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
            for (String oosPath : paths) {
                inputStream = new URL(oosPath).openStream();
                fileOutputStream = new FileOutputStream(filePath + "/" + MessageFormat.format("{0}-{1}-互排导出.zip", UUID.randomUUID().toString(), UUID.randomUUID().toString()));
                IOUtils.copy(inputStream,fileOutputStream);
                inputStream.close();
                fileOutputStream.close();
            }
            toZip(filePath, outputStream, false);
            deleteDir(file);

        }catch (Exception e){
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            }catch (Exception e){ }
            try {
                if(outputStream!=null){
                    outputStream.close();
                }
            }catch (Exception e){ }
            try {
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            }catch (Exception e){ }
        }
    }

    private static void downOne(HttpServletResponse response, String oosPath) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            outputStream = response.getOutputStream();
            response.setContentType("multipart/form-data;charset=utf-8");
            String fileName = MessageFormat.format("{0}-{1}-互排导出.zip", System.currentTimeMillis(),System.currentTimeMillis());
            response.setHeader("Content-disposition", checkDownFileNameHeader(fileName));
            response.flushBuffer();
            inputStream = new URL(oosPath).openStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }catch (Exception e){
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            }catch (Exception e){ }
            try {
                if(outputStream!=null){
                    outputStream.close();
                }
            }catch (Exception e){ }
        }
    }

    public static void downNio(HttpServletResponse response, List<String> paths){
        ReadableByteChannel readableByteChannel = null;
        ServletOutputStream outputStream = null;
        URL url;
        try {
            outputStream = response.getOutputStream();
            if (paths.size() == 1) {
                String oosPath = paths.get(0);
                response.setContentType("multipart/form-data;charset=utf-8");
                String fileName = MessageFormat.format("{0}-{1}-导出.zip", UUID.randomUUID().toString(), UUID.randomUUID().toString());
                response.setHeader("Content-disposition", checkDownFileNameHeader(fileName));
                response.flushBuffer();

                url = new URL(oosPath);
                readableByteChannel = Channels.newChannel(url.openStream());

                WritableByteChannel wbc = Channels.newChannel(outputStream);
                channelCopy(readableByteChannel, wbc);

                if (null != wbc) {
                    wbc.close();
                }
                if (null != readableByteChannel) {
                    readableByteChannel.close();
                }

            } else {
                response.setContentType("multipart/form-data;charset=utf-8");
                String fileName = MessageFormat.format("排除人群包{0}.zip", paths.size());
                response.setHeader("Content-disposition", checkDownFileNameHeader(fileName));
                response.flushBuffer();

                String uuid = UUID.randomUUID().toString().replace("-", "");
                File file = new File(uuid);
                if (!file.exists()) {
                    file.mkdir();
                }
                for (String oosPath: paths) {
                    url = new URL(oosPath);
                    readableByteChannel = Channels.newChannel(url.openStream());

                    RandomAccessFile randomAccessFile = new RandomAccessFile(uuid + "/" + MessageFormat.format("{0}-{1}-互排导出.zip", UUID.randomUUID().toString(), UUID.randomUUID().toString()), "rw");
                    FileChannel fileChannel = randomAccessFile.getChannel();
                    channelCopy(readableByteChannel, fileChannel);

                    if (null != readableByteChannel) {
                        readableByteChannel.close();
                    }
                    if (null != fileChannel) {
                        fileChannel.close();
                    }
                }

                toZip(uuid, outputStream, false);

                deleteDir(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(outputStream!=null){
                    outputStream.close();
                }
            }catch (Exception e){
            }
        }
    }

    public static String checkDownFileNameHeader(String filename) {
        String returnFileName = "attachment;";
        returnFileName += " filename=\"" + encodeURIComponent(filename) + "\";";
        returnFileName += " filename*=utf-8''" + encodeURIComponent(filename);
        return returnFileName;
    }
    /**
     * <pre>
     * 符合 RFC 3986 标准的“百分号URL编码”
     * 在这个方法里，空格会被编码成%20，而不是+
     * 和浏览器的encodeURIComponent行为一致
     * </pre>
     *
     * @param value
     * @return
     */
    public static String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void channelCopy(ReadableByteChannel src, WritableByteChannel dst) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (src.read(buffer) != -1) {
            //切换为读状态
            buffer.flip();
            //不能保证全部写入
            dst.write(buffer);
            //释放已读数据空间，等待写入新数据
            buffer.compact();
        }
        //退出循环时，compact（）方法调用后，缓冲区中可能还有数据，需要再次读数据
        buffer.flip();
        while (buffer.hasRemaining()) {
            dst.write(buffer);
        }
    }

    private static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure)
            throws RuntimeException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */

    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();

                }

            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }

                }
            }
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
