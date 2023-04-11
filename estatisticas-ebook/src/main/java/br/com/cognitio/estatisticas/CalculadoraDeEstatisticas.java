package br.com.cognitio.estatisticas;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;

public class CalculadoraDeEstatisticas implements Plugin {

    @Override
    public void aposGeracao(Ebook ebook) {
        List<Capitulo> capitulos = ebook.getCapitulos();
        for(Capitulo capitulo : capitulos){
            String html = capitulo.getConteudoHTML();
            Document document = Jsoup.parse(html);
            String textoDoCapitulo = document.body().text();
            String[] palavras = textoDoCapitulo.split("\\s+");
            for(String palavra: palavras){
                System.out.println(palavra);
            }
        }
    }

    @Override
    public String aposRenderizacao(String arg0) {
        return arg0;
    }
    
}
