import Design_Pattern_logique_Metier.ParticipantObserver;
import Premier.Concert;
import Premier.Conference;
import Premier.Participant;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import Exception.CapaciteMaxAtteinteException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JJ {
    private Concert concert;
    private Conference conference;
    private Participant participant,participant2;
    private ParticipantObserver observer,observer2;

    @BeforeEach
    public void init() {
         participant = new Participant("p1",
                "Michèle_K",
                "michelekenmeugne@gmail.com");

         concert= new Concert("coc1",
                "Concert de HALLS",
                LocalDateTime.now(),
                 "Jouvence",
                 1,
                 "Deborah",
                 "Gospel"
                         );
        participant2 = new Participant("p2",
                "Francois",
                "Francois@gmail.com");

        observer=new ParticipantObserver(participant.getEmail());
        observer2=new ParticipantObserver(participant2.getEmail());

    }
    @Test
    public void Rien(){

    }
    @Test
    public void TestajouterParticipant() throws CapaciteMaxAtteinteException {

        concert.ajouterParticipant(observer);
        concert.ajouterParticipant(observer2);
    }
    @Test
    public void TestAfficherDetailsConcert(){
        concert.afficherDetails();
    }
 /*   @Test
    public void TestRetirerParticipant{
        concert.retirerObserver(observer);
    }*/
}





