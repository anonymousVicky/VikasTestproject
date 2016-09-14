package Actions;

import org.openqa.selenium.By;

public class ElementLocator {
	public By by;

	public By Elements(String locator) {
		String prefix = locator.substring(0, 2);
		switch (prefix) {
		case "ID":
			by = By.id(locator.substring(3, locator.length()));
			break;
		case "XP":
			by = By.xpath(locator.substring(3, locator.length()));
			break;
		case "CS":
			by = By.cssSelector(locator.substring(3, locator.length()));
			break;
		case "NM":
			by = By.name(locator.substring(3, locator.length()));
			break;
		case "CN":
			by = By.className(locator.substring(3, locator.length()));
			break;
		case "LT":
			by = By.linkText(locator.substring(3, locator.length()));
			break;
		case "TN":
			by = By.tagName(locator.substring(3, locator.length()));
			break;
		case "PL":
			by = By.partialLinkText(locator.substring(3, locator.length()));
		default:
			System.out.println("Invalid choice of element identifier. Please select appropriate element identfier or make sure you have used correct abbreviation in property file.");
		}
		return (by);
	}
}
