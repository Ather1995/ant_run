package com.beancore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {
    public static String readFileToString(String filePath) throws IOException {
	StringBuilder sb = new StringBuilder();
	File file = new File(filePath);
	BufferedReader br = new BufferedReader(new FileReader(file));
	String line = null;
	while ((line = br.readLine()) != null) {
	    sb.append(new String(line.getBytes(),"utf-8")).append("\r\n");
	}
	br.close();
	return sb.toString();
    }

}