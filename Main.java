public class Main {
    public static void main(String[] args) {
        try {
            // テスト用に引数を指定（本来は args をそのまま渡してもOK）
            String[] arguments = {"-l", "-p", "8080", "-d", "/usr/logs"};

            // スキーマ："l" は boolean, "p#" は int, "d*" は string
            Args argParser = new Args("l,p#,d*", arguments);

            boolean logging = argParser.getBoolean('l');
            int port = argParser.getInt('p');
            String directory = argParser.getString('d');

            System.out.println("argParser.has(p): " + argParser.has('p'));
            System.out.println("argParser.has(m): " + argParser.has('m'));
            System.out.println("Logging enabled: " + logging);
            System.out.println("Port: " + port);
            System.out.println("Directory: " + directory);

        } catch (Exception e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
        }
    }
}