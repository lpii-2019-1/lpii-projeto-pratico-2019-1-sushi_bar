package sushibar;

/**
 *
 * @author Giovane
 */
public class IngredienteDAO {
    
    public void inserir(Ingrediente ingrediente) {
		String sql = "INSERT INTO ingredientes (nome, quantidade) VALUES (?, ?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, ingrediente.getNome());
			stmt.setInt(2, ingrediente.getQuant());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro: " + e);
		}
	}
    
}
