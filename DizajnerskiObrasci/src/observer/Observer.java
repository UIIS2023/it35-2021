package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class Observer implements PropertyChangeListener {

	private DrawingFrame frame;

	public Observer(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

		if (arg0.getPropertyName().equals("btnToFront")) {
			frame.getBtnToFront().setEnabled((boolean) arg0.getNewValue());
		}
		if (arg0.getPropertyName().equals("btnToBack")) {
			frame.getBtnToBack().setEnabled((boolean) arg0.getNewValue());
		}
		if (arg0.getPropertyName().equals("btnBringToFront")) {
			frame.getBtnBringToFront().setEnabled((boolean) arg0.getNewValue());
		}
		if (arg0.getPropertyName().equals("btnBringToBack")) {
			frame.getBtnBringToBack().setEnabled((boolean) arg0.getNewValue());
		}
		if (arg0.getPropertyName().equals("tglbtnDelete")) {
			frame.getTglbtnDelete().setEnabled((boolean) arg0.getNewValue());
		}
		if (arg0.getPropertyName().equals("tglbtnModify")) {
			System.out.println("Observeeer");
			frame.getTglbtnModify().setEnabled((boolean) arg0.getNewValue());
		}

	}

}
