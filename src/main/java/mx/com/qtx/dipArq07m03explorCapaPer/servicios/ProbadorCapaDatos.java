package mx.com.qtx.dipArq07m03explorCapaPer.servicios;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import mx.com.qtx.dipArq07m03explorCapaPer.servicios.dto.CategoriaDTO;
import mx.com.qtx.dipArq07m03explorCapaPer.servicios.dto.CategoriaProductoDTO;

//@Component
public class ProbadorCapaDatos implements CommandLineRunner{
	private static Logger log = LoggerFactory.getLogger(ProbadorCapaDatos.class);
	
	@Autowired
	IGestorDatos gestorDatos;

	@Override
	public void run(String... args) throws Exception {
		
		CategoriaDTO catDto = this.gestorDatos.leerCategoriaXID(5);
		log.info("Categoria leída: " + catDto);
		List<CategoriaProductoDTO> lstProdCats = this.gestorDatos.leerProductosConCategorias(1);
		lstProdCats.forEach(pcI->log.info(pcI.toString()));
	}

}
