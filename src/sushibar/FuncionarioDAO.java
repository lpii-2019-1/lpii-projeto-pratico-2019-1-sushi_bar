
package sushibar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FuncionarioDAO {
    
    private Connection conexao;
    private PreparedStatement stmt;
    
    public FuncionarioDAO() {
        this.conexao = new Conexao().getConexao();
    }
    
    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, data_nasc, endereco, salario, cargo, cpf) VALUES (?,?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, funcionario.getNasc());
            stmt.setString(3, funcionario.getEnd());
            stmt.setDouble(4, funcionario.getSalario());
            stmt.setString(5, funcionario.getCargo());
            stmt.setInt(6, funcionario.getCPF());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
}
