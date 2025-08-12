package br.com.catalogo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {

    private int id;

    private String title;

    @JsonAlias("authors")
    private List<Autor> authors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Autor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Autor> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Título: ").append(title).append("\n");
        sb.append("Autores: ");
        if (authors != null) {
            for (Autor autor : authors) {
                sb.append(autor.getName()).append("; ");
            }
        }
        sb.append("\n---------------------------");
        return sb.toString();
    }
}
