package optional;

import java.util.Optional;

public class TestOptionalArg {


    static void test(String id ){

        Optional<String> optional = Optional.ofNullable(id);
        id = optional.orElse("1005").toString();
        //Person personNew = person.orElse(new Person());
        System.out.println(id);

    }

    public static void main(String[] args) {

        test(null);
        test("1000");
        test(null);
    }
}
