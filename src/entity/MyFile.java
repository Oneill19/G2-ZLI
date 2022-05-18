package entity;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("serial")
public class MyFile extends File implements Serializable {
	
	private String Description, fileName;
	private int size=0;
	
	//
	public  byte[] mybytearray;
	
	public void initArray(int size)
	{
		mybytearray = new byte [size];	
	}
	
	public MyFile( String fileName) {
		super(fileName);
	}
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public byte[] getMybytearray() {
		return mybytearray;
	}
	
	public byte getMybytearray(int i) {
		return mybytearray[i];
	}

	public void setMybytearray(byte[] mybytearray) {
		
		for(int i=0;i<mybytearray.length;i++)
		this.mybytearray[i] = mybytearray[i];
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "MyFile [Description=" + Description + ", fileName=" + fileName + ", size=" + size + ", mybytearray="
				+ Arrays.toString(mybytearray) + "]";
	}	
	
	
}

