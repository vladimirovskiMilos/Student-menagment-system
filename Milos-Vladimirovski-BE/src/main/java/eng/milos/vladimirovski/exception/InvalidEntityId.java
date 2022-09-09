package eng.milos.vladimirovski.exception;

public class InvalidEntityId extends MyException{

    private final Object entity;


    public InvalidEntityId(String message, Object entity ) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
