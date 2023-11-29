class Cliente extends Thread {
    private final Banco banco;
    private final int idCliente;

    public Cliente(Banco banco, int idCliente) {
        this.banco = banco;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        banco.ingresarBanco(idCliente);

        // Simulamos alguna actividad dentro del banco
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        banco.salirBanco(idCliente);
    }
}
