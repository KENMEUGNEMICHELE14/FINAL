import Design_Pattern_logique_Metier.GestionEvenements;
import Design_Pattern_logique_Metier.ParticipantObserver;
import Design_Pattern_logique_Metier.SerializeJson;
import Premier.Concert;
import Premier.Evenement;
import Premier.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EssaiTest {
        private GestionEvenements gestion;
        private Concert concert;
        private Participant participant;

        @BeforeEach
        void setUp() {
            gestion = GestionEvenements.getInstance();
            gestion.getEvenements().clear();

            concert = new Concert("c2", "Concert Test",
                    LocalDateTime.now().plusDays(1), "Paris", 50, "Artiste", "Rock");

            participant = new Participant("p1", "Jean", "jean@test.com");
        }

        @Test
        void testAjoutEvenement() {
            assertDoesNotThrow(() -> gestion.ajouterEvenement(concert));
            assertEquals(1, gestion.getEvenements().size());
        }

        @Test
        void testAjoutParticipant() {
            gestion.ajouterEvenement(concert);
            assertDoesNotThrow(() -> concert.ajouterParticipant(
                    new ParticipantObserver(participant.getEmail())));
        }

    @Test
    void testAjoutIncrementalCorrect() throws Exception {
        String fichier = "concert.json";

        // 1. Obtenir l'instance unique
        GestionEvenements gestion = GestionEvenements.getInstance();

        // 2. Charger les événements existants pour vérification
        gestion.chargerEvenements(fichier);
        int nbInitial = gestion.getEvenements().size();

        // 3. Créer le nouveau concert
        Concert nouveau = new Concert(
                "c3",
                "POP",
                LocalDateTime.of(2024, 12, 8, 17, 0),
                "Washington",
                5,
                "Garou",
                "Style POP"
        );

        // 4. Ajout INCrémental avec la nouvelle méthode
        new SerializeJson().ajouterAListe(nouveau, fichier, Evenement.class);

        // 5. Vérifications
        gestion.chargerEvenements(fichier);

        assertEquals(nbInitial + 1, gestion.getEvenements().size(),
                "Doit avoir un événement de plus");

        // Vérification des IDs spécifiques
        assertNotNull(gestion.rechercherEvenement("c1"), "Premier événement doit rester");
        assertNotNull(gestion.rechercherEvenement("c3"), "Nouvel événement doit être ajouté");

        // Affichage pour vérification visuelle
        System.out.println("Contenu final:");
        gestion.getEvenements().values().forEach(e -> {
            System.out.println("- " + e.getId() + ": " + e.getNom());
        });
    }
}