package io.github.joaomarccos.pos.services.concurrency.cliente;

import io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal1;
import io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal2;
import io.github.joaomarccos.pos.services.concurrency.services.ProdutorServiceCanal3;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class Client {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        URL wsdl = new URL("http://127.0.0.1:9876/concurService?wsdl");
        QName qname = new QName("http://services.concurrency.services.pos.joaomarccos.github.io/", "ProdutorServiceCanal1ImplService");
        Service ws = Service.create(wsdl, qname);

        ProdutorServiceCanal1 canal1 = ws.getPort(ProdutorServiceCanal1.class);
        
        wsdl = new URL("http://127.0.0.1:9877/concurService?wsdl");
        qname = new QName("http://services.concurrency.services.pos.joaomarccos.github.io/", "ProdutorServiceCanal2ImplService");
        ws = Service.create(wsdl, qname);
        
        ProdutorServiceCanal2 canal2 = ws.getPort(ProdutorServiceCanal2.class);
        
        wsdl = new URL("http://127.0.0.1:9878/concurService?wsdl");
        qname = new QName("http://services.concurrency.services.pos.joaomarccos.github.io/", "ProdutorServiceCanal3ImplService");
        ws = Service.create(wsdl, qname);
        ProdutorServiceCanal3 canal3 = ws.getPort(ProdutorServiceCanal3.class);

        String request = canal1.request("id");
        System.out.println(request);
        canal2.process(request.split(":")[1]);

        while (true) {
            Thread.sleep(3000);
            String response = canal3.response(request.split(":")[1]);
            System.out.println(response);
            if (!response.equals("noresponse")) {
                break;
            }
        }

    }
}
