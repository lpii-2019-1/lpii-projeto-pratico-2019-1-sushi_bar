package sushibar;
import java.util.ArrayList;


public class Prato {
    
    String nome;
    ArrayList<Ingrediente> nomeIng;
    double preco;

    public Prato(String nome, ArrayList<Ingrediente> nomeIng, double preco) {
        this.nome = nome;
        this.nomeIng = nomeIng;
        this.preco = preco;
    }

    public ArrayList<Ingrediente> getNomeIng() {
        return nomeIng;
    }

    public void setNomeIng(ArrayList<Ingrediente> nomeIng) {
        this.nomeIng = nomeIng;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
    public void addIng(String ing){
    
        
        
    }
    
    public void removeIng(String ing){
    
    
    
    }
    
}
