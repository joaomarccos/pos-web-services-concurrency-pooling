package io.github.joaomarccos.pos.services.concurrency.services;

import io.github.joaomarccos.pos.services.concurrency.produtor.Produtor;
import javax.jws.WebService;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
@WebService(endpointInterface = "io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal3")
public class ProdutorServiceCanal3Impl implements ProdutorServiceCanal3 {

    private final Produtor produtor;

    public ProdutorServiceCanal3Impl(Produtor produtor) {
        this.produtor = produtor;
    }

    @Override
    public String response(String hash) {
        return produtor.getResponse(hash);
    }

}
