package quizzbus.view;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Menu;
import jfox.context.Dependent;
import jfox.javafx.control.MenuBarAbstract;
import quizzbus.report.ManagerReport;
import quizzbus.view.compte.ViewCompteCombo;
import quizzbus.view.compte.ViewCompteList;
import quizzbus.view.systeme.ViewLogin;
import quizzbus.view.systeme.ModelConnexion;
import quizzbus.view.systeme.ViewAbout;
import quizzbus.view.systeme.ViewConnexion;
import quizzbus.view.test.ViewTestDaoCompte;


@Dependent
public class MenuBarAppli extends MenuBarAbstract {

	
	//-------
	// Champs 
	//-------
	
	private final BooleanProperty flagConnexion	= new SimpleBooleanProperty();
	private final BooleanProperty flagRoleAdmin	= new SimpleBooleanProperty();
	
	@Inject
	private ManagerGui 		managerGui;
	@Inject
	private ModelConnexion	modelConnexion;	
	@Inject
	private ManagerReport 	managerReport;
	
	
	//-------
	// Initialisation
	//-------
	
	@PostConstruct
	public void init() {
		
		//-------
		// Variables de travail

		Menu menu;
		
		
		//-------
		// Menu Système
		
		menu = addMenu( "Système" );

		addMenuItem( "Se déconnecter", menu, flagConnexion,
				e -> managerGui.showView( ViewLogin.class ) );

		addMenuItem( "Quitter", menu, 
				e -> managerGui.exit() );
		
		
		//-------
		// Menu Données
		
		menu = addMenu( "Donnees", flagConnexion );
		
		addMenuItem( "Comptes (2 vues) ", menu, flagRoleAdmin, 
				e -> managerGui.showView( ViewCompteList.class ) );
		
		addMenuItem( "Comptes (1 vue) ", menu, flagRoleAdmin, 
				e -> managerGui.showView( ViewCompteCombo.class ) );

		
		//-------
		// Menu Tests
		
		menu = addMenu( "Test", flagRoleAdmin );
		
		addMenuItem( "DaoCompte", menu,
				e -> managerGui.showView( ViewTestDaoCompte.class ) );

		
		//-------
		// Menu Aide
		
		menu = addMenu( "?" );
		
		addMenuItem( "A propos", menu,
				e -> managerGui.showDialog( ViewAbout.class ) );

		
		//-------
		// Gestion des droits d'accès
		
		final var compteActif = modelConnexion.compteActifProperty();
		flagConnexion.bind( compteActif.isNotNull() );
		flagRoleAdmin.bind( Bindings.createBooleanBinding( () -> flagConnexion.get() && compteActif.get().isFlagAdmin(), compteActif ) );
		
	}
}
