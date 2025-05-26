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



}