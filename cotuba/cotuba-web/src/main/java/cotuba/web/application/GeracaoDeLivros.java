package cotuba.web.application;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import cotuba.application.Cotuba;
import cotuba.application.ParametrosCotuba;
import cotuba.domain.FormatoEbook;
import cotuba.web.domain.Capitulo;

@Service
public class GeracaoDeLivros {


    public Path geraLivro(List<Capitulo> capitulos, FormatoEbook formato){

        MDsDoBancoDeDados mDsDoBancoDeDados = new MDsDoBancoDeDados(capitulos);


        var cotuba = new Cotuba();
        ParametrosCotuba parametros = new ParametrosCotubaWeb(formato, mDsDoBancoDeDados);
        cotuba.executa(parametros);
        return parametros.getArquivoDeSaida();
    }
    
}
