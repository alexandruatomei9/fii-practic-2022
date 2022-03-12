package ro.info.iasi.fiipractic.qualifiers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("barFormatter")
@Primary
public class BarFormatter implements Formatter {

    public String format() {
        return "bar";
    }
}
