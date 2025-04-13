import java.util.*;

public class StringArgumentMarshaler implements ArgumentMarshaler {
    private String stringValue = "";
    public void set(Iterator<String> currentArgument) throws Exception {
        try {
            this.stringValue = currentArgument.next();

        } catch(Exception e) {
            throw new Exception();
        }
        
    }

    public static String getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArgumentMarshaler) {
            return ((StringArgumentMarshaler) am).stringValue;
        }
        else {
            return "";
        }
    }

}
