package pl.edu.agh.wiet.studiesplanner.solver;

import org.springframework.stereotype.Service;
import pl.edu.agh.wiet.studiesplanner.model.data.*;
import pl.edu.agh.wiet.studiesplanner.model.solver.Conflict;

import java.util.*;
import java.util.function.Function;

@Service
public class ConflictSolver {

    public Set<Conflict> solve(Iterable<Convention> conventions) {
        Set<Conflict> conflicts = new HashSet<>();
        conflicts.addAll(solveConflictsByCriterion(conventions, Activity::getTeacher));
        conflicts.addAll(solveConflictsByCriterion(conventions, Activity::getClassroom));
        conflicts.addAll(solveConflictsByCriterion(conventions, Activity::getStudentsGroup));

        return conflicts;
    }

    private Set<Conflict> solveConflictsByCriterion(Iterable<Convention> conventions,
                                                    Function<Activity, ConflictCriterion> criterionProducer) {
        final Map<ConflictCriterion, List<TimeBlock>> blocksByCriteria = new HashMap<>();
        final Set<Conflict> conflicts = new HashSet<>();
        for(Convention convention : conventions) {
            for(TimeBlock timeBlock : convention.getTimeBlocks()) {
                for(Activity activity : timeBlock.getActivityList()) {
                    ConflictCriterion key = criterionProducer.apply(activity);
                    if(blocksByCriteria.containsKey(key)) {
                        blocksByCriteria.get(key).add(timeBlock);
                    } else {
                        blocksByCriteria.put(criterionProducer.apply(activity),
                                new LinkedList<>(Collections.singletonList(timeBlock)));
                    }
                }
            }
        }

        for(Map.Entry<ConflictCriterion, List<TimeBlock>> entry : blocksByCriteria.entrySet()) {
            ConflictCriterion key = entry.getKey();
            List<TimeBlock> timeBlocks = entry.getValue();
            for(int i = 0; i < timeBlocks.size(); i++) {
                for(int j = i + 1; j < timeBlocks.size(); j++) {
                    TimeBlock firstBlock = timeBlocks.get(i);
                    TimeBlock secondBlock = timeBlocks.get(j);
                    if(firstBlock.isOverlappingWith(secondBlock)) { // conflict
                        conflicts.add(key.createConflict(new HashSet<>(Arrays.asList(firstBlock, secondBlock))));
                    }
                }
            }
        }

        return conflicts;
    }
}
