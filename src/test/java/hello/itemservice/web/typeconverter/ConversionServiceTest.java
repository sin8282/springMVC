package hello.itemservice.web.typeconverter;

import hello.itemservice.web.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void 컨버젼서비스테스트(){
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());

        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10,String.class)).isEqualTo("10");
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        assertThat(conversionService.convert(ipPort,String.class)).isEqualTo("127.0.0.1:8080");
        assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo(ipPort);

    }
}
