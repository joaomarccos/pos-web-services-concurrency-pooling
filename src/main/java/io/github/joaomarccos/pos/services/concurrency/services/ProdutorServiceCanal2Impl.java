package io.github.joaomarccos.pos.services.concurrency.services;

import io.github.joaomarccos.pos.services.concurrency.produtor.Produtor;
import javax.jws.WebService;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
@WebService(endpointInterface = "io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal2")
public class ProdutorServiceCanal2Impl implements ProdutorServiceCanal2{

    private final Produtor produtor;

    public ProdutorServiceCanal2Impl(Produtor produtor) {
        this.produtor = produtor;        
    }
    
    @Override
    public void process(String hash) {
        produtor.addTask(hash);
    }

}
