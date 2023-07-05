package br.com.cognitio.estatisticas;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;


public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {

    @Override
    public void aposGeracao(Ebook ebook) {

        ContagemDePalavras contagemDePalavras = new ContagemDePalavras();

        for(Capitulo capitulo : ebook.capitulos()){
            String html = capitulo.conteudoHTML();
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


            for(ContagemDePalavras.Contagem contagem : contagemDePalavras){
                System.out.println(contagem.palavra() + ": " + contagem.ocorrencias());
            }
        }
    }
    
}
