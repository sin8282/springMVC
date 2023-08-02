package hello.itemservice.web.typeconverter;

import hello.itemservice.web.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        log.info("convert source={}", source);
        String[] result = source.split(":");
        String ip = result[0];
        int port = Integer.parseInt(result[1]);

        return new IpPort(ip, port);
    }
}
