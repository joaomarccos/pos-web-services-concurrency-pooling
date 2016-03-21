package io.github.joaomarccos.pos.services.concurrency.services;

import io.github.joaomarccos.pos.services.concurrency.produtor.Produtor;
import javax.jws.WebService;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
@WebService(endpointInterface = "io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal1")
public class ProdutorServiceCanal1Impl implements ProdutorServiceCanal1 {

    private final Produtor produtor;

    public ProdutorServiceCanal1Impl(Produtor produtor) {
        this.produtor = produtor;
    }
    
    @Override
    public String request(String id) {
        return produtor.requestSession(id);
    }
    
}