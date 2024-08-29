package pl.mantiscrab.zebrapuzzlesolver.config;

import org.apache.cxf.Bus;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mantiscrab.zebrapuzzlesolver.ws.ZebraPuzzleSolverPort;

@Configuration
class WsConfig {
    private final Bus cxfBus;

    WsConfig(Bus cxfBus) {
        this.cxfBus = cxfBus;
    }

    @Bean
    Endpoint zebraPuzzleSolverEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(cxfBus, new ZebraPuzzleSolverPort());
        endpoint.publish("/zebra-puzzle-solver");
        return endpoint;
    }
}
