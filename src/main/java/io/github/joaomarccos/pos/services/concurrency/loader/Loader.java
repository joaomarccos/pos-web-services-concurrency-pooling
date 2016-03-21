package io.github.joaomarccos.pos.services.concurrency.loader;

import io.github.joaomarccos.pos.services.concurrency.produtor.Produtor;
import io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal1Impl;
import io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal2Impl;
import io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal3Impl;
import javax.xml.ws.Endpoint;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class Loader {
    public static void main(String[] args) {
        Produtor p = new Produtor();
        p.start();
        Endpoint.publish("http://127.0.0.1:9876/concurService", new ProdutorServiceCanal1Impl(p));
        Endpoint.publish("http://127.0.0.1:9877/concurService", new ProdutorServiceCanal2Impl(p));
        Endpoint.publish("http://127.0.0.1:9878/concurService", new ProdutorServiceCanal3Impl(p));
    }
}
