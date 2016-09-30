package main.java;

import sun.security.ssl.Debug;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Vector;

/**
 * Created by arsen on 20.08.2016.
 */
public class File {

    byte[] serialize(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(out);
            os.writeObject(obj);
            os.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return out.toByteArray();
    }

    Object deserialize(byte[] data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;

    }

    public <T> T Read(String name) {
        FileInputStream fis;
        java.io.File f = new java.io.File(name);
        if (f != null) {
            byte[] b = new byte[(int) f.length()];
            try {
                fis = new FileInputStream(name);
                fis.read(b);
                return (T) deserialize(b);
            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }

        }
        return null;
    }

    public <T> T Read(String name, int index) {
        int skip = 82;
        FileInputStream fis;
        long posH = 0, posL = 0;
        byte[] b = new byte[skip];
        T ret = null;
        try {
            fis = new FileInputStream(name);
            fis.getChannel().position(index * skip);
            fis.read(b);
            posL = (long) deserialize(b);
            fis.read(b);
            posH = (long) deserialize(b);
            fis.getChannel().position(posL);
            b = new byte[(int) (posH - posL)];
            //fis.skip(index*skip);
            fis.read(b);
            ret = (T) deserialize(b);
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return ret;
    }

    public <T> void Write(T obj, String name) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(name);
            fos.write(serialize(obj));
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public <T> void Write(T[] objects, String name) {
        int skip = 82;
        FileOutputStream fos;
        long[] arrPos = new long[objects.length + 1];

        try {
            fos = new FileOutputStream(name);


            fos.getChannel().position((objects.length + 1) * skip);
            for (int i = 0; i < objects.length; i++) {
                arrPos[i] = fos.getChannel().position();
                fos.write(serialize(objects[i]));

            }
            arrPos[objects.length] = fos.getChannel().position();
            fos.getChannel().position(0);
            for (int i = 0; i < arrPos.length; i++) {
                fos.write(serialize(arrPos[i]));
                System.out.println("position " + arrPos[i]);
            }
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {

            System.out.println(e);
            //pos= fos.getChannel().position();

        } catch (IOException e) {
            System.out.println(e);
        }


    }


}
