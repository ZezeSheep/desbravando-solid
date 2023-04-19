package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Capitulo;

public interface AoRenderizarHTML {

    String aposRenderizacao(String html);

    static void renderizou(Capitulo capitulo){
        ServiceLoader.load(AoRenderizarHTML.class)
        .forEach(plugin -> {
            String html = capitulo.getConteudoHTML();
            String htmlModificado = plugin.aposRenderizacao(html);
            capitulo.setConteudoHTML(htmlModificado);
        });
        
    }
    
}
