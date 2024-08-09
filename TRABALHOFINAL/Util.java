public class Util {
    public static void clearScreen() {
        // Verifica o sistema operacional
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                // Limpa a tela no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Limpa a tela no Unix-based (Linux, macOS)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }
}