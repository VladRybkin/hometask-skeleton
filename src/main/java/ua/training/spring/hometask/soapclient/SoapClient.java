package ua.training.spring.hometask.soapclient;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import ua.training.spring.hometask.dto.soap.request.GetUserByIdRequest;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class SoapClient {

    private static final String message = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
            + "    <SOAP-ENV:Header/>\n"
            + "    <SOAP-ENV:Body>\n"
            + "        <ns3:addUserRequest xmlns:ns3=\"http://training/schemas/hometask\">\n"
            + "            <firstName>test</firstName>\n"
            + "            <lastName>44</lastName>\n"
            + "            <email>test</email>\n"
            + "            <password>soappassword</password>\n"
            + "            <birthDay>2020-11-18T19:04:15.104+02:00</birthDay>\n"
            + "        </ns3:addUserRequest>\n"
            + "    </SOAP-ENV:Body>\n"
            + "</SOAP-ENV:Envelope>";

    public void sentMessage() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("ua.training.spring.hometask.dto.soap");
        webServiceTemplate.setDefaultUri("http://localhost:8888/ws");
        webServiceTemplate.setMarshaller(marshaller);


        StreamSource source = new StreamSource(new StringReader(message));
        StreamResult result = new StreamResult(System.out);
      webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8888/ws/", source, result);
        System.out.println(result);
    }

}
