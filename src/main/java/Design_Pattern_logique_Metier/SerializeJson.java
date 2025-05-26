package Design_Pattern_logique_Metier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SerializeJson {
    private final ObjectMapper mapper;
    // final car sa valeur ne change pas après l'initialisation
    //ObjetcMapper est la classe pricipale de Jackson pour la sérialisation

    public SerializeJson() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());// Active le support des dates
        mapper.enable(SerializationFeature.INDENT_OUTPUT);// rend Jason lisible

    }

    // Méthode pour sauvegarder les Objet dans un fichier Json
    public <T> void sauvegarderObjet(T objet, String cheminFichier) throws Exception {
        mapper.writeValue(new File(cheminFichier), objet);

    }
    // writeValue(): convertit l'objet en JSON et l'écrit dans le fichier
    // On utilise T pour tout type d'objet

    // Méthode pour sauvegarder une liste
    public <T> void sauvegarderListe(List<T> objets, String cheminFichier) throws Exception {
        mapper.writeValue(new File(cheminFichier), objets);

    }

    // Méthode pour charger un Objet depuis un ficheir JSON

    public <T> T chargerObjet(String cheminFichier, Class<T> classe) throws Exception {
        return mapper.readValue(new File(cheminFichier),classe);

    }
    // classe spécifie le type d'Objet à créer


    // Méthode pour charger une liste

    public <T> List <T> chargerListe(String cheminFichier,Class <T> classe) throws Exception{
        return mapper.readValue(
                new File(cheminFichier),
                mapper.getTypeFactory().constructCollectionType(List.class, classe)
        );
    }
    // constructCollectionTpype() spécifie que le JSON contient une lsite d'objet de type class contient


    // Nouvelle méthode pour ajouter à un fichier existant
    public <T> void ajouterAListe(T element, String cheminFichier, Class<T> classe) throws Exception {
        List<T> existants = new ArrayList<>();

        // Lire le fichier existant s'il y en a un
        if (Files.exists(Paths.get(cheminFichier))) {
            existants = chargerListe(cheminFichier, classe);
        }

        // Ajouter le nouvel élément
        existants.add(element);

        // Réécrire le fichier complet
        sauvegarderListe(existants, cheminFichier);
    }
}

