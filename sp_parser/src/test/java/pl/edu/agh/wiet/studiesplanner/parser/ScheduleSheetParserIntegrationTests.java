package pl.edu.agh.wiet.studiesplanner.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.agh.wiet.studiesplanner.model.data.*;

import java.util.List;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ScheduleSheetParserIntegrationTests {

    @Autowired
    private Schedule schedule;

    @Test
    public void parsedListSizeShouldBeEight() {
        Assert.assertEquals(8, schedule.getConventions().size());
    }

    @Test
    public void allConventionsShouldHaveTimeBlocks(){
        for(Convention convention : schedule.getConventions()){
            Assert.assertNotEquals(0, convention.getTimeBlocks().size());
        }
    }

//    @Test
//    public void fourLastConventionsShouldHaveNoActivitiesInTimeBlocks(){
//        for(int i=4;i<8;i++){
//            Convention convention = schedule.getConventions().get(i);
//            for(TimeBlock timeBlock : convention.getTimeBlocks()){
//                Assert.assertEquals(0, timeBlock.getActivityList().size());
//            }
//        }
//    }
//
//    @Test
//    public void firstThreeConventionsShouldHaveTwoActivitiesInEveryTimeBlock(){
//        for(int i=0;i<3;i++){
//            Convention convention = schedule.getConventions().get(i);
//            for(TimeBlock timeBlock : convention.getTimeBlocks()){
//                Assert.assertEquals(2, timeBlock.getActivityList().size());
//            }
//        }
//    }
//
//    @Test
//    public void fourthConventionShouldHaveOnlyOneActivityInFirstTwoTimeBlocksAndTwoInThirdAndForth(){
//        List<TimeBlock> timeBlocks = schedule.getConventions().get(3).getTimeBlocks();
//        Assert.assertEquals(1, timeBlocks.get(0).getActivityList().size());
//        Assert.assertEquals(1, timeBlocks.get(1).getActivityList().size());
//        Assert.assertEquals(2, timeBlocks.get(2).getActivityList().size());
//        Assert.assertEquals(2, timeBlocks.get(3).getActivityList().size());
//    }
//
//    @Test
//    public void firstThreeConventionsShouldHaveLabAsEveryActivity(){
//        for(int i=0;i<3;i++){
//            Convention convention = schedule.getConventions().get(i);
//            for(TimeBlock timeBlock : convention.getTimeBlocks()){
//                for(Activity activity : timeBlock.getActivityList()){
//                    Assert.assertEquals(ActivityType.L, activity.getType());
//                }
//            }
//        }
//    }
//
//    @Test
//    public void fourthConventionShouldHaveLectureInFirstTwoTimeBlocksAndTwoLabsInThirdAndForth() {
//        List<TimeBlock> timeBlocks = schedule.getConventions().get(3).getTimeBlocks();
//        Assert.assertEquals(ActivityType.W, timeBlocks.get(0).getActivityList().get(0).getType());
//        Assert.assertEquals(ActivityType.W, timeBlocks.get(1).getActivityList().get(0).getType());
//        for (int i = 2; i < 4; i++) {
//            TimeBlock timeBlock = timeBlocks.get(i);
//            for (Activity activity : timeBlock.getActivityList()) {
//                Assert.assertEquals(ActivityType.L, activity.getType());
//            }
//        }
//
//    }

}
