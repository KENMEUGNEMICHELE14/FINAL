import Design_Pattern_logique_Metier.GestionEvenements;
import Design_Pattern_logique_Metier.ParticipantObserver;
import Premier.Concert;
import Premier.Conference;
import Premier.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;



public class EvenementTest {
    private GestionEvenements gestion;
    private Concert concert;
    private Participant participant1;
    private ParticipantObserver observer1;



    @BeforeEach
            void setUp(){

        //1/ Initialisation
        gestion= GestionEvenements.getInstance();

        gestion.chargerEvenements("concert.json");
        Concert concert =(Concert) gestion.rechercherEvenement("c1");
        assertNotNull(concert,"le concert c1 doit exister");


        participant1=new Participant(
                "p1",
                "Michèle_K",
                "michelekenmeugne@gmail.com"
        );
        observer1=new ParticipantObserver(participant1.getEmail());
    }

    public void TestAjouterParticipant() throws Exception{
        assertDoesNotThrow(()->concert.ajouterParticipant(observer1));
        assertTrue(concert.getObservers().contains(observer1));
        gestion.sauvegarderEvenement("convert.json");
    }

    // test de chargement d'un fichier JSON
    @Test
    public void ChargementTest(){
        // reinitialisation
        gestion.getEvenements().clear();
        assertTrue(gestion.getEvenements().isEmpty());

        // chargeons
        gestion.chargerEvenements("conference.json");
        Conference charge=(Conference)gestion.rechercherEvenement("conf1");
        assertNotNull(charge);
        assertEquals("Conference de Recyclahe", charge.getNom());

    }
}


