package utils;
import java.util.ArrayList;
import java.util.List;

public class CategoryUtils
{
    private static ArrayList<String> cateUtil = new ArrayList<>(
            List.of("Work", "Home", "Holiday", "Hobby", "College"));


    public ArrayList<String> getCateUtil() {
        return cateUtil;
    }

    public static boolean isValid(String Cath) {
        String cathIn = Cath.toUpperCase();

        return cateUtil.contains(cathIn);
    }
}

