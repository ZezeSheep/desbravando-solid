package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;

public interface RenderizadorMDParaHTML {

    public List<Capitulo> renderiza(Path diretorioDosMD);
    
}
