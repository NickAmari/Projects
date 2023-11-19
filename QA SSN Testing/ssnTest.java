import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class h2qu2Test {

    @Test
    void main() {
    }

    @Test
    void validateSSN() {
        //Testing for valid length and parts
        assertEquals("valid ssn", h2qu2.validateSSN("111-11-1111"));//should pass
        //assertEquals("invalid ssn", hw2Question2.validateSSN("111-11-111"));//less than 9 PROBLEM
        assertEquals("invalid ssn", h2qu2.validateSSN("111-11-11111"));//more than 9
        assertEquals("Incorrect format", h2qu2.validateSSN("111-111111"));//less than 3 parts
        assertEquals("Incorrect format", h2qu2.validateSSN("111-11-11-11"));//more than 3 parts

        // Testing for First Part digit correctness
        assertEquals("valid ssn", h2qu2.validateSSN("111-11-1111"));//3 digits
        //assertEquals("invalid ssn", hw2Question2.validateSSN("1-11-1111"));// less than 3 PROBLEM
        //assertEquals("invalid ssn", hw2Question2.validateSSN("1111-11-1111"));//more than 3 PROBLEM
        assertEquals("invalid ssn", h2qu2.validateSSN("000-11-1111"));// first part = 000
        assertEquals("invalid ssn", h2qu2.validateSSN("666-11-1111"));// first part = 666
        assertEquals("invalid ssn", h2qu2.validateSSN("950-11-1111"));// first part between 900-999

        // Testing for Second Part digit correctness
        assertEquals("valid ssn", h2qu2.validateSSN("111-11-1111"));//2 digits
        assertEquals("invalid ssn", h2qu2.validateSSN("111-1-1111"));//less than 2
        assertEquals("invalid ssn", h2qu2.validateSSN("111-111-1111"));// more than 2
        assertEquals("invalid ssn", h2qu2.validateSSN("111-00-1111"));// second part between 01-99

        // Testing for Third Part digit correctness
        assertEquals("valid ssn", h2qu2.validateSSN("111-11-1111"));// 4 digits
        //assertEquals("invalid ssn", hw2Question2.validateSSN("111-11-111"));// less than 4 PROBLEM
        assertEquals("invalid ssn", h2qu2.validateSSN("111-11-11111"));// more than 4
        assertEquals("invalid ssn", h2qu2.validateSSN("111-11-0000"));// third part between 0001-9999

        /* PROBLEM marked test cases are producing wrong output for 2 reasons:
            Defect 1. Will be valid if there are less than 9 digits, but the second part is correct.
                ex. 1-11-1 counts as valid
            Defect 2. The first part can be wrong, as long as the second and third parts are correct.
                ex. 1-11-1111 counts as valid
                ex. 11111-11-1111 counts as valid
                ex. 11111-11-11111 counts as invalid because the third part is wrong
                ex. 11111-111-1111 counts as invalid because the second part is wrong
         */
    }
}