import com.nick.demo.IPValidationRegex;
import com.nick.demo.SortStudent;
import com.nick.demo.TimeElapsedUtil;
import org.junit.Test;

public class TestIPValidationRegex {
    @Test
    public void testFilterIPAddresses(){
        TimeElapsedUtil util = new TimeElapsedUtil();
        IPValidationRegex ipValidationRegex = util.getProxy(IPValidationRegex.class, "filterIPAddresses");
        ipValidationRegex.filterIPAddresses("IPList.txt");
    }
}
