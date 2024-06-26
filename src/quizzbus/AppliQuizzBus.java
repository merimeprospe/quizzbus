package quizzbus;

import java.io.InputStream;
import java.util.logging.LogManager;

import org.mapstruct.factory.Mappers;

import quizzbus.commun.IMapper;
import quizzbus.view.ManagerGui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jfox.context.ContextGlobal;
import jfox.context.IContext;
import jfox.javafx.util.UtilFX;
import jfox.javafx.util.converter.ConverterLocalDate;
import jfox.javafx.util.converter.ConverterLocalDateTime;
import jfox.jdbc.DataSourceSingleConnection;

public class AppliQuizzBus extends Application {
	
	//-------
	// Champs
	//-------
	
	private IContext context;
	
	//-------
	// Actions
	//-------
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		configureLogging();
	}
	
	@Override
	public final void start(Stage stagePrimary) {

		try {
			
			// Formats par défaut
			ConverterLocalDate.setPatternDefault( "dd/MM/yyyy" );
			ConverterLocalDateTime.setPatternDefault( "dd/MM/yyyy hh:mm" );

			// JDBC - DataSource
			var dataSource = new DataSourceSingleConnection( 
					UtilFX.getInputStram( "classpath:/META-INF/jdbc.properties" ) );
			
			// Context
			context = new ContextGlobal();
			context.addBean( dataSource );
			context.addBean( Mappers.getMapper( IMapper.class ) );

			// ManagerGui
	    	ManagerGui managerGui = context.getBean( ManagerGui.class );
	    	managerGui.setFactoryController( context::getBean );
	    	managerGui.setApplication( this );
			managerGui.setStage( stagePrimary );
			managerGui.configureStage();
			
			// Affiche le stage
			stagePrimary.show();
			
		} catch(Exception e) {
			UtilFX.unwrapException(e).printStackTrace();
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setHeaderText( "Impossible de démarrer l'application." );
	        alert.showAndWait();
	        Platform.exit();
		}

	}
	
	@Override
	public final void stop() throws Exception {
		if (context != null ) {
			context.close();
		}
	}
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	private void configureLogging() {

		try {
			InputStream in = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/logging.properties");
			if( in != null ) {
				LogManager.getLogManager().readConfiguration( in );
				in.close();
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}		
	}

}
