package org.example;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 编码类
 */
public class Encode {

    private static final Integer maxSize = 800;
    private static final Integer width = 600;
    private static final Integer height = 600;
    private static final Charset charset = StandardCharsets.ISO_8859_1;
    private static final String source = "./source";
    private static final String out = "./out";

    public static void main(String[] args) {
        List<String> fileNames = FileUtil.listFileNames(source);
        if (CollectionUtil.isEmpty(fileNames)) {
            System.out.println("无文件");
            return;
        }
        List<File> files = new ArrayList<>(fileNames.size());
        for (String fileName : fileNames) {
            files.add(new File(source + "/" + fileName));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipUtil.zip(outputStream, charset, false, null, files.toArray(new File[0]));
        byte[] byteArray = outputStream.toByteArray();
        // 写入图片
        int fileIndex = 1;
        int current = 0;
        while (current < byteArray.length) {
            byte[] sub = ArrayUtil.sub(byteArray, current, current + maxSize);
            String content = new String(sub, charset);
            String fileName = out + "/" + fileIndex + ".png";
            QrCodeUtil.generate(content, width, height, new File(fileName));
            System.out.println(fileName);
            fileIndex++;
            current += maxSize;
        }
        System.out.println("完成");
    }

}