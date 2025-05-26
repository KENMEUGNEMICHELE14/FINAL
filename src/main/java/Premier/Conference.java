package Premier;

import Design_Pattern_logique_Metier.ParticipantObserver;
import com.fasterxml.jackson.annotation.*;
import Exception.CapaciteMaxAtteinteException;

import java.time.LocalDateTime;

@JsonTypeName("conference")
public  class Conference extends Evenement {

    @JsonProperty("theme")
    private String theme;
   /* @JsonProperty("participants")
    private List<Participant> participants;
*/
    
    @JsonCreator
    public Conference(
            @JsonProperty("id") String id,
            @JsonProperty("nom") String nom,
            @JsonProperty("lieu") String lieu,
            @JsonProperty("capacite") int capaciteMax,
            //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
            @JsonProperty("date") LocalDateTime date ){
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
        /*this.participants= participants;*/
    }

    public String getTheme(){
        return theme;
    }
    public void setTheme(){
        this.theme=theme;
    }


     @Override
     public void ajouterParticipant(ParticipantObserver observer) throws CapaciteMaxAtteinteException {
         super.ajouterParticipant(observer);
     }



    @Override
    public void afficherDetails() {
        System.out.println("Les détails de la conférences:");
        System.out.println("Nom:"+getNom());
        System.out.println("Theme:"+ theme);
        System.out.println("Lieu:"+getLieu());
        System.out.println("Date:"+getDate());
        System.out.println("Capacité"+ getCapaciteMax());
        System.out.println("Statut: " + (isEstAnnule() ? "ANNULÉ" : "ACTIF"));

// on doit ajouté si l'evement est annulé ou pas
        // je crois que intervenant doit aussi intervenir

    }


    @Override
    public void annuler() {
        super.annuler();// appele la méthode parente
        notifierObservers("CONFERENCE ANNULÉE: " + getNom());


    }
     @Override
     public void notifierObservers(String message){
         super.notifierObservers(message);
     }

     @Override
     public void retirerObserver(ParticipantObserver observer) {
         super.retirerObserver(observer);
     }

}