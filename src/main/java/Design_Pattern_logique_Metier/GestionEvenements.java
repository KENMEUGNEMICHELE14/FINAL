package Design_Pattern_logique_Metier;

import Premier.*;
import Exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class GestionEvenements {
    private static GestionEvenements instance;// instance unique du singleton
    protected Map<String, Evenement> evenements;// collection d'evenements
    private SerializeJson serializer;// c'est un nouvel attribut

    // POur le constructeur privé
    private GestionEvenements() {
        evenements = new HashMap<>();//
// cela signifie que lorsqu'on cree l'unique instance de GestionEvenement, on prépare un espace mémoire pour stocker les évenemnts
        serializer=new SerializeJson();// initialisation
    }

    // L'accès à l'instance unique
    public static GestionEvenements getInstance() {
        if (instance == null) {
            instance = new GestionEvenements();
        }
        return instance;
    }
    public Map<String, Evenement> getEvenements() {
        return evenements;
    }

    // ajouter un evenement
    public void ajouterEvenement(Evenement e) throws EvenementDejaExistantException {
        if(evenements.containsKey(e.getId())){
            throw new EvenementDejaExistantException(("L'évenement  avec l'ID "+e.getId()+"existe délà "));
        }else{
        evenements.put(e.getId(),e);
        }
    }

    // suprimer un evenement
    public void supprimerEvenement(String id){

        evenements.remove(id);
    }

    // Rechercher un evenement
    public Evenement rechercherEvenement(String id){

        return evenements.get(id);
    }
    // Méthode pour sauvgarder tous les évenements dans le fichier JSON
    public void sauvegarderEvenement(String cheminFichier){
        try{
            serializer.sauvegarderListe(new ArrayList<>(evenements.values()),cheminFichier);
            System.out.println("Evenement Sauvegardé");
        } catch (Exception e){
            System.err.println("Erreur de sauvegarde");
        }
    }

    // Méthode de chargmeenet
  /*  public Evenement chargerEvenementUnique(String cheminFichier, String id) throws Exception {
                   List<Evenement> evenementsCharges =serializer.chargerListe(cheminFichier,Evenement.class);
            return evenementsCharges.stream()
                    .filter(evenement -> evenement.getId().equals(id))
                    .findFirst()
                    .orElse(null);

    }*/

    // charger plusieurs evenemtns
    public void chargerEvenements(String cheminFichier) {
        try {
            List<Evenement> evenementsCharges = serializer.chargerListe(cheminFichier, Evenement.class);
            evenements.clear(); // Vide la map actuelle
            evenementsCharges.forEach(e -> evenements.put(e.getId(), e)); // Remplit avec les nouveaux
            System.out.println("Événements chargés depuis " + cheminFichier);
        } catch (Exception e) {
            System.err.println(" Erreur de chargement : " + e.getMessage());
        }
    }
}