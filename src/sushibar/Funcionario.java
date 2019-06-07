package sushibar;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Date;


public class Funcionario {
    
    String nome;
    Calendar data_nasc;
    String endereco;
    double salario;
    String cargo;
    int cpf;

    public Funcionario(String nome, Calendar data_nasc, String endereco, int cpf) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.endereco = endereco;
        this.cpf = cpf;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Calendar data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    

    
    
}
