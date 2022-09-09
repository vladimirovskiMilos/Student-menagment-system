package eng.milos.vladimirovski.exception;

public class YearOfStudyException extends MyException{

    private final Object entity;


    public YearOfStudyException(String message, Object entity ) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
