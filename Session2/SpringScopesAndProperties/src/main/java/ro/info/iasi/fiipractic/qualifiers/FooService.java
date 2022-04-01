package ro.info.iasi.fiipractic.qualifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;

    public void format() {
        System.out.println(formatter.format());
    }
}
