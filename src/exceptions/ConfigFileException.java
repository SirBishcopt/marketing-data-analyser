package exceptions;

public class ConfigFileException extends RuntimeException {

    public ConfigFileException (String info){
        super(info);
    }

}
