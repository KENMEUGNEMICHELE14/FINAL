package Design_Pattern_logique_Metier;

import Premier.Evenement;
import Premier.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantObserver implements NotificationService{
private String email;
private List<EvenementObservable> evenementsDuParticipant=new ArrayList<EvenementObservable>();
private NotificationService notificationService = this;
    public ParticipantObserver(String email) {
        this.email = email;
    }

    public List<EvenementObservable> getEvenementsDuParticipant() {
        return evenementsDuParticipant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void envoyerNotification(String message) {
        System.out.println("Notification pour "+ email + ":" +message);

    }
}
