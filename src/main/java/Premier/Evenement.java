package Premier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;

import Design_Pattern_logique_Metier.EvenementObservable;
import Design_Pattern_logique_Metier.NotificationService;

import Design_Pattern_logique_Metier.ParticipantObserver;
import Exception.CapaciteMaxAtteinteException;

import com.fasterxml.jackson.annotation.*;

// permet de différencier les sous classes dans le JSON
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
              property = "type")

// Déclarons les sous-classes autorisées(Concert et Conférence)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Conference.class,name="conference"),
        @JsonSubTypes.Type(value=Concert.class,name="concert")
})

public abstract class Evenement implements EvenementObservable {

    // Renommons les champs dans le JSON
    @JsonProperty("id")
    private String id;

    @JsonProperty("nom")
    private String nom;

    // Formatage explicite des dates
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

   @JsonProperty("lieu")
    private String lieu;
   @JsonProperty("capacity")
    private int capaciteMax;

   @JsonProperty("participants")
    private List<ParticipantObserver> observers = new ArrayList<ParticipantObserver>();// utile pour ajouter les paticipants

   @JsonIgnore
    private boolean estAnnule = false;
   @JsonProperty
   private Organisateur oganisateur;

    public Evenement(){};

    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }
    // Définitions des méthodes


    public List<ParticipantObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<ParticipantObserver> observers) {
        this.observers = observers;
    }

    public boolean isEstAnnule() {
        return estAnnule;
    }

    public Organisateur getOganisateur() {
        return oganisateur;
    }

    public void setOganisateur(Organisateur oganisateur) {
        this.oganisateur = oganisateur;
    }

    // Les méthodes:

    public abstract void afficherDetails();

    public void ajouterParticipant(ParticipantObserver observer) throws CapaciteMaxAtteinteException {
        if (observers.size() >= getCapaciteMax()) {
            throw new CapaciteMaxAtteinteException("La Capacité maximale de la salle est atteinte");
        } else {
            observers.add(observer);
            // pour les différentes liste du participant(observer)
            observer.getEvenementsDuParticipant().add(this);

        }
    }
// pour retirer un Observer
    public void retirerObserver(ParticipantObserver observer){
        observers.remove(observer);
    }

    /*pour annuler*/
    public void annuler(){
        this.estAnnule = true; // Marquer l'état d'annulation
        System.out.println("Evenement annulé : " + this.getNom());

    }

// Pour notifier
    public void notifierObservers(String message){
        for(ParticipantObserver o:observers)
            o.envoyerNotification(message);
    }



    /*pour modifier un evenement
    public void modifierDetails(String nouveauNom) {
        this.nom = nouveauNom;
        notifierObservers("MODIFICATION:" + getId() + ":Nouveau nom=" + nouveauNom);

    }*/



}