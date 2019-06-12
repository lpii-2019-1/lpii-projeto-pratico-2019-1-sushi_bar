package sushibar;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.text.DateFormat;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Sushibar {
    
    public static String formatar(double precoDouble) {
    DecimalFormat fmt = new DecimalFormat("0.00");      
    String preco = fmt.format(precoDouble);
    
    return preco;
}

    public static void main(String[] args) {
        String hora = "HH:mm:ss";
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserir codigo de Funcionario: ");
        int idFuncionario = sc.nextInt();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.pesquisaFunc(idFuncionario);
        System.out.println("\n");

        while (idFuncionario != 0) {
            System.out.println("Logado como " + funcionario.getNome());

            System.out.println("Digite o codigo da operacao desejada: ");
            Scanner sc1 = new Scanner(System.in);
            int idOperacao = sc1.nextInt();

            ArrayList<Prato> pratos = new ArrayList<Prato>();
            ArrayList<Integer> ids = new ArrayList<>();
            Scanner sc2 = new Scanner(System.in);
            System.out.println("\n");
            while (idOperacao == 1) {
                Pedido pedido = new Pedido();
                pedido.setIdFuncionario(idFuncionario);
                int idPrato = 0;
                double precoTotal = 0;
                do {
                    
                    System.out.println("Codigo do Prato: ");

                    idPrato = sc2.nextInt();
                    
                    PratoDAO pratoDAO = new PratoDAO();
                    pratos.add(pratoDAO.pesquisaPrato(idPrato));
                    ids.add(idPrato);
                    Prato prato = pratoDAO.pesquisaPrato(idPrato);
                    precoTotal = precoTotal + prato.getPreco();
                    
                } while (idPrato != 0);
                System.out.println("\n");
                Scanner sc3 = new Scanner(System.in);
                System.out.println("Pagamento com cartao(s/n)? ");
                String cartao = "";
                
                while(cartao.trim().equals("") || cartao.isEmpty()){
                cartao = sc3.nextLine();
                }
                if ("s".equals(cartao)){
                    pedido.setCartao(true);
                }else{
                    pedido.setCartao(false);
                }
                
                java.util.Date data = new java.util.Date();
                java.sql.Date dataSql = new java.sql.Date(data.getTime());
                
                
                pedido.setData(dataSql);
                System.out.println("\n");
                
                DateFormat df1 = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
                
                SimpleDateFormat dfh = new SimpleDateFormat(hora);
                System.out.println(df1.format(pedido.getData())+" "+dfh.format(pedido.getData()));
                pedido.setHora(dfh.format(pedido.getData()));
                for (int i = 0; i < pratos.size()-1; i++) {
                    Prato prato = pratos.get(i);
                    System.out.println(prato.getNome() +"  R$" + formatar(prato.getPreco()));
                }
                pedido.setPrecoTotal(precoTotal);
                System.out.println("Preco total: R$"+formatar(precoTotal));
                System.out.println("Cartao: " + pedido.isCartao());
                System.out.println("\n");
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.inserir(pedido);
                pedido = pedidoDAO.pesquisaPedido(pedido);
                pedidoDAO.inserirPratos(ids, pedido.getIdPedido());
                pratos.clear();
                break;

            }

            if (idOperacao == 0) {
                break;
            } else if (idOperacao != 1 && idOperacao!= 0) {
                System.out.println("Codigo de operacao invalido, favor checar a tabela de operacoes!");
            }
        }
    }
}
