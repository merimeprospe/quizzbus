package quizzbus.report;

import jfox.jasperreports.IEnumReport;

public enum EnumReport implements IEnumReport {

	// -------
	// Valeurs
	// -------

	Exemple				( "personne/Exemple.jrxml" ),
	;

	// -------
	// Champs
	// -------

	private String path;

	// -------
	// Constructeur
	// -------

	EnumReport(String path) {
		this.path = path;
	}

	// -------
	// Getters & setters
	// -------

	@Override
	public String getPath() {
		return path;
	}

}
