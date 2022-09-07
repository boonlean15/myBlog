import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.FixedLengthBlockOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.geom.Point2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author linch
 * @create 2022/2/22 16:29
 */
//@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class JavaSecondTest {

    @Test
    public void testStreamFilter(){
        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("python");
        long count = words.stream().filter(x -> x.length() > 4).count();
        log.info("count ----------- " + count);
    }

    @Test
    public void testStreamPeek(){
        Stream<Double> peek = Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println("fetching " + e));
        Optional<Double> first = peek.findFirst();
        Double aDouble = first.get();
        log.info("aDouble ------ > " + aDouble);
    }

    @Test
    public void testStreamOptional() throws IOException {
        Stream<Double> optional = Stream.iterate(1.0, p -> p * 2).limit(100);
        Optional<Double> first = optional.findFirst();
        Optional<Double> aDouble = first.map(v -> v * 2);
        Double aDouble1 = aDouble.get();
        log.info("aD ----- " + aDouble1);
    }


    @Test
    public void testStreamMapping() throws IOException {
        Stream<Locale> availableLocales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> collect = availableLocales.collect(Collectors.groupingBy(Locale::getDisplayCountry,
                Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));

        log.info("collect ------- " + collect);
        availableLocales = Stream.of(Locale.getAvailableLocales());
        availableLocales.forEach(l -> {
            String displayCountry = l.getDisplayCountry();
            String displayLanguage = l.getDisplayLanguage();
            log.info("displayCountry --- " + displayCountry);
            log.info("displayLanguage --- " + displayLanguage);
        });
        
    }




}
