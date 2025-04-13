import java.util.*;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
    private int intValue = 0;
    public void set(Iterator<String> currentArgument) throws Exception {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            this.intValue = Integer.parseInt(parameter);

        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static int getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof IntegerArgumentMarshaler) {
            return ((IntegerArgumentMarshaler) am).intValue;
        } else {
            return 0;
        }
    }
}
