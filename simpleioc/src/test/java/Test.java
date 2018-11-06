import com.angrycow1111.simpleioc.core.JsonApplicationContext;
import com.angrycow1111.simpleioc.entity.Robot;

public class Test {

    public static void main(String[] args) throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot robot = (Robot) applicationContext.getBean("robot");
        robot.show();

    }
}
