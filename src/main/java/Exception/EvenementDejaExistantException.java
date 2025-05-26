package Exception;

public class EvenementDejaExistantException extends RuntimeException {

    public EvenementDejaExistantException(String avertissement) {

        super(avertissement);
    }

}
