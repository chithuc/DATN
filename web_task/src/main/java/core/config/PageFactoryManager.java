package core.config;

public class PageFactoryManager {
    private PageFactoryManager() {
    }

    public static <TPage> TPage get(Class<TPage> pageClass) {
        Object page = null;

        try {
            page = pageClass.newInstance();
        } catch (InstantiationException var3) {
            var3.printStackTrace();
        } catch (IllegalAccessException var4) {
            var4.printStackTrace();
        }

        return (TPage) page;
    }

}
