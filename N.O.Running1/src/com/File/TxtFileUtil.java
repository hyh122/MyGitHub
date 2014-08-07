package com.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 
 * @author nyc
 * 
 *         用于读写txt文件的工具类
 */
public class TxtFileUtil {

	/**
	 * 创建文件
	 * 
	 * @param txtFile
	 * @return
	 */
	public static boolean createFile(File txtFile) throws Exception {
		boolean flag = false;
		try {
			if (!txtFile.exists()) {
				txtFile.createNewFile();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 读TXT文件内容
	 * 
	 * @param txtFile
	 * @return
	 */
	public static String readTxtFile(File txtFile) throws Exception {
		String result = "";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(txtFile);
			bufferedReader = new BufferedReader(fileReader);
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					if(!read.equals("\r\n")){
					result = result + read + "\r\n";
					}
					else{
						result=result+read;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		return result;
	}

	public static boolean compareFiles(File srcFile, File desFile) {

		return getFileMD5(srcFile).equals(getFileMD5(desFile));
		
	}
	
	private static String getFileMD5(File file) {
	    if (!file.isFile()){
	      return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in=null;
	    byte buffer[] = new byte[1024];
	    int len;
	    try {
	      digest = MessageDigest.getInstance("MD5");
	      in = new FileInputStream(file);
	      while ((len = in.read(buffer, 0, 1024)) != -1) {
	        digest.update(buffer, 0, len);
	      }
	      in.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	    BigInteger bigInt = new BigInteger(1, digest.digest());
	    return bigInt.toString(16);
	  }
	
	/**
	 * 追加到内容到原文件尾部
	 * 
	 * @param txtFile
	 */

	public static boolean appendToFile(String content, File txtFile) {
		boolean append = false;
		boolean result = false;

		try {
			if (txtFile.exists())
				append = true;
			FileWriter fw = new FileWriter(txtFile, append);// 同时创建新文件
			// 创建字符输出流对象
			BufferedWriter bf = new BufferedWriter(fw);
			// 创建缓冲字符输出流对象
			bf.append(content);
			result = true;
			bf.flush();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将内容写到TXT文件，覆盖原来内容
	 * 
	 * @param content
	 *            ：写入的符串
	 * @param txtFile
	 *            ：文本文件
	 * @return： 是否写入成功
	 * @throws Exception
	 *             ：抛出异常
	 */
	public static boolean writeTxtFile(String content, File txtFile)
			throws Exception {
		// RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream outStream = null;
		try {

			outStream = new FileOutputStream(txtFile);
			if (txtFile.exists()) {
				txtFile.delete();
			}

			outStream.write((new String("")).getBytes());
			outStream.flush();
			outStream.write(content.getBytes("utf8"));
			outStream.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void copyFile(File frmFile, File toFile) {

		String content = "";
		try {
			content = readTxtFile(frmFile);
			if (toFile.exists()) {
				toFile.delete();
			}
			writeTxtFile(content, toFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
