import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Banco {
    private static final int MAX_CLIENTES = 2;
    private int clientesEnBanco = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition condicionClientes = lock.newCondition();

    public void ingresarBanco(int idCliente) {
        lock.lock();
        try {
            while (clientesEnBanco == MAX_CLIENTES) {
                System.out.println("Cliente " + idCliente + " esperando para ingresar al banco.");
                condicionClientes.await();
            }

            clientesEnBanco++;
            System.out.println("Cliente " + idCliente + " ha ingresado al banco. Clientes en el banco: " + clientesEnBanco);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void salirBanco(int idCliente) {
        lock.lock();
        try {
            clientesEnBanco--;
            System.out.println("Cliente " + idCliente + " ha salido del banco. Clientes en el banco: " + clientesEnBanco);
            condicionClientes.signalAll();

        } finally {
            lock.unlock();
        }
    }
}