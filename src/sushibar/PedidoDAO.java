package sushibar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidoDAO {

    private Connection conexao;
    private PreparedStatement stmt;
    private PreparedStatement stmt1;
    private PreparedStatement stmt2;


    public PedidoDAO() {

        this.conexao = new Conexao().getConnection();

    }

    public void inserir(Pedido pedido) {

        String sql = "INSERT INTO Pedido(precoTotal, cartao, idFuncionario, data, hora) VALUES (?,?,?,?,?)";

        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, pedido.getPrecoTotal());
            stmt.setBoolean(2, pedido.isCartao());
            stmt.setInt(3, pedido.getIdFuncionario());
            stmt.setDate(4, new java.sql.Date(pedido.getData().getTime()));
            stmt.setString(5, pedido.getHora());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void inserirPratos(ArrayList<Integer> idPratos, int idPedido) {

        String sql = "INSERT INTO Pedido_Prato(idPedido, idPrato) VALUES(?,?)";
        String sql2 = "SET FOREIGN_KEY_CHECKS= 0";
        String sql3 = "SET FOREIGN_KEY_CHECKS= 1";

        try {
            stmt1 = conexao.prepareStatement(sql2);
            stmt1.execute();
            stmt1.close();
            for (int i = 0; i < idPratos.size() - 1; i++) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idPedido);
                stmt.setInt(2, idPratos.get(i));
                stmt.execute();
                stmt.close();
            }
            stmt2 = conexao.prepareStatement(sql3);
            stmt2.execute();
            stmt2.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Pedido pesquisaPedido(Pedido pedido) {
        String sql = "SELECT * FROM Pedido WHERE data = ? and hora = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(pedido.getData().getTime()));
            stmt.setString(2, pedido.getHora());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                pedido.setIdPedido(rs.getInt("idPedido"));
               
            }
            stmt.close();
            return pedido;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
