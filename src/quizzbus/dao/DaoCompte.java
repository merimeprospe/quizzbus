package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import quizzbus.data.Compte;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoCompte extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM compte WHERE idcompte = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Compte compte ) throws SQLException {
		query.set( "pseudo",		compte.getPseudo() );
		query.set( "motdepasse",	compte.getMotDePasse() );
		query.set( "email",			compte.getEmail() );
		query.set( "flagadmin",		compte.isFlagAdmin() );
	}
	
	protected Compte build( Query query ) throws SQLException {
		var compte = new Compte();
		compte.setId(			query.get( "idcompte", Integer.class ) );
		compte.setPseudo(		query.get( "pseudo", String.class ) );
		compte.setMotDePasse(	query.get( "motdepasse", String.class ) );
		compte.setEmail(		query.get( "email", String.class ) );
		compte.setFlagAdmin(	query.get( "flagadmin", Boolean.class ) );
		return compte;
	}

	//-------
	// Actions
	//-------

	public void inserer( Compte compte )  {
		var query = createQuery( sqlDefault );
		query.insertRow( compte, this::setData, true );
		compte.setId( query.get( "idcompte", Integer.class ));
		query.close();
	}

	public void modifier( Compte compte )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, compte.getId() );
		query.updateRow( compte, this::setData );
	}

	public void supprimer( int idCompte )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idCompte );
		query.deleteRow();
	}

	public Compte retrouver( int idCompte )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idCompte );
		return query.getSingleResult( this::build );
	}

	public List<Compte> listerTout()   {
		var query = createQuery(  "SELECT * FROM compte ORDER BY pseudo" );
		return query.getResultList( this::build );
	}

	public Compte validerAuthentification( String pseudo, String motDePasse )  {
		var query = createQuery( "SELECT * FROM compte WHERE pseudo = ? AND motdepasse = ?" );
		query.setParam( 1, pseudo );
		query.setParam( 2, motDePasse);
		return query.getSingleResult( this::build );
	}

	public boolean verifierUnicitePseudo( String pseudo, Integer idCompte )   {
		var query = createQuery(  "SELECT COUNT(*) = 0 FROM compte WHERE pseudo = ? AND idcompte <> ?" );
		query.setParam( 1, pseudo );
		query.setParam( 2, idCompte);
		return query.getSingleResult( Boolean.class );
	}
	
}
