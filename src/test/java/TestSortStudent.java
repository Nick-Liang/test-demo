import com.nick.demo.SortStudent;
import com.nick.demo.TimeElapsedUtil;
import org.junit.Test;

public class TestSortStudent {
    @Test
    public void testSort(){
        TimeElapsedUtil util = new TimeElapsedUtil();
        SortStudent sortStudent = util.getProxy(SortStudent.class, "sort");
        sortStudent.sort("StudentScore.txt");
    }
}
