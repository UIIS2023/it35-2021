package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class FileSerializable implements SaveOpenFile {
	

	private DrawingModel model;
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	
	
	public FileSerializable(DrawingModel model) {
		this.model=model;
	}
	
	@Override
	public void save(File file) {
		try {
			fileOutputStream = new FileOutputStream(file + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(model.getAll());
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}



	@Override
	public void open(File file) {
		try {
			fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			model.addMultiple((ArrayList<Shape>)objectInputStream.readObject());
	        objectInputStream.close();
	        fileInputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}



	
	

	
	

	
	
}
