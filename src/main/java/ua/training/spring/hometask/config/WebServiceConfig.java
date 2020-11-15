package ua.training.spring.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean(name = "users")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("userPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://localhost:8888/schemas/users");
        wsdl11Definition.setSchema(usersSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("soap/schema1.xsd"));
    }

    //    @Bean
    //    public Jaxb2Marshaller marshaller() {
    //        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    //        marshaller.setContextPath("ua.training.spring.hometask.dto.soap");
    //        return marshaller;
    //    }
    //    @Bean
    //    public UserClient countryClient(Jaxb2Marshaller marshaller) {
    //        UserClient client = new UserClient();
    //        client.setDefaultUri("http://localhost:8080/ws");
    //        client.setMarshaller(marshaller);
    //        client.setUnmarshaller(marshaller);
    //        return client;
    //    }

}
