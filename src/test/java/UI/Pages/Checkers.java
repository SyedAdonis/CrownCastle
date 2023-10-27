package UI.Pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MiscUtils;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class Checkers {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    By message = By.id("message");
    //Select an orange piece to move.
    By restart = By.xpath("//a[.='Restart...']");
    By checkerH1 = By.xpath("//h1[.='Checkers']");

  public Checkers(WebDriver driver) throws AWTException {
      this.driver = driver;
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      actions = new Actions(driver);
      js = (JavascriptExecutor) driver;
  }

  public void moveAPiece(String fromSpace, String toSpace) {

      System.out.printf("Moving Piece from %s to %s%n", fromSpace, toSpace);
      WebElement fromElement = driver.findElement(By.name(fromSpace));
      WebElement toElement = driver.findElement(By.name(toSpace));
      js.executeScript("arguments[0].click();", fromElement);
      js.executeScript("arguments[0].click();", toElement);
//      actions.moveToElement(driver.findElement(By.name(fromSpace)));
//      actions.click().build().perform();
//      actions.moveToElement(driver.findElement(By.name(toSpace)));
//      actions.click().build().perform();
      // Regular click was getting stuck on "Please wait". so Had to use Actions class. you can try the regular click by enabling the below commands"
      // driver.findElement(By.name(fromSpace)).click();
      // driver.findElement(By.name(toSpace)).click();
  }

  public void restartGame() {
      driver.findElement(restart).click();
      wait.until(ExpectedConditions.textToBe(message, "Select an orange piece to move."));
      MiscUtils.writeInfo("Game Restarted Successfully");
  }

    public void makeAMove() {
        wait.until(ExpectedConditions.textToBe(message, "Make a move."));
        System.out.println("Ready to Make the Next Move");
    }
  public void confirmSiteIsUp() {
      try {
          wait.until(ExpectedConditions.visibilityOfElementLocated(checkerH1));
          MiscUtils.writeInfo("The site is Up");
      } catch (TimeoutException e) {
          throw new NoSuchElementException("The Checkers website is not up");
      }
  }
public void multipleMoves(List<String> moves){

          moves.forEach(n -> {
              String [] move = n.split("-");
              moveAPiece(move[0], move[1]);

              //checking for "Make a move" Text
              makeAMove();

              //Waiting 2 seconds. Only to show the game better
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          });
      }




  }

