package quizzbus.view.compte;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.ControllerAbstract;
import jfox.javafx.view.Mode;
import quizzbus.data.Compte;
import quizzbus.view.ManagerGui;

public class ViewCompteList extends ControllerAbstract {
	
	//-------
	// Composants de la vue
	//-------

	@FXML
	private ListView<Compte>	lsvComptes;
	@FXML
	private Button				btnModifier;
	@FXML
	private Button				btnSupprimer;

	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui			managerGui;
	@Inject
	private ModelCompte		modelCompte;
	
	//-------
	// Initialisation du Controller
	//-------

	@FXML
	private void initialize() {

		// ListView
		lsvComptes.setItems( modelCompte.getList() );
		UtilFX.setCellFactory( lsvComptes, "pseudo" );
		bindBidirectional( lsvComptes, modelCompte.currentProperty(), modelCompte.flagRefreshingListProperty() );
		
		// Configuraiton des boutons
		lsvComptes.getSelectionModel().selectedItemProperty().addListener(obs -> {
			configurerBoutons();
		});
		configurerBoutons();
		
	}
	
	@Override
	public void refresh() {
		modelCompte.refreshList();
		lsvComptes.requestFocus();
	}
	
	//-------
	// Actions
	//-------
	
	@FXML
	private void doAjouter() {
		modelCompte.initDraft( Mode.NEW );
		managerGui.showView( ViewCompteForm.class );
	}

	@FXML
	private void doModifier() {
		modelCompte.initDraft( Mode.EDIT );
		managerGui.showView( ViewCompteForm.class );
	}

	@FXML
	private void doSupprimer() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelCompte.deleteCurrent();
			refresh();
		}
	}
	
	//-------
	// Gestion des évènements
	//-------

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( lsvComptes.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doModifier();
				}
			}
		}
	}
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	private void configurerBoutons() {
		var flagDisable = lsvComptes.getSelectionModel().getSelectedItem() == null;
		btnModifier.setDisable(flagDisable);
		btnSupprimer.setDisable(flagDisable);
	}

}
