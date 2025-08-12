package br.com.catalogo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CatalogService {

    private final List<Livro> livrosSalvos = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();

    public void buscarLivrosPorTitulo(String titulo) {
        try {
            String url = "https://gutendex.com/books?search=" + titulo.replace(" ", "%20");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            GutendexResponse gutendexResponse = mapper.readValue(response.body(), GutendexResponse.class);

            if (gutendexResponse.getResults().isEmpty()) {
                System.out.println("Nenhum livro encontrado para o título informado.");
                return;
            }

            System.out.println("\n--- Resultados ---");
            for (Livro livro : gutendexResponse.getResults()) {
                System.out.println(livro);
                livrosSalvos.add(livro);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
        }
    }

    public void buscarLivrosPorAutor(String autor) {
        try {
            String url = "https://gutendex.com/books?search=" + autor.replace(" ", "%20");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            GutendexResponse gutendexResponse = mapper.readValue(response.body(), GutendexResponse.class);

            if (gutendexResponse.getResults().isEmpty()) {
                System.out.println("Nenhum livro encontrado para o autor informado.");
                return;
            }

            System.out.println("\n--- Resultados ---");
            for (Livro livro : gutendexResponse.getResults()) {
                boolean autorEncontrado = livro.getAuthors().stream()
                        .anyMatch(a -> a.getName().toLowerCase().contains(autor.toLowerCase()));
                if (autorEncontrado) {
                    System.out.println(livro);
                    livrosSalvos.add(livro);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
        }
    }

    public void listarLivrosSalvos() {
        if (livrosSalvos.isEmpty()) {
            System.out.println("Nenhum livro salvo ainda.");
            return;
        }
        System.out.println("\n--- Livros Salvos ---");
        livrosSalvos.forEach(System.out::println);
    }

    public void salvarLivrosEmArquivo() {
        if (livrosSalvos.isEmpty()) {
            System.out.println("Nenhum livro para salvar.");
            return;
        }

        try {
            FileService.salvarEmJson("livros_salvos.json", livrosSalvos);
            System.out.println("Arquivo 'livros_salvos.json' salvo com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}
