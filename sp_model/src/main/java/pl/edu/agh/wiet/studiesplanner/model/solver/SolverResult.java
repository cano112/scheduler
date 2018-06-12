package pl.edu.agh.wiet.studiesplanner.model.solver;

public class SolverResult {
    private final String resultString;
    private final int conflictsCount;
    private final int warningsCount;

    public SolverResult(String resultString, int conflictsCount, int warningsCount) {
        this.resultString = resultString;
        this.conflictsCount = conflictsCount;
        this.warningsCount = warningsCount;
    }

    public String getResultString() {
        return resultString;
    }

    public int getConflictsCount() {
        return conflictsCount;
    }

    public int getWarningsCount() {
        return warningsCount;
    }
}
