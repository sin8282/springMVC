package hello.itemservice.web.typeconverter;

import hello.itemservice.web.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.text.ParseException;
import java.util.Locale;

class FormatterServiceTest {

   @Test
    void formatterTest(){

       DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

       conversionService.addConverter(new StringToIpPortConverter());
       conversionService.addConverter(new IpPortToStringConverter());

       conversionService.addFormatter(new MyNumberFormatter());

       IpPort ipPort = new IpPort("127.0.0.1", 8080);
       Assertions.assertThat(ipPort).isEqualTo(conversionService.convert("127.0.0.1:8080", IpPort.class));

       Assertions.assertThat("1,000").isEqualTo(conversionService.convert(1000, String.class));
       Assertions.assertThat(conversionService.convert("1,000",Integer.class)).isEqualTo(1000);

   }
}