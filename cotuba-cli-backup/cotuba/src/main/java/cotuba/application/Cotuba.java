package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.plugin.AoFinalizarGeracao;

@Component
public class Cotuba {

    public void executa(ParametrosCotuba parametros) {

        FormatoEbook formato = parametros.getFormato(); 
        Path diretorioDosMD = parametros.getDiretorioDosMD(); 
        Path arquivoDeSaida = parametros.getArquivoDeSaida();

        var renderizador = new RenderizadorMDParaHTML();

        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook(formato, arquivoDeSaida, capitulos);

        GeradorEbook gerador = GeradorEbook.cria(formato);

        gerador.gera(ebook);

        AoFinalizarGeracao.gerou(ebook);

    }

}
