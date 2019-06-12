package sushibar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PratoDAO {
    private Connection conexao;
    private PreparedStatement stmt; 
    
    public PratoDAO(){
    
        this.conexao = new Conexao().getConnection();
    
    }
    
    public Prato pesquisaPrato(int id) {
        String sql = "SELECT * FROM Prato WHERE idPrato = ?";
        try {
            
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Prato prato = new Prato();
            
            if (rs.next()) {
                
                prato.setIdPrato(rs.getInt("idPrato"));
                prato.setNome(rs.getString("nome"));
                prato.setPreco(rs.getDouble("preco"));
        
            }
            stmt.close();
            return prato;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
