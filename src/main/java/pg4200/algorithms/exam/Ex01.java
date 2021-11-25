package pg4200.algorithms.exam;
/**
 * Created by Abdullah on 25-Nov-21.
 */
public class Ex01 {
    /**
     * Exercise 01, Point "a":
     * write regular expression starts with pg4200 then _ then number(s) then and ends with .zip
     * @function matchPg4200zipFile
     */
    public String matchZipFilePg4200Regex() {
        return "((pg4200_)?i[0-9]{1,5})[.]zip*";
    }

    /**
     * Exercise 01, Point "b":
     * Match Any sentence contains one of those words ( algorithm, pg4200, algdat )
     * And that  sentence ends with . OR ! OR ?
     *
     */
    public String matchImportantWords() {
        return "(?i)(.* )?(pg4200|algdat|algorithm)( .*)?(\\.|\\!|\\?)$";
    }

}
