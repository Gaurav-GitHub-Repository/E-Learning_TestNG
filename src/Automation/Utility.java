package Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Utility {
        //Upload file using robot class
	public void uploadfile() throws AWTException
	{
		Robot robot = new Robot();
        StringSelection file = new StringSelection("C:\\Users\\Gaurav\\Desktop\\Photo\\Photo.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
}
