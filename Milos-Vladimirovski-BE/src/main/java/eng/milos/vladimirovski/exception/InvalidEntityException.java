package eng.milos.vladimirovski.exception;

public class InvalidEntityException extends MyException {

    private final Object entity;

    public InvalidEntityException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }
    public Object getEntity(){
        return entity;
    }
}
