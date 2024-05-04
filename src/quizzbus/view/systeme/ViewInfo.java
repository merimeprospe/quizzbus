package quizzbus.view.systeme;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;

public class ViewInfo extends ControllerAbstract {
	
	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private Label		labTitre;
	@FXML
	private Label		labMessage;
	@Inject
	private ManagerGui 		managerGui;
	
	//-------
	// Autres champs
	//-------
	
	@Inject
	private ModelInfo	modelInfo;
	
	//-------
	// Initialisations
	//-------
	
	@FXML
	private void initialize() {
		
		// Data binding
		//bind( labTitre, modelInfo.titreProperty() );
		//bind( labMessage, modelInfo.messageProperty() );
		
	}

	@FXML
	public void déconnection() {
	    //System.out.println("L'image a été cliquée !");
	    managerGui.showView( ViewLogin.class );
	}
	@FXML
	public void Dashboard() {
	    System.out.println("L'image a été cliquée !");
	    managerGui.showView( ViewInfo.class );
	}
}
