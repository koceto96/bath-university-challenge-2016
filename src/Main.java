import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;
import java.io.*;

public class Main {

    Scanner sc;
    ArrayList<String> oldValues;
    ArrayList<String> newValues;

    public Main() {
        sc = new Scanner(System.in);
        oldValues = new ArrayList<>();
        newValues = new ArrayList<>();
    }

    public static void main(String[] args) {
        Main main = new Main();
	    System.out.printf("1. Edit a web page%n2. Editing report%n3. Exit application%n");
        String input = main.sc.nextLine();
        while (!input.equals("\\d+") && Integer.parseInt(input) > 3 && Integer.parseInt(input) < 1) {
            input = main.sc.nextLine();
        }
        main.options(Integer.parseInt(input));

    }

    public void options(int a){
        switch (a) {
            case 1:
                enterWebPage();
                break;
            case 2:
                editReport();
                break;
            case 3:
                exitApp();
                break;
        }

    }

    void enterWebPage() {
        StringBuffer sb = new StringBuffer("");
        System.out.println("Enter a web page URL: ");
        String input = sc.nextLine();

        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL(input);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException mue) {
            System.err.println("!");
        } catch (IOException ioe) {
            System.err.println("!!");
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
                System.err.println("!!!");
            }
        }
        String output1 = sb.toString();
        String title = output1.substring(output1.indexOf("<title>")+7,
                output1.indexOf("</title>"));
        System.out.println("title: " + title);
        String meta = output1.substring(output1.indexOf("<meta name=\"description\" content=\"") +
                        "<meta name=\"description\" content=\"".length() ,
                output1.indexOf("\" />",output1.indexOf("<meta name=\"description\" content=\"")));
        System.out.println("meta: " + meta);
        String keyWords = output1.substring(output1.indexOf("<meta name=\"keywords\" content=\"") +
                        "<meta name=\"keywords\" content=\"".length() ,
                output1.indexOf("\" />",output1.indexOf("<meta name=\"keywords\" content=\"")));
        System.out.println("keywords: " + keyWords);
        oldValues.add(title);
        oldValues.add(meta);
        oldValues.add(keyWords);

        System.out.printf("1. Edit title%n2. Edit content description %n3. Exit keywords%n");
        String input2 = sc.nextLine();
        while (!input.equals("\\d+") && Integer.parseInt(input) > 3 && Integer.parseInt(input) < 1) {
            input2 = sc.nextLine();
        }
        switch (Integer.parseInt(input2)){
            case 1:
                System.out.println("Enter new title");
                title = sc.nextLine();
                break;
            case 2:
                System.out.println("Enter new description");
                meta = sc.nextLine();
                break;
            case 3:
                System.out.println("Enter new keyWords");
                keyWords = sc.nextLine();
                break;
        }
        newValues.add(title);
        newValues.add(meta);
        newValues.add(keyWords);

    }

    void editReport() {

    }

    void exitApp() {

    }

}
