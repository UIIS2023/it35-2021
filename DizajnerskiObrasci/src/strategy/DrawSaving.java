package strategy;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import mvc.DrawingFrame;

public class DrawSaving implements SaveOpenFile {
	
	private DrawingFrame frame;
	
	public DrawSaving(DrawingFrame frame) {
		this.frame=frame;
	}

	@Override
	public void save(File file) {
		BufferedImage buffer=null;
		try {
			buffer= new Robot().createScreenCapture(frame.getView().getBounds());
			frame.getView().paint(buffer.createGraphics());
			ImageIO.write(buffer, "jpeg",new File(file+".jpeg"));
		}catch (Exception e) {
			 System.out.println(e.getMessage());
			
		}
		
	}

	@Override
	public void open(File file) {
		// TODO Auto-generated method stub
		
	}

}
