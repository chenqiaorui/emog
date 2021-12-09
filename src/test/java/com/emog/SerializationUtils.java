package com.emog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationUtils {
	private static String FILE_NAME="C:\\Users\\admin\\code\\test.txt";
	
	public static void write(Serializable s) {
		try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            objectOutputStream.writeObject(s);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	//反序列化 读的过程
    public static Object read(){
        Object obj=null;
        // 反序列化
        try {
            ObjectInput input = new ObjectInputStream(new FileInputStream(FILE_NAME));
            obj = input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
