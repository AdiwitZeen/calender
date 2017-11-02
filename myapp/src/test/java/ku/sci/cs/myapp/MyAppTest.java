//Khanathit Phattarapanich 5710450103

package ku.sci.cs.myapp;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyAppTest {
	
	Controller control = new Controller();
	
	@Test
	public void calendarTest() {
		//fail("Not yet implemented");
		Calendar c = new Calendar(1," 27/8/2017","18:00","meeting");
		String result = c.getNumber()+c.getDate()+" - "+c.getTime()+" - "+c.getDetail();
		assertEquals("1 27/8/2017 - 18:00 - meeting", result);
	}
}
