import Design_Pattern_logique_Metier.GestionEvenements;
import Design_Pattern_logique_Metier.ParticipantObserver;
import Premier.Concert;
import Premier.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Exception.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTest {

    private GestionEvenements gestion;
    private Concert concert;

    @BeforeEach
    void setUp() {
        gestion = GestionEvenements.getInstance();
        gestion.getEvenements().clear(); // Nettoyage avant chaque test
         concert = new Concert(
                "conc1,",
                "concert de ZOUK",
                LocalDate.now().plusDays(1).atStartOfDay(),
                "Palais de Sport de Yaoundé",
                2,
                "Fanny J",
                "ZOUK"
        );

    }



    @Test
    void TesterParticipantCapaciteMax() {
        ParticipantObserver p1 = new ParticipantObserver("p1@gmail.com");
        ParticipantObserver p2 = new ParticipantObserver("p2@gmail.com");
        ParticipantObserver p3 = new ParticipantObserver("p3@gmail.com");


// Maintenat, on va essayer d'ajouter les participants
        assertDoesNotThrow(() -> concert.ajouterParticipant(p1));
        assertDoesNotThrow(()->concert.ajouterParticipant(p2));

        // Ici maintenant, on doit lancer l'Exception
        Exception exception=assertThrows(CapaciteMaxAtteinteException.class,
                ()->concert.ajouterParticipant(p3));
    // Verifions le message
        assertEquals("La Capacité maximale de la salle est atteinte",
                exception.getMessage());
}
@Test void TesterEvenementDejaExistant(){



        Concert concert2 = new Concert(
                "conc1", // Même ID
                "COncest CHIC",
                LocalDateTime.now().plusDays(1),
                "Japoma",
                50,
                "TAYJOR",
                "Pop"
        );

        // Act & Assert
        assertDoesNotThrow(() -> gestion.ajouterEvenement(concert));

        Exception exception = assertThrows(
                EvenementDejaExistantException.class,
                () -> gestion.ajouterEvenement(concert2)
        );

        assertEquals("L'évenement avec l'ID c1 existe déjà",
                exception.getMessage());
    }
}

