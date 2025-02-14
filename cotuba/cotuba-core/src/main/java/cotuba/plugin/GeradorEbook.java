package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

public interface GeradorEbook {
    void gera(Ebook ebook);

    FormatoEbook formato();

    static GeradorEbook cria(FormatoEbook formato){
        for(GeradorEbook gerador : ServiceLoader.load(GeradorEbook.class)){
            if(gerador.formato().equals(formato)){
                return gerador;
            }
        }

        throw new IllegalArgumentException("Formato do ebook invalido: " + formato);
    }
}
