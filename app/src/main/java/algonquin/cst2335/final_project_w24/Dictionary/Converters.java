package algonquin.cst2335.final_project_w24.Dictionary;
import androidx.room.TypeConverter;
import java.util.ArrayList;
/**
 * purpose of the file: Specifies how Room should convert my custom data type (ArrayList<String>) to a type that can be stored in the database.
 * author: Tony Nsang
 * lab section: 022
 * creation date: March 28, 2023.
 */
/**
 * Specifies how Room should convert my custom data type (ArrayList<String>) to a type that can be stored in the database.
 */
public class Converters {
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        // Split the string by 1 to convert it back to ArrayList<String>
        String[] array = value.split("1");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String item : array) {
            arrayList.add(item);
        }
        return arrayList;
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        // Convert ArrayList<String> to 1-separated String
        StringBuilder value = new StringBuilder();
        for (String item : list) {
            value.append(item);
            value.append("1");
        }
        return value.toString();
    }
}
