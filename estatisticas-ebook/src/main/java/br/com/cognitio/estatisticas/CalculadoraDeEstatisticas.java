package br.com.cognitio.estatisticas;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;

public class CalculadoraDeEstatisticas implements Plugin {

    @Override
    public void aposGeracao(Ebook ebook) {

        var contagemDePalavras = new ContagemDePalavras();


        List<Capitulo> capitulos = ebook.getCapitulos();
        for(Capitulo capitulo : capitulos){
            String html = capitulo.getConteudoHTML();
            Document document = Jsoup.parse(html);
            String textoDoCapitulo = document.body().text();
            
            String textoDoCapituloSemPontuacao = textoDoCapitulo.replaceAll("\\p{Punct}", " ");

            String textoDoCapituloSemAcentos = Normalizer
                .normalize(textoDoCapituloSemPontuacao, Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
            
            String[] palavras = textoDoCapituloSemAcentos.split("\\s+");
            for(String palavra : palavras){
                String emMaiusculas = palavra.toUpperCase();

                contagemDePalavras.adicionaPalavra(emMaiusculas);
            }

            for(Map.Entry<String, Integer> contagem : contagemDePalavras.entrySet()){
                String palavra = contagem.getKey();
                Integer ocorrencias = contagem.getValue();
                System.out.println(palavra + ": " + ocorrencias);
            }
        }
    }

    @Override
    public String aposRenderizacao(String arg0) {
        return arg0;
    }
    
}
