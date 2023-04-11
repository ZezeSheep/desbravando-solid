package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

public interface Plugin {
    
    String aposRenderizacao(String html);

    void aposGeracao(Ebook ebook);

    static void renderizou(Capitulo capitulo){
        ServiceLoader.load(Plugin.class)
        .forEach(plugin -> {
            String html = capitulo.getConteudoHTML();
            String htmlModificado = plugin.aposRenderizacao(html);
            capitulo.setConteudoHTML(htmlModificado);
        });
        
    }
}
