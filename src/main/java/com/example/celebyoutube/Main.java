package com.example.celebyoutube;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Getter
@Setter
class Node {
    int next;
    int value;

    Node(int next, int value) {
        this.next = next;
        this.value = value;
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. URL 선언
        String connUrl = "https://www.youtube.com/channel/UCnXuSso6ypX2NVrl8_2TwfQ/videos";
        // 2. HTML 가져오기
        Document doc = Jsoup.connect(connUrl).get();

        System.out.println(doc.toString());

        Elements elements = doc.select("meta[property]");

        System.out.println(elements);

        String title = elements.select("meta[property=og:title]").attr("content");
        String content = elements.select("meta[property=og:description]").attr("content");
        String image = elements.select("meta[property=og:image]").attr("content");

        System.out.println(title);
        System.out.println(content);
        System.out.println(image);

        /*List<String> elementList = doc.select("span.about-stat").eachText();

        Long subscriber = Long.valueOf(elementList.get(0).replaceAll("[^0-9]",""));
        System.out.println(subscriber);

        Long views = Long.valueOf(elementList.get(1).replaceAll("[^0-9]",""));
        System.out.println(views);

        String[] joinDateArray = elementList.get(2).replaceAll("[^0-9.]","").split("\\.");
        LocalDate joinDate = LocalDate.of(Integer.valueOf(joinDateArray[0]),Integer.valueOf(joinDateArray[1]),Integer.valueOf(joinDateArray[2]));
        System.out.println(joinDate);
        System.out.println(Arrays.toString(joinDateArray));
*/
    }
}
