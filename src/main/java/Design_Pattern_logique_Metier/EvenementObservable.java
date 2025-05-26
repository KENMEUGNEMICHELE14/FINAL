package Design_Pattern_logique_Metier;

import Premier.Evenement;
import Exception.CapaciteMaxAtteinteException;

import java.util.ArrayList;
import java.util.List;

public interface EvenementObservable {

    // Méthode pour s'abonner
    public void ajouterParticipant(ParticipantObserver observer) throws CapaciteMaxAtteinteException;
    // POur retirer
    public void retirerObserver(ParticipantObserver observer);

    // methode pour notifier tout le mondes
    public void notifierObservers(String message);
}
