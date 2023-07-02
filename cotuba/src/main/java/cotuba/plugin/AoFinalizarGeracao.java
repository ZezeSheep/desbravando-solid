package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Ebook;

public interface AoFinalizarGeracao {

    void aposGeracao(Ebook ebook);

    static void gerou(Ebook ebook) {
        ServiceLoader.load(AoFinalizarGeracao.class)
        .forEach(plugin -> {
            plugin.aposGeracao(ebook);
        });
    }
    
}
