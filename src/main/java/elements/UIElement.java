package elements;

import org.openqa.selenium.*;
import core.BrowsersService;
import utils.Waiters;

import java.util.List;

public class UIElement implements WebElement {
    private final BrowsersService browsersService;
    private final WebElement webElement;
    private final Waiters waiters;

    public UIElement(BrowsersService browsersService, WebElement webElement) {
        this.browsersService = browsersService;
        waiters = browsersService.getWaiters();

        this.webElement = webElement;
    }

    public UIElement(BrowsersService browsersService, By by) {
        this.browsersService = browsersService;
        waiters = browsersService.getWaiters();

        this.webElement = waiters.presenceOfElementLocated(by);
    }

    @Override
    public void click() {
        try {
            webElement.click();
        } catch (ElementNotVisibleException e) {
            ((JavascriptExecutor) browsersService.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
            waiters.waitForClickable(webElement);
            webElement.click();
        }
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        ((JavascriptExecutor) browsersService.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
        webElement.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return webElement.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public elements.UIElement findElement(By by) {
        return new elements.UIElement(browsersService, webElement.findElement(by));
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return webElement.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return webElement.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return webElement.getScreenshotAs(outputType);
    }

    public elements.UIElement getParent() {
        JavascriptExecutor executor = (JavascriptExecutor) browsersService.getDriver();
        return new elements.UIElement(browsersService, (WebElement)executor.executeScript("return arguments[0].parentNode;", webElement));
    }
}
