public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        for (int i = 1; i <= 5; i++) {
            Cliente cliente = new Cliente(banco, i);
            cliente.start();
        }
    }
}