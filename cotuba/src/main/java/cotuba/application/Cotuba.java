package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

@Component
public class Cotuba {

    private RenderizadorMDParaHTML renderizador;
    private GeradorEPUB geradorEPUB;
    private GeradorPDF geradorPDF;

    public Cotuba(RenderizadorMDParaHTML renderizador, 
        GeradorEPUB geradorEPUB, GeradorPDF geradorPDF){
            this.renderizador = renderizador;
            this.geradorPDF = geradorPDF;
            this.geradorEPUB = geradorEPUB;
    }

    public void executa(ParametrosCotuba parametros) {

        String formato = parametros.getFormato(); 
        Path diretorioDosMD = parametros.getDiretorioDosMD(); 
        Path arquivoDeSaida = parametros.getArquivoDeSaida();

        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {

            geradorPDF.gera(ebook);

        } else if ("epub".equals(formato)) {

            geradorEPUB.gera(ebook);

        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

    }

}
