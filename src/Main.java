import java.io.File;

public class Main {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        File[] fileList = new File(path + "/" + "properties/").listFiles();

        ParseClass parseClass = new ParseClass();
        parseClass.parseMethod(fileList);



    }
}
