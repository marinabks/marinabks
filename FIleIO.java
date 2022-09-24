/**
 * 
 */
package chapter5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author maris
 *
 */
public class FIleIO {
	private File file;
	/**
	 * 
	 */
	public FIleIO() {
		this.file = new File("TestFoler/textfile.txt");
	}
	public FIleIO(String fileName) {
		//C:\Users\mbakkos\Desktop\TestFolder
		//Absolute file path
		this.file = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "TestFolder" +
				File.separator + fileName);
		//C:\Users\mbakkos\Desktop\TestFolder\testfile.txt
	}
	@Override
	public String toString() {
		return "FileUtilities [file=" + file + "]";
	}
	public void createFile() /*throws IOException*/ {
		boolean fileCreated = false;
		while (!fileCreated) {
			try {
				if (this.file.createNewFile()) {
					System.out.println("Created file = " + file.getName());
				}
				else {
					System.out.println("File " + file.getName() + " already exists.");
				}
				fileCreated = true;
			} 
			catch (IOException e) {
				e.printStackTrace();
				if(file.getParentFile().mkdir()) {
					//file.getParentFile() => \Users\mbakkos\Desktop\TestFolder
					System.out.println("Created folder = " + file.getParentFile().getName());
				}
				else {
					System.out.println("Folder " + file.getParentFile().getName() + " already exists.");
				}
			}
		}
		System.out.println("Last modified = " + new Date(file.lastModified()));
	}
	public void /*ArrayList<Integer>*/ writeFile() {
		PrintWriter fileOut = null;
		try {
			//fileOut = new PrintWriter(file)
			fileOut = new PrintWriter(new FileWriter(file, true));
			ArrayList<Integer> list = new ArrayList<>();
			for(int i = 0; i < 20; i++) {
				list.add((int)(Math.random()*100));
			}
			System.out.println(list);
//			fileOut.println(list);
			for(int n:list) {
				fileOut.println(n);
			}
			//return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//Conditional is required to prevent a NullPointerException if there is a FileNotFoundException generated by the
			//PrintWriter.
			if(fileOut!=null)
				fileOut.close();
			System.out.println("Last modified = " + new Date(file.lastModified()));
			System.out.println("File size = " + file.length() + " bytes");
		}
		//return null;
	}
	public void readFile() {
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(file);
			while(fileIn.hasNext()) {
				String line = fileIn.nextLine();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(fileIn != null)
				fileIn.close();
		}
	}
	public void writeFileBinary() {
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(file);
			ArrayList<Integer> list = new ArrayList<>();
			for(int i = 0; i < 20; i++) {
				list.add((int)(Math.random()*100));
			}
			System.out.println(list);
			for(int n:list) {
				//Write only the lowest order byte of the int
				fileOut.write(n);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//Conditional is required to prevent a NullPointerException if there is a FileNotFoundException generated by the
			//PrintWriter.
			if(fileOut!=null)
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			System.out.println("Last modified = " + new Date(file.lastModified()));
			System.out.println("File size = " + file.length() + " bytes");
		}
	}
	public void readFileBinary() {
		FileInputStream fileIn = null;
		int byteRead = 0;
		try {
			fileIn = new FileInputStream(file);
			while((byteRead = fileIn.read()) != -1) {
				System.out.println(byteRead);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fileIn != null)
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
