package recipe.android.vogorecipe.utilities;

/**
 * Created by lorence on 6/9/2018.
 * @author lorence
 */

public class Utils {

    private static long sLastClickTime = 0;

    public static boolean isDoubleClick() {
        long clickTime = System.currentTimeMillis();
        if (clickTime - sLastClickTime < 500) {
            sLastClickTime = clickTime;
            return true;
        }
        sLastClickTime = clickTime;
        return false;
    }

}
