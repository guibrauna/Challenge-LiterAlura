package br.com.catalogo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final CatalogService catalogService;

    public Main(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Catálogo de Livros =====");
            System.out.println("1 - Buscar livros por título");
            System.out.println("2 - Buscar livros por autor");
            System.out.println("3 - Listar livros salvos");
            System.out.println("4 - Salvar livros em arquivo JSON");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Digite o título para busca: ");
                    String titulo = scanner.nextLine();
                    catalogService.buscarLivrosPorTitulo(titulo);
                    break;
                case "2":
                    System.out.print("Digite o nome do autor: ");
                    String autor = scanner.nextLine();
                    catalogService.buscarLivrosPorAutor(autor);
                    break;
                case "3":
                    catalogService.listarLivrosSalvos();
                    break;
                case "4":
                    catalogService.salvarLivrosEmArquivo();
                    break;
                case "5":
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
