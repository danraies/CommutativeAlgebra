package danraies.commutativealgebra;
import java.io.PrintStream;
import java.util.ArrayList;

class FactoryLogger {
    boolean showVerboseOutput = true;
    PrintStream log = System.out;
    ArrayList<TestResult> testResults = new ArrayList<TestResult>();

    void shouldShowVerboseOutput(boolean showVerboseOutput) {
        this.showVerboseOutput = showVerboseOutput;
    }

    void setPrintStream(PrintStream stream) {
        this.log = stream;
    }
    
    void announceStart() {
        
    }
    
    void logEnd() {
        
    }

    void announceAxiomCheck(String testName) {
        
    }

    void logAxiomResult(TestResult result) {
        
    }

    void logIndividualCheck(int testCounter,
                            Element[] listOfElements,
                            boolean passedThisCheck) {
        
    }
}
