
package sushibar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;





public class FuncionarioDAO {
    
    private Connection conexao;
    private PreparedStatement stmt;
    
    public FuncionarioDAO(){
        this.conexao = new Conexao().getConnection();
    }
    
    public Funcionario pesquisaFunc(int idFuncionario) {
        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            Funcionario funcionario = new Funcionario();
            if (rs.next()) {
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("Nome"));
            }
            stmt.close();
            return funcionario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
