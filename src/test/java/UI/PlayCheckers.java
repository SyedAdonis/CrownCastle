package UI;

import Parameters.BaseClass;
import UI.Pages.Checkers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class PlayCheckers extends BaseClass {
    WebDriver driver;
    Checkers checkers;
    @BeforeClass
    public void beforeClass() throws AWTException {
        driver = new ChromeDriver();
        checkers = new Checkers(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void theCheckers(){

        // Navigate to "https://www.gamesforthebrain.com/game/checkers/"
        driver.get(webHost);

        // Confirm that the site is up
        checkers.confirmSiteIsUp();

        // 5 Legal moves.
        List<String> moves = List.of("space62-space53", "space42-space33", "space51-space33", "space71-space62", "space31-space53");
        checkers.multipleMoves(moves);

        // Restart Game and confirm Restart Successful
        checkers.restartGame();

        //Quit browser. Disabled because not in the steps
        //driver.quit();


    }
}
