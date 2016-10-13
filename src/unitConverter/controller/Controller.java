package unitConverter.controller;
import unitConverter.model.Model;
import unitConverter.view.View;

public interface Controller {
	void setModel(Model model);
	Model getModel();
	View getView();
	void setView(View view);
}
