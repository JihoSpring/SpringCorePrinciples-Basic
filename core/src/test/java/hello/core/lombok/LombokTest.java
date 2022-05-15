package hello.core.lombok;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

class LombokTest {

    @Getter
    @RequiredArgsConstructor
    @ToString
    static class LombokClass {
        private final String name;
        private final int age;
    }

    @Test
    void testLombok() {
        LombokClass lombok = new LombokClass("name", 10);
        Assertions.assertThat(lombok.getAge()).isEqualTo(10);
        Assertions.assertThat(lombok.getName()).isEqualTo("name");
    }

}
