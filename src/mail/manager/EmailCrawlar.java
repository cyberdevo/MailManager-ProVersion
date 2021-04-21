/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail.manager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Haier
 */
public class EmailCrawlar {

    public ArrayList<String> emailScrapper(String weburl) {
        // TODO code application logic here
        String givenURL = weburl;
        String authority = null; // host name	

        {
            try {
   
                URL mainURL = new URL(givenURL);
                authority = mainURL.getAuthority();
            } catch (MalformedURLException ex) {
                Logger.getLogger(EmailCrawlar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   
        String regex = "[a-zA-Z0-9\\\\.\\\\-\\\\_]+@[a-zA-Z]+[\\\\.]{1}[a-zA-Z]{2,4}";
        Pattern pattern = Pattern.compile(regex);

        ArrayList<String> listOfURL = new ArrayList<String>();
        ArrayList<String> listOfEmail = new ArrayList<String>();
        listOfURL.add(givenURL);

        for (int i = 0; i < 50; i++) {
            try {
                //...
                givenURL = listOfURL.get(i);
                Document doc = Jsoup.connect(givenURL).get();
//                JOptionPane.showMessageDialog(null , doc);
                String siteText = doc.text();
//                  JOptionPane.showMessageDialog(null , siteText);
                Matcher matcher = pattern.matcher(siteText);
                while (matcher.find()) {
                    if (!listOfEmail.contains(matcher.group())) {
                        listOfEmail.add(matcher.group());
                    }
                }

                Elements scrapedUrls = doc.select("a[href]");
                for (Element tag_a : scrapedUrls) {
                    String str = tag_a.attr("abs:href");
                    URL url = new URL(str);
                    if (authority.equals(url.getAuthority())) {
                        if (!listOfURL.contains(str)) {
                            listOfURL.add(str);
                        }
                    }
                }

                System.out.print(" --- Found links (" + scrapedUrls.size() + ") ");
                System.out.println("Saved links (" + listOfURL.size() + ") ");

            } catch (IOException ex) {
                Logger.getLogger(EmailCrawlar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        System.out.println("Total links : " + listOfURL.size());
        System.out.println("Total Email Address : " + listOfEmail.size());
        System.out.println("Total Email Address : " + listOfEmail);
        return listOfEmail;
    }
}
