package org.example;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 解码类
 */
public class Decode {
    private static final String source = "./source";
    private static final String out = "./out";
    private static final Charset charset = StandardCharsets.ISO_8859_1;

    public static void main(String[] args) throws IOException {
        List<String> fileNames = FileUtil.listFileNames(source);
        if (CollectionUtil.isEmpty(fileNames)) {
            System.out.println("无文件");
            return;
        }
        fileNames.sort(Decode::compareString);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (String fileName : fileNames) {
            System.out.println(fileName);
            File file = new File(source + "/" + fileName);
            String source = QrCodeUtil.decode(ImgUtil.read(file), false, true);
            byte[] bytes = source.getBytes(charset);
            outputStream.write(bytes);
        }
        byte[] bytes = outputStream.toByteArray();
        FileUtil.writeBytes(bytes, out + "/out.zip");
    }

    private static int compareString(String str1, String str2) {
        String[] split1 = str1.split("\\.");
        String[] split2 = str2.split("\\.");

        if (split1.length <= 1 || split2.length <= 1) {
            return str1.compareTo(str2);
        }
        try {
            Integer a = Integer.parseInt(split1[0]);
            Integer b = Integer.parseInt(split2[0]);
            return a.compareTo(b);
        } catch (Exception e) {
            return str1.compareTo(str2);
        }
    }
}
