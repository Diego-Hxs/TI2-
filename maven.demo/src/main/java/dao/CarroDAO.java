package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Carro;

public class CarroDAO extends DAO {

    public CarroDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Carro carro) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO Carro (Nome, Tipo, Codigo) "
                    + "VALUES ('" + carro.getNome() + "', '" + carro.getTipo() + "', "
                    + carro.getCodigo() + ");";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Carro get(int codigo) {
        Carro carro = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Carro WHERE Codigo=" + codigo;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                carro = new Carro(rs.getInt("Codigo"), rs.getString("Tipo"), rs.getString("Nome"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carro;
    }

    public List<Carro> get() {
        return get("");
    }

    public List<Carro> getOrderByCodigo() {
        return get("Codigo");
    }

    public List<Carro> getOrderByNome() {
        return get("Nome");
    }

    public static List<Carro> getOrderByTipo() {
        return get("Tipo");
    }

    private static List<Carro> get(String orderBy) {

        List<Carro> carros = new ArrayList<Carro>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Carro" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Carro c = new Carro(rs.getInt("Codigo"), rs.getString("Tipo"), rs.getString("Nome"));
                carros.add(c);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carros;
    }

    //Removido o getSexoMasculino, pois não faz sentido para carros.

    public static boolean update(Carro carro) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE Carro SET Nome = '" + carro.getNome() + "', Tipo = '"
                    + carro.getTipo() + "' WHERE Codigo = " + carro.getCodigo();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int codigo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM Carro WHERE Codigo = " + codigo;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public static boolean autenticar(String Nome, String Tipo) {
        boolean resp = false;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Carro WHERE Nome LIKE '" + Nome + "' AND Tipo LIKE '" + Tipo + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }
}