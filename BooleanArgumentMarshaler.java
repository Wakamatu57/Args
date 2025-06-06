import java.util.*;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
    private boolean booleanValue = false;
    public void set(Iterator<String> currentArgument) throws Exception {
        this.booleanValue = true;
    }

    public static boolean getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof BooleanArgumentMarshaler) {
            return ((BooleanArgumentMarshaler) am).booleanValue;
        }
        else {
            return false;
        }
    }

}
