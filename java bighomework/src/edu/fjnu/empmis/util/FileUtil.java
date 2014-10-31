package edu.fjnu.empmis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.imageio.stream.FileImageInputStream;

import edu.fjnu.empmis.domain.Employee;


public class FileUtil {

	/**
	 * 
	 * @param file
	 *            文件名
	 * @param message
	 *            要存入文件的信息
	 * @throws IOException
	 *             异常抛出
	 */
	public void writeToFile(File file, String... message) throws IOException {
		PrintStream printStream=null;
		// true表示可以追加文件的内容而不会被覆盖
		OutputStream fileOutPutStream = new FileOutputStream(file, true);
		//定义一个打印流
		printStream=new PrintStream(fileOutPutStream);
		printStream.println();
		for (int i = 0; i < message.length; i++) {
			printStream.print(message[i]);
			if(i!=message.length-1){
				printStream.print(":");
			}
		}

		printStream.close();

	}

	/**
	 * 
	 * @param file文件名
	 * 
	 * @return 返回一个String类型的值
	 * @throws IOException
	 */
	public String readFromFile(File file) throws IOException {
		InputStream fileInputStream = new FileInputStream(file);
		int length = (int) file.length();
		byte[] b = new byte[length];

		fileInputStream.read(b);
		String str = new String(b);
		/**
		 * 关闭输入流
		 */
		fileInputStream.close();
		return str;
	}

	/**
	 * 将文件读取出来的信息按行保存在一个List集合中
	 * 
	 * @param file
	 *            文件名
	 * @return
	 * @throws IOException
	 */
	public List<String> readFileByLine(File file) throws IOException {
		List<String> strs = new ArrayList<String>();
		InputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(
				fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String temp = null;
		while ((temp = bufferedReader.readLine()) != null) {
			if(temp!=""){
			strs.add(temp);
			}
			

		}

		return strs;

	}

}
