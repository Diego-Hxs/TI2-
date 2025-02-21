package app;

import java.util.List;
import dao.CarroDAO;
import model.Carro;

public class Aplicacao {

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {

        CarroDAO carroDAO = new CarroDAO();

        System.out.println("\n\n==== Inserir Carro === ");
        Carro carro = new Carro(10, "Fusca", "Popular");
        if (carroDAO.insert(carro) == true) {
            System.out.println("Inserção com sucesso -> " + carro.toString());
        }

        System.out.println("\n\n==== Testando autenticação ===");
        System.out.println("Usuário (" + carro.getNome() + "): " + CarroDAO.autenticar("Ferrari", "Esportivo"));

        System.out.println("\n\n==== Mostrar carros do tipo esportivo === ");
        List<Carro> carros = CarroDAO.getOrderByTipo();
        for (Carro c : carros) {
            System.out.println(c.toString());
        }

        System.out.println("\n\n==== Atualizar Tipo (código (" + carro.getCodigo() + ") === ");
        carro.setTipo(DAO.toMD5("pablo")); // Nota: DAO.toMD5 é um método de criptografia
        CarroDAO.update(carro);

        System.out.println("\n\n==== Testando autenticação ===");
        System.out.println("Usuário (" + carro.getNome() + "): " + CarroDAO.autenticar("pablo", DAO.toMD5("pablo")));

        System.out.println("\n\n==== Invadir usando SQL Injection ===");
        System.out.println("Usuário (" + carro.getNome() + "): " + CarroDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

        System.out.println("\n\n==== Mostrar carros ordenados por código === ");
        carro = (Carro) carroDAO.getOrderByCodigo();
        for (Carro c : carros) {
            System.out.println(c.toString());
        }

        System.out.println("\n\n==== Excluir carro (código " + carro.getCodigo() + ") === ");
        carroDAO.delete(carro.getCodigo());

        System.out.println("\n\n==== Mostrar carros ordenados por nome === ");
        carro = (Carro) carroDAO.getOrderByNome();
        for (Carro c : carros) {
            System.out.println(c.toString());
        }
    }
}