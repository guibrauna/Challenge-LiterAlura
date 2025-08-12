package br.com.catalogo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.List;

public class FileService {

    public static <T> void salvarEmJson(String arquivo, List<T> lista) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(arquivo), lista);
    }
}
