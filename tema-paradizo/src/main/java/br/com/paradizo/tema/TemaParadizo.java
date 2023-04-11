package br.com.paradizo.tema;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;

public class TemaParadizo implements Plugin {

    private String aplicaTema(String html){
        Document document = Jsoup.parse(html);
        String css = cssDoTema();
        document.select("head")
            .append("<style>"+ css+ "</style>");
        return document.html();
    }

    private String cssDoTema(){
        return FileUtils.getResourceContents("/tema.css");
    }

    @Override
    public void aposGeracao(Ebook ebook) {
    }

    @Override
    public String aposRenderizacao(String html) {
        String htmlComTema = aplicaTema(html);
        return htmlComTema;
    }
    
}
