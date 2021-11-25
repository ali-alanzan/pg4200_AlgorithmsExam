package pg4200.algorithms.exam.ex01;

import org.junit.jupiter.api.Test;
import pg4200.algorithms.exam.Ex01;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Abdullah on 25-Nov-21.
 */
public class Ex01FileZipTest {
    Ex01 pattern = new Ex01();
    String regex = pattern.matchZipFilePg4200Regex();
    @Test
    void testInvalidFileName() {
        assertFalse("".matches(regex));
        assertFalse("testname.zip".matches(regex));
        assertFalse("testname_123.zip".matches(regex));
        assertFalse("testname_123.zip".matches(regex));
        assertFalse("pg4200123.zip".matches(regex));
        assertFalse("pg4200_a123.zip".matches(regex));
        assertFalse("pg4200_123a.zip".matches(regex));
        assertFalse("pg4200_123_.zip".matches(regex));
        assertFalse("pg4200__123.zip".matches(regex));
        assertFalse("pg4200_123zip".matches(regex));
    }


    @Test
    void testInvalidFileExtension() {
        assertFalse("".matches(regex));
        assertFalse("pg4200_123.rar".matches(regex));
        assertFalse("pg4200_123.zip.rar".matches(regex));
        assertFalse("pg4200_123.txt".matches(regex));
        assertFalse("pg4200_123.zip.txt".matches(regex));
        assertFalse("pg4200_123.rar.zip".matches(regex));
    }

    @Test
    void testValidFile() {
        assertTrue("pg4200_123.zip".matches(regex));
        assertTrue("pg4200_12345.zip".matches(regex));
        assertTrue("pg4200_123.zip".matches(regex));
        assertTrue("pg4200_12345.zip".matches(regex));
        assertTrue("pg4200_00000.zip".matches(regex));
        assertTrue("pg4200_0.zip".matches(regex));
    }


}
