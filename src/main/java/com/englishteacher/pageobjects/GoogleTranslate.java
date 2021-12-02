package com.englishteacher.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleTranslate extends Page {

    @FindBy(xpath = "//textarea")
    private WebElement textBox;

    @FindBy(xpath = "(//span[normalize-space()='volume_up']/ancestor::button)[1]")
    private WebElement listenButton;

    //TODO: Check if button labels work with Hungarian localization. Fix if does not work.
    @FindBy(css = "button[aria-label='Listen to source text'][style]")
    private WebElement clickToListen;

    @FindBy(css = "button[aria-label='Stop listening'][style]")
    private WebElement clickToStop;

    public GoogleTranslate(WebDriver driver) {
        super(driver);
    }

    public void enterText(String text) {
        typeText(textBox, text);
    }

    public void clickListen() {
        click(listenButton);
        waitUntilElementVisible(clickToStop);
        waitUntilElementVisible(clickToListen);
    }
}