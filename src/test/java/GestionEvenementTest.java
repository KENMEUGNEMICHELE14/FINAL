import Design_Pattern_logique_Metier.GestionEvenements;
import Premier.Concert;
import Premier.Conference;
import Premier.Evenement;
import Premier.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




public class GestionEvenementTest {

    /*  @BeforeEach
        void init() {
            // On réinitialise l'instance avant chaque test
            gestion = GestionEvenements.getInstance();
            gestion.getEvenements().clear(); // vide la map pour éviter les doublons
        }

        @Test
        void testAjouterEvenement() {
            Evenement event = new Conference(
                    "C001",
                    "Conférence Test",
                    LocalDateTime.of(2025, 6, 10, 14, 0),
                    "Amphi A",
                    100,
                    "Informatique",
                    List.of() // pas d'intervenants
            );

            assertDoesNotThrow(() -> gestion.ajouterEvenement(event));
            assertTrue(gestion.getEvenements().containsKey("C001"));
            assertEquals("Conférence Test", gestion.getEvenements().get("C001").getNom());
        }
    }*/

    // Pour faire la sauvegarde je fais dabord la sérilisation, De même pour charger

    @Test
    void testSauvegarderConcert() throws Exception {
        // 1) Créons un evenement
        Concert concert1 = new Concert(
                "c1",
                "Concert de Gospel",
                LocalDateTime.of(2025, 5, 25, 20, 0),
                "Paris",
                20,
                "Morijah",
                "Gospel"

        );
        //2) ajoutons l'evenemt
        GestionEvenements gestion = GestionEvenements.getInstance();
        gestion.ajouterEvenement(concert1);
        // 3- Sauvegarder dans le fichier JSON
        String cheminFichier = "concert.json";
        gestion.sauvegarderEvenement(cheminFichier);

        //4) Vérifier que le fichier est crée et n'est pas vide
        File fichier = new File(cheminFichier);
        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);

    }

    @Test
    void testSauvegarderConference() throws Exception {
        // 1) Créons un evenement
        Conference conference1 = new Conference(
                "conf1",
                "Conference de Recyclahe",
                "Yaoundé",
                5,
                LocalDateTime.of(2025, 4, 5, 9, 30)

        );
        //2) ajoutons l'evenemt
        GestionEvenements gestion = GestionEvenements.getInstance();
        gestion.ajouterEvenement(conference1);
        // 3- Sauvegarder dans le fichier JSON
        String cheminFichier = "conference.json";
        gestion.sauvegarderEvenement(cheminFichier);

        //4) Vérifier que le fichier est crée et n'est pas vide
        File fichier = new File(cheminFichier);
        assertTrue(fichier.exists());
        assertTrue(fichier.length() > 0);

    }


}