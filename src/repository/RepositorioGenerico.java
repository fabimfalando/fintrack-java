package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioGenerico<T> {

    private final List<T> elementos = new ArrayList<>();

    // Adiciona um elemento genérico
    public void adicionar(T elemento) {
        if (elemento != null) {
            elementos.add(elemento);
        }
    }

    // Remove um elemento genérico
    public boolean remover(T elemento) {
        return elementos.remove(elemento);
    }

    // Retorna todos os elementos
    public List<T> listarTodos() {
        return Collections.unmodifiableList(elementos);
    }

    // Método utilizando curinga com limite superior (? extends T)
    // Permite adicionar múltiplos itens de uma lista de subclasse/tipo derivado
    public void adicionarTodos(List<? extends T> novosElementos) {
        if (novosElementos != null) {
            elementos.addAll(novosElementos);
        }
    }

    // Método utilizando curinga com limite inferior (? super T)
    // Permite exportar/copiar os elementos armazenados para uma lista de supertipo
    public void copiarPara(List<? super T> destino) {
        if (destino != null) {
            destino.addAll(elementos);
        }
    }

    public int tamanho() {
        return elementos.size();
    }

    public void limpar() {
        elementos.clear();
    }
}