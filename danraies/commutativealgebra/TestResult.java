package danraies.commutativealgebra;

class TestResult {
    private String testName;
    private boolean passed;
    private String counterExample;

    TestResult(String testName, boolean passed) {
        this.testName = testName;
        this.passed = passed;
        counterExample = null;
    }

    boolean passed() {
        return passed;
    }

    void setCounterExample(String counterExample) {
        this.counterExample = counterExample;
    }

    void setCounterExample(Element[] list) {
        counterExample = "(";
        for (int i = 0; i < list.length; i++) {
            counterExample = counterExample + list[i];
            if (i < (list.length - 1)) {
                counterExample = counterExample + ", ";
            }
        }
        counterExample = counterExample + ")";
    }

    String testResultLogEntry() {
        String logEntry = "";
        if (passed) {
            logEntry = "(---PASSED---) " + testName;
        } else {
            logEntry = "(***FAILED***) " + testName;
            if (counterExample != null) {
                logEntry = logEntry + "\n\t(Counter Example) " + counterExample;
            }
        }
        return logEntry;
    }
    
    public static void main(String[] args) {
        TestResult t1 = new TestResult("Test 1", true);
        TestResult t2 = new TestResult("Test 2", false);
        TestResult t3 = new TestResult("Test 3", false);
        t3.setCounterExample("1/2 and 3/4");
        System.out.println(t1.testResultLogEntry());
        System.out.println(t2.testResultLogEntry());
        System.out.println(t3.testResultLogEntry());
    }
}
