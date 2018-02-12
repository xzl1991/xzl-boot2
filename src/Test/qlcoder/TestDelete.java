package qlcoder;

import java.io.*;

/**
 * @auther xzl on 11:12 2018/1/3
 */
public class TestDelete {
    public static void main(String[] args) throws Exception {
        float f = -5f;
        //查看浮点类型的 IEEE754 表示
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(f)));
        File file = getFile("E:\\text.txt");
        //用 BufferWrite 写文件
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writer.write("创建了新文件,");
        writer.write("\n第二次输入");
        writer.flush();
//        file.deleteOnExit();
//        file.delete();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuilder sb = new StringBuilder();
        int ch = 0;
        char[] buf = new char[1];
        while ((ch = bufferedReader.read(buf)) != -1) {
            if (ch == '\r')
                continue;
            if (ch == '\n')
                continue;
            sb.append(buf);
        }
        System.out.println(sb.toString());
        System.out.println("读取的文件:" + bufferedReader.readLine());
        System.out.println("删除文件:" + file.exists());
    }

    private static File getFile(String filePath){
        File file = new File(filePath);
        System.out.println("文件存在:" + file.exists());
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
