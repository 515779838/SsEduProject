package com.example.sseduproject.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

public class FileTools {

    public static boolean isImgFile(String string) {
        // jpeg/jpg/gif/bmp/png
        if (string != null && !"".equals(string)) {
            string = string.toLowerCase();
            if (string.endsWith("png")) {
                return true;
            }
            if (string.endsWith("jpg")) {
                return true;
            }
            if (string.endsWith("jpeg")) {
                return true;
            }
            if (string.endsWith("gif")) {
                return true;
            }
            if (string.endsWith("bmp")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return true;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();

            Log.e("zj", "e ===" + e.toString());
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;

    }

//
//
//    public static void saveFile(String str, int pos) {
//        String filePath = Constant.SDPATH + File.separator + "hello" + pos + ".txt";
//        try {
//            File file = new File(filePath);
//            if (!file.exists()) {
//                File dir = new File(file.getParent());
//                dir.mkdirs();
//                file.createNewFile();
//            }
//            FileOutputStream outStream = new FileOutputStream(file);
//            outStream.write(str.getBytes());
//            outStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static Bitmap stringToBitmap(String string) {
//        //将字符串转换成Bitmap类型
//        Bitmap bitmap = null;
//        try {
//            byte[] bitmapArray;
//            bitmapArray = Base64.decode(string, Base64.DEFAULT);
//            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }

    /**
     * 将从Message中获取的，表示图片的字符串解析为Bitmap对象
     *
     * @param picStrInMsg
     * @return
     */
    public static Bitmap stringToBitmap(String picStrInMsg,int a) {
        Bitmap bitmap = null;

        byte[] imgByte = null;
        InputStream input = null;
        try {
            imgByte = Base64.decode(picStrInMsg, Base64.DEFAULT);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = a;
            input = new ByteArrayInputStream(imgByte);
            SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(input, null, options));
            bitmap = (Bitmap) softRef.get();
            ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (imgByte != null) {
                imgByte = null;
            }

            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

//
//        byte[] imgByte = Base64.decode(picStrInMsg, Base64.DEFAULT);
//
//        try {
//            bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
//            imgByte = null;
//        } catch (OutOfMemoryError e) {
//            e.printStackTrace();
//            try {
//                bitmap = BitmapFactory.decodeByteArray(imgByte, 0,
//                        imgByte.length);
//            } catch (OutOfMemoryError e1) {
//                e.printStackTrace();
//            } catch (Exception e1) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return bitmap;
    }
}
