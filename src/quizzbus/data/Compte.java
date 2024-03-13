package quizzbus.data;

import java.util.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Compte  {
	
	//-------
	// Données observables
	//-------
	
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			pseudo		= new SimpleStringProperty();
	private final StringProperty			motDePasse	= new SimpleStringProperty();
	private final StringProperty			email 		= new SimpleStringProperty();
	private final BooleanProperty			flagAdmin	= new SimpleBooleanProperty();
	
	//-------
	// Getters & Setters
	//-------

	public final ObjectProperty<Integer> idProperty() {
		return this.id;
	}

	public final Integer getId() {
		return this.idProperty().get();
	}

	public final void setId(final Integer id) {
		this.idProperty().set(id);
	}

	public final StringProperty pseudoProperty() {
		return this.pseudo;
	}

	public final String getPseudo() {
		return this.pseudoProperty().get();
	}

	public final void setPseudo(final String pseudo) {
		this.pseudoProperty().set(pseudo);
	}

	public final StringProperty motDePasseProperty() {
		return this.motDePasse;
	}

	public final String getMotDePasse() {
		return this.motDePasseProperty().get();
	}

	public final void setMotDePasse(final String motDePasse) {
		this.motDePasseProperty().set(motDePasse);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	public final BooleanProperty flagAdminProperty() {
		return this.flagAdmin;
	}

	public final boolean isFlagAdmin() {
		return this.flagAdminProperty().get();
	}

	public final void setFlagAdmin(final boolean flagAdmin) {
		this.flagAdminProperty().set(flagAdmin);
	}
	
	//-------
	// hashCode() & equals()
	//-------

	@Override
	public int hashCode() {
		return Objects.hash(id.get() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(id.get(), other.id.get() );
	}
	
}
