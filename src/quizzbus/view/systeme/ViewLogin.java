package quizzbus.view.systeme;

import quizzbus.view.ManagerGui;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jfox.javafx.view.ControllerAbstract;

public class ViewLogin extends ControllerAbstract {

	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private TextField		txfPseudo;
	@FXML
	private PasswordField	pwfMotDePasse;
	
	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui		managerGui;
	@Inject
	private ModelConnexion	modelConnexion;
	@Inject
	private ModelInfo		modelInfo;
	
	//-------
	// Initialisations
	//-------
	
	@FXML
	private void initialize() {

		var draft = modelConnexion.getDraft();
		
		// Data binding
		bindBidirectional( txfPseudo, draft.pseudoProperty() );
		bindBidirectional( pwfMotDePasse, draft.motDePasseProperty() );

	}
	
	@Override
	public void refresh() {
		// Ferem la session si elle est ouverte
		if ( modelConnexion.getCompteActif() != null ) {
			modelConnexion.fermerSessionUtilisateur();
		}
	}

	//-------
	// Actions
	//-------
	
	@FXML
	private void doConnexion() {
		System.out.println("Login.doConnexion()");
		managerGui.execTask( () -> {
			modelConnexion.ouvrirSessionUtilisateur();
			Platform.runLater( () -> {
         			modelInfo.setTitre( "Bienvenue" );
        			modelInfo.setMessage( "Connexion réussie" );
        			managerGui.showView( ViewInfo.class );
            }) ;
		} );
	}

}
