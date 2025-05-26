package Premier;

import Design_Pattern_logique_Metier.ParticipantObserver;
import com.fasterxml.jackson.annotation.*;

import java.time.LocalDateTime;
import Exception.CapaciteMaxAtteinteException;

@JsonTypeName("concert")
public  class Concert extends Evenement {

    @JsonProperty("artiste")
    private String artiste;
    @JsonProperty("genreMusical")
    private String genreMusical;

   // public Concert(){super();}
    @JsonCreator
    public Concert(@JsonProperty ("id") String id,
                   @JsonProperty("nom") String nom,
                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                   @JsonProperty("lieu") String lieu,
                   @JsonProperty("capacite") int capaciteMax,
                   @JsonProperty("artiste") String artiste,
                   @JsonProperty("genreMusical") String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }


    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(String genreMusical) {
        this.genreMusical = genreMusical;
    }


    @Override
    public void ajouterParticipant(ParticipantObserver observer) throws CapaciteMaxAtteinteException {
        super.ajouterParticipant(observer);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Les détails du concert:");
        System.out.println("Nom:" + getNom());
        System.out.println("Artiste:" + artiste);
        System.out.println("Genre musical:" + genreMusical);
        System.out.println("Lieu:" + getLieu());
        System.out.println("Date:" + getDate());
        System.out.println("Capacité maximale" + getCapaciteMax());
        System.out.println("Statut: " + (isEstAnnule() ? "ANNULÉ" : "ACTIF"));
// on doit ajouté si l'evement est annulé ou pas
    }


    @Override
    public void annuler() {
        super.annuler();
        notifierObservers("CONCERT ANNULÉE: " + getNom());

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