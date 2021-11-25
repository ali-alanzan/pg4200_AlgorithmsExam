package pg4200.algorithms.exam.ex01;

import org.junit.jupiter.api.Test;
import pg4200.algorithms.exam.Ex01;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ex01WordsTest {
    Ex01 pattern = new Ex01();
    String regex = pattern.matchImportantWords();
    @Test
    void testInvalidSentence() {
        assertFalse("algorithm".matches(regex));
        assertFalse(" algorithm ".matches(regex));
        assertFalse("Start algorithm End".matches(regex));
        assertFalse("Check ? algorithm ".matches(regex));
        assertFalse("Check ? algorithm ".matches(regex));
        assertFalse("Check if this ? algdat is valid".matches(regex));
        assertFalse("algdat Check ? pg400 ".matches(regex));
        assertFalse("algdat Check ? pg400 ".matches(regex));
    }

    @Test
    void testValidSentenceWithNoWords() {
        assertFalse("".matches(regex));
        assertFalse("algori,, any word?".matches(regex));
        assertFalse("Start End!".matches(regex));
        assertFalse("Check algo... ".matches(regex));
        assertFalse("Check if any ? ".matches(regex));
        assertFalse("Check if it is valid? ".matches(regex));
    }


    @Test
    void testValidSentenceWithMatterWords() {
        assertTrue("is pg4200 best college in the tech industry in NR?".matches(regex));
        assertTrue("Anyway I didn't understand what is algdat!".matches(regex));
        assertTrue("What About Algorithm?".matches(regex));
        assertTrue("Ok I will study it with algdat.".matches(regex));
    }
}
