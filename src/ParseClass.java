import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseClass {

    public void parseMethod(File[] fileList) {
        CreateExcelFile createExcelfile = new CreateExcelFile();
        BufferedReader bf = null;
        String line = "";
        List<String> messageTagNameList = new ArrayList<>();
        Map<String, String> enMap = new HashMap<>();
        Map<String, String> tonMap = new HashMap<>();

        try {
            for (File file : fileList) {
                if (file.getName().equals("message_en_US.properties")) {
                    System.out.println("------------------------ message_en -----------------------");
                    bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

                    while ((bf.readLine()) != null) {
                        line = bf.readLine();

                        if (!(line.indexOf("#") == 0) && !line.equals("")) {
                            messageTagNameList.add(line.split("=")[0]);
                            //enList.add(line.split("=")[1]);
                            enMap.put(line.split("=")[0], line.split("=")[1]);
                        } else if (line.indexOf("#") == 0) {
                            messageTagNameList.add(line);
                        }
                    }
                    System.out.println("message_en success");
                } else {
                    System.out.println("------------------------ message_ton -----------------------");
                    bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

                    while ((bf.readLine()) != null) {
                        line = bf.readLine();
                        String tagName = "";

                        if (!(line.indexOf("#") == 0) && !line.equals("")) {
                            tagName = line.split("=")[0];
                            if (messageTagNameList.contains(tagName)) {
                                //tonList.add(line.split("=")[1]);
                                tonMap.put(tagName, line.split("=")[1]);
                            } else {
                                System.out.println("fail tagName : " + tagName);
                            }
                        }
                    }
                    System.out.println("message_ton success");
                }
            }

            System.out.println("list setting end");

            createExcelfile.createWorkbook(messageTagNameList, enMap, tonMap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
