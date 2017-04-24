
public class FactoryMethod {

    public static Theme getTheme(String type) {
        Theme themeFactory = null;
        if (type == "Classic") {
            themeFactory = new ThemeClassic();
        } else if (type == "Dargula") {
            themeFactory = new ThemeBlack();
        }
        return themeFactory;
    }

}
