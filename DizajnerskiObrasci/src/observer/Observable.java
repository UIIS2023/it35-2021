package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Observable {
	private boolean btnModifyActivate;
	private boolean btnDeleteActive;
	private boolean btnForwardActivate;
	private boolean btnBackwardActivate;
	private boolean btnToFrontActivate;
	private boolean btnToBackActivate;

	private PropertyChangeSupport propertyChangeSupport;

	public Observable() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

	public void setModifyBtnActivated(boolean btnModifyActivate) {
		propertyChangeSupport.firePropertyChange("tglbtnModify", this.btnModifyActivate, btnModifyActivate);
		this.btnModifyActivate = btnModifyActivate;
	}

	public void setDeleteBtnActivated(boolean btnDeleteActive) {
		propertyChangeSupport.firePropertyChange("tglbtnDelete", this.btnDeleteActive, btnDeleteActive);
		this.btnDeleteActive = btnDeleteActive;
	}

	public void setBtnActivatedForward(boolean btnForwardActivate) {
		propertyChangeSupport.firePropertyChange("btnToFront", this.btnForwardActivate, btnForwardActivate);
		this.btnForwardActivate = btnForwardActivate;
	}

	public void setBtnActivatedToFront(boolean btnToFrontActivate) {
		propertyChangeSupport.firePropertyChange("btnBringToFront", this.btnToFrontActivate, btnToFrontActivate);
		this.btnToFrontActivate = btnToFrontActivate;
	}

	public void setBtnActivatedBackward(boolean btnBackwardActivate) {
		propertyChangeSupport.firePropertyChange("btnToBack", this.btnBackwardActivate, btnBackwardActivate);
		this.btnBackwardActivate = btnBackwardActivate;
	}

	public void setBtnActivatedToBack(boolean btnToBackActivate) {
		propertyChangeSupport.firePropertyChange("btnBringToBack", this.btnToBackActivate, btnToBackActivate);
		this.btnToBackActivate = btnToBackActivate;
	}

}
