package cotuba.application;

import java.nio.file.Path;

import cotuba.domain.FormatoEbook;

public interface ParametrosCotuba {

    public Path getDiretorioDosMD();

    public FormatoEbook getFormato();

    public Path getArquivoDeSaida();
    
}
