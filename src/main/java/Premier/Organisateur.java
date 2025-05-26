package Premier;

import java.util.ArrayList;
import java.util.List;
import Exception.EvenementDejaExistantException;

public class Organisateur extends Participant {
    private List<Evenement> evenementsOrganises;;

    public Organisateur(String id, String nom, String email, List<Evenement> evenementsOrganises) {
        super(id, nom, email);
        this.evenementsOrganises = new ArrayList<Evenement>();
    }

    public List<Evenement> getEvenementsOrganises() {
        return evenementsOrganises;
    }

    public void setEvenementsOrganises(List<Evenement> evenementsOrganises) {
        this.evenementsOrganises = evenementsOrganises;
    }
// POur créer un Evenement
    public void creerEvenement(Evenement e) throws EvenementDejaExistantException {
        if(evenementsOrganises.contains(e))
            throw new EvenementDejaExistantException("Evenement existe déjà");
        evenementsOrganises.add(e);
        e.setOganisateur(this);

    }

}

