package cn.qianfg.util;

import java.io.*;

public class SerUtil {
    public static byte[] getByte(Object o) {
        byte[] bs = null;
        //对象输出流
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;
        bos = new ByteArrayOutputStream();
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            bs = bos.toByteArray();
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bs;
    }

    //反序列化
    public static Object getObject(byte[] bs) {
        Object o = null;
        ObjectInputStream ois = null;
        ByteArrayInputStream bis = null;
        bis = new ByteArrayInputStream(bs);
        try {
            ois = new ObjectInputStream(bis);
            o = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}
