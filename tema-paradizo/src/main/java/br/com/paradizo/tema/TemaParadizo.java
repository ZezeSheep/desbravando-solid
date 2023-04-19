package br.com.paradizo.tema;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.plugin.AoRenderizarHTML;

public class TemaParadizo implements AoRenderizarHTML {

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
    public String aposRenderizacao(String html) {
        String htmlComTema = aplicaTema(html);
        return htmlComTema;
    }
    
}
