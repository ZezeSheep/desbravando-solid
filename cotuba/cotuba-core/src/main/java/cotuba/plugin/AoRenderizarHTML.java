package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Capitulo;

public interface AoRenderizarHTML {

    String aposRenderizacao(String html);

    static String renderizou(String html){
        String htmlModificado = html;
        for(AoRenderizarHTML plugin : ServiceLoader.load(AoRenderizarHTML.class))
        {            
            htmlModificado = plugin.aposRenderizacao(htmlModificado);
            
        }
        return htmlModificado;        
    }
    
}
