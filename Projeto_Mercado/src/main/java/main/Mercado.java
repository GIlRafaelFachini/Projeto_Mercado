package main;

import models.Produto;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado{
    private static ArrayList<Produto> produtos;
    private static Map<Produto,Integer> carrinho;

    public static void main(String[] args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------------");
        System.out.println("--------------BEM VINDO AO MERCADO!---------------");
        System.out.println("--------------------------------------------------");
        System.out.println("-----SELECIONE UMA OPÇÃO QUE DESEJA REALIZAR: ----");
        System.out.println("--------------------------------------------------");
        System.out.println(" OPÇÃO 1 CADASTRAR ");
        System.out.println(" OPÇÃO 2 LISTAR ");
        System.out.println(" OPÇÃO 3 COMPRAR ");
        System.out.println(" OPÇÃO 4 CARRINHO ");
        System.out.println(" OPÇÃO 5 SAIR ");

        int option = scanner.nextInt();

        switch (option){

            case 1:
                cadastrarProdutos();
                break;
            case 2:
               listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                verCarrinhoDeProdutos();
                break;
            case 5:
                System.out.println("Obrigado pela preferência");
                System.exit(0);
            default:
                System.out.println("Opção inválida");
                menu();
               break;
        }
    }
    private static void cadastrarProdutos(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome do produto");
        String nome = scanner.next();

        System.out.println("preço do produto");
        Double preco = scanner.nextDouble();

        Produto produto = new Produto(nome,preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + "Cadastrado com sucesso!");
        menu();
    }
    private static void listarProdutos() {

        if (produtos.size()>0){
            System.out.println("Lista de Produtos \n");
            for(Produto p : produtos){
                System.out.println(p);
            }
        }
        else {
            System.out.println("Nenhum produto cadstrado");
        }
        menu();
    }

    private static void comprarProdutos() {

        Scanner scanner = new Scanner(System.in);

        if (produtos.size()>0){
            System.out.println("Código dos Produtos \n");

            System.out.println("Produtos disponíveis");
            for(Produto p : produtos){
                System.out.println(p);
            }
        }
        int id = Integer.parseInt(scanner.next());
        boolean isPresent = false;

        for(Produto p : produtos){
            if (p.getId()==id){
                int qtd = 0;
                try {
                    qtd = carrinho.get(p);
                    carrinho.put(p,qtd + 1);// checa se o produto ja existe no carrinho e incrementa qtd
                }catch (NullPointerException e){
                    carrinho.put(p,1); // se o produto for primeiro no carrinho
                }

                System.out.println(p.getNome() + "Produto adicionadocom sucesso!");
                isPresent = true;

                if(isPresent){
                    System.out.println("Deseja adicionar novo produto ao carrinho?");
                    System.out.println("Digite 1 para sim, ou 0 para finalizar a compra. \n");
                    int option = Integer.parseInt(scanner.next());

                    if (option == 1 ){
                        comprarProdutos();
                    }
                    else {
                        finalizarcompra();
                    }
                }
                else {

                    System.out.println("Produto não encontrado");
                    menu();
                }
            }
        }

        menu();
    }

    private static void verCarrinhoDeProdutos() {
        System.out.println("Produtos no seu carrinho:");
        if (carrinho.size()>0){
            for (Produto p : carrinho.keySet()){
                System.out.println("Produto" + p + "\n Quantidade " + carrinho.get(p));
            }
        }
        else {
            System.out.println("Carrinho está vazio!");
        }
        menu();
    }

    private static void finalizarcompra(){

        Double valorDaCompra =0.0;
        System.out.println("Seus produtos:");

        for (Produto p : carrinho.keySet()) {
            int qtd = carrinho.get(p);
            valorDaCompra += p.getPreco() * qtd;
            System.out.println(p);
            System.out.println("Quantide" + qtd);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("O valor da compra é: " + Utils.doubleToString(valorDaCompra));
        carrinho.clear();
        System.out.println("Obrigado pela preferência");
        menu();
    }

}
