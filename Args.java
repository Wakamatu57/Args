import java.util.*;

public class Args {
    private Map<Character, ArgumentMarshaler> marshalers;
    private Set<Character> argsFound;
    private ListIterator<String> currentArgument;

    public Args(String schema, String[] args) throws Exception {
        this.marshalers = new HashMap<Character, ArgumentMarshaler>();
        this.argsFound = new HashSet<Character>();

        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(args));
    }

    private void parseSchema(String schema) throws Exception {
        for (String element: schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }
    }

    private void parseSchemaElement(String element) throws Exception {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (elementTail.length() == 0) {
            this.marshalers.put(elementId, new BooleanArgumentMarshaler());
        }
        else if (elementTail.equals("*")) {
            this.marshalers.put(elementId, new StringArgumentMarshaler());
        }
        else if (elementTail.equals("#")) {
            this.marshalers.put(elementId, new IntegerArgumentMarshaler());
        }
    }

    private void validateSchemaElementId(char elementId) throws Exception {
        if (!Character.isLetter(elementId)) {
            throw new Exception();
        }
    }

    private void parseArgumentStrings(List<String> argsList) throws Exception {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
            String argString = currentArgument.next();
            if (argString.startsWith("-")) {
                parseArgumentCharacters(argString.substring(1));
            } else {
                currentArgument.previous();
                break;
            }
        }
    }

    private void parseArgumentCharacters(String argChars) throws Exception {
        for (int i = 0; i < argChars.length(); i++) {
            parseArgumentCharacter(argChars.charAt(i));
        }
    }

    private void parseArgumentCharacter(char argchar) throws Exception {
        ArgumentMarshaler m = this.marshalers.get(argchar);
        if (m == null) {
            throw new Exception();
        } else {
            this.argsFound.add(argchar);
            try {
                m.set(currentArgument);
            } catch (Exception e) {
                throw e;
            }
            
        }
    }

    public boolean has(char arg) {
        return this.argsFound.contains(arg);
    }
    
    public boolean getBoolean(char arg) {
        return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public String getString(char arg) {
        return StringArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public int getInt(char arg) {
        return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
    }

    



}
