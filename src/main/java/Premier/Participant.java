package Premier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    @JsonProperty("id")
    private  String id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("email")
    private String email;
   /* private List<Evenement> evenementsDuParticipants=new ArrayList<Evenement>();*/

    // le constructeur;
    @JsonCreator
    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public List<Evenement> getEvenementsDuParticipants() {
        return evenementsDuParticipants;
    }*/
}
