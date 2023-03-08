package cotuba.epub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import cotuba.application.GeradorEPUB;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

@Component
public class GeradorEPUBComEpublic implements GeradorEPUB {

    @Override
    public void gera(Ebook ebook) {

        Path arquivoDeSaida = ebook.getArquivoDeSaida();

        var epub = new Book();

        for(Capitulo capitulo : ebook.getCapitulos()){

            String html = capitulo.getConteudoHTML();

            String tituloDoCapitulo = capitulo.getTitulo();
            // TODO: usar título do capítulo
            epub.addSection(tituloDoCapitulo, new Resource(html.getBytes(), MediatypeService.XHTML));
        }


        var epubWriter = new EpubWriter();

        try {
            epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
        } catch (IOException ex) {
            throw new IllegalStateException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }

}
