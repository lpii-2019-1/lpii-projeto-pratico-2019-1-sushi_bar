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

    //metodo para formartar os precos em "00.00"
    public static String formatar(double precoDouble) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String preco = fmt.format(precoDouble);

        return preco;
    }

    public static void main(String[] args) {
        String hora = "HH:mm:ss";
        Scanner sc = new Scanner(System.in);
        //logar funcionario
        System.out.println("Inserir codigo de Funcionario: ");
        int idFuncionario = sc.nextInt();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.pesquisaFunc(idFuncionario);
        System.out.println("\n");

        while (idFuncionario != 0) {
            System.out.println("Logado como " + funcionario.getNome());
            //escolher operacao 1-Pedido 0-Finalizar
            System.out.println("Digite o codigo da operacao desejada: ");
            Scanner sc1 = new Scanner(System.in);
            int idOperacao = sc1.nextInt();
            //criando arraylist de pratos e de ids dos pratos
            ArrayList<Prato> pratos = new ArrayList<Prato>();
            ArrayList<Integer> ids = new ArrayList<>();
            Scanner sc2 = new Scanner(System.in);
            System.out.println("\n");
            //inicia o cilco da operacao 1 onde vao ser requisistados os ids dos pratos
            while (idOperacao == 1) {
                Pedido pedido = new Pedido();
                pedido.setIdFuncionario(idFuncionario);
                int idPrato = 0;
                double precoTotal = 0;
                do {

                    System.out.println("Codigo do Prato: ");

                    idPrato = sc2.nextInt();
                    if (idPrato == 999) {
                        break;
                    }
                    PratoDAO pratoDAO = new PratoDAO();
                    pratos.add(pratoDAO.pesquisaPrato(idPrato)); //add o prato escolhido ao array de pratos
                    ids.add(idPrato);
                    //busca o prato no banco de dados e soma o seu preco ao preco total do pedido
                    Prato prato = pratoDAO.pesquisaPrato(idPrato);
                    precoTotal = precoTotal + prato.getPreco();
                    //verifica se o cliente deseja pagar com cartao
                } while (idPrato != 0);
                if (idPrato == 999) {
                    System.out.println("Pedido cancelado....\n");

                    break;
                }
                System.out.println("\n");
                Scanner sc3 = new Scanner(System.in);
                System.out.println("Pagamento com cartao(s/n)? ");
                String cartao = "";

                while (cartao.trim().equals("") || cartao.isEmpty()) {
                    cartao = sc3.nextLine();
                }
                if ("s".equals(cartao)) {
                    pedido.setCartao(true);
                } else {
                    pedido.setCartao(false);
                }
                System.out.println("\n");

                for (int i = 0; i < pratos.size() - 1; i++) {
                    Prato prato = pratos.get(i);
                    System.out.println(prato.getNome() + "  R$" + formatar(prato.getPreco()));
                }
                System.out.println("Preco total: R$" + formatar(precoTotal));

                if ("s".equals(cartao)) {
                    System.out.println("\n");

                    System.out.println("Aguardando resposta da maquina de cartao.....");
                }

                if (!pedido.isCartao()) {
                    Scanner sc5 = new Scanner(System.in);
                    System.out.println("Insira o valor do pagamento do cliente: ");
                    double pagamento = sc5.nextDouble();
                    if (pagamento == 0) {
                        System.out.println("Pedido cancelado....\n");
                        break;
                    }
                    double troco = pagamento - precoTotal;
                    System.out.println("Troco do cliente: R$" + formatar(troco));
                }
                System.out.println("\n");

                Scanner sc4 = new Scanner(System.in);
                //pega o nome do cliente para saber a quem entregar o pedido
                System.out.println("Nome do cliente: ");
                String nomeCliente = sc4.nextLine();
                //pega a data e hora atular e declara como data e hora do pedido
                java.util.Date data = new java.util.Date();
                java.sql.Date dataSql = new java.sql.Date(data.getTime());

                pedido.setData(dataSql);
                System.out.println("\n");
                //formata a data para o tipo pt br
                DateFormat df1 = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));

                SimpleDateFormat dfh = new SimpleDateFormat(hora);

                System.out.println("Imprimindo comprovante....");
                System.out.println("\n");

                //imprime os dados do pedido
                System.out.println("Data: " + df1.format(pedido.getData()) + " Hora: " + dfh.format(pedido.getData()));
                pedido.setHora(dfh.format(pedido.getData()));
                System.out.println("\t" + nomeCliente);
                for (int i = 0; i < pratos.size() - 1; i++) {
                    Prato prato = pratos.get(i);
                    System.out.println(prato.getNome() + "  R$" + formatar(prato.getPreco()));
                }
                pedido.setPrecoTotal(precoTotal);
                System.out.println("Preco total: R$" + formatar(precoTotal));
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
            } else if (idOperacao != 1 && idOperacao != 0) {
                System.out.println("Codigo de operacao invalido, favor checar a tabela de operacoes!");
            }
        }
        System.out.println("Sessão finalizada...");
    }
}
