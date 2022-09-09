package eng.milos.vladimirovski.exception;

public class DuplicateEntityException extends MyException{

    private final Object entity;


    public DuplicateEntityException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
