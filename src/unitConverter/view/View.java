package unitConverter.view;
import unitConverter.model.Model;
import unitConverter.controller.Controller;

public interface View {
	Controller getController();
	void setController(Controller controller);
	Model getModel();
	void setModel(Model model);
}
