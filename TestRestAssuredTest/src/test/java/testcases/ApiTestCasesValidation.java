package testcases;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Book;

import java.util.ArrayList;
import java.util.List;

public class ApiTestCasesValidation {

    @Test
    public void verifyGetTestMethod() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");
        //System.out.println(response.asString());
        System.out.println(response.prettyPrint());
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");

    }

    @Test
    public void verifyHeaderDetails() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");
        //System.out.println(response.asString());

        Headers headers = response.getHeaders();

        for (Header header : headers) {
            System.out.println(header.getName() + " : " + header.getValue());
        }

        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");

    }

    @Test
    public void verifyGetTestResponseBody() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");
        //System.out.println(response.asString());
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.prettyPrint());

    }


    @Test
    public void verifyGetResponseBody() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");
        //System.out.println(response.asString());

        String actualResponse = response.asString();

        Assert.assertTrue(actualResponse.contains("9781449325862"), "isbn number is not present in reponse");

    }

    @Test
    public void verifyNumberOfPagesWithJsonPath() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");

        JsonPath jsonPath = response.jsonPath();
        int pages = jsonPath.get("books[1].pages");
        String description = jsonPath.get("books[1].description");


        System.out.println("pages : " + pages);
        System.out.println("description : " + description);

        Assert.assertEquals(pages, 254);
        Assert.assertEquals(description.trim(), "With Learning JavaScript Design Patterns, you'll learn how to write beautiful, structured, and maintainable JavaScript by applying classical and modern design patterns to the language. If you want to keep your code efficient, more manageable, and up-to-da");

    }


    @Test
    public void verifyListOfTitles() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");

        JsonPath jsonPath = response.jsonPath();
        List<String> titleList = jsonPath.getList("books.title");

        List<String> expectedList = new ArrayList<>();
        expectedList.add("Git Pocket Guide");
        expectedList.add("Learning JavaScript Design Patterns");
        expectedList.add("Designing Evolvable Web APIs with ASP.NET");
        expectedList.add("Speaking JavaScript");
        expectedList.add("You Don't Know JS");
        expectedList.add("Programming JavaScript Applications");
        expectedList.add("Eloquent JavaScript, Second Edition");
        expectedList.add("Understanding ECMAScript 6");

        for (String title : titleList) {
            System.out.println(title);
        }

        Assert.assertTrue(titleList.containsAll(expectedList));
    }

    @Test
    public void verifyDeserilizetoBookObject() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");

        JsonPath jsonPath = response.jsonPath();

        List<Book> allBooks = jsonPath.getList("books", Book.class);

        List<String> expectedList = new ArrayList<>();
        expectedList.add("Git Pocket Guide");
        expectedList.add("Learning JavaScript Design Patterns");
        expectedList.add("Designing Evolvable Web APIs with ASP.NET");
        expectedList.add("Speaking JavaScript");
        expectedList.add("You Don't Know JS");
        expectedList.add("Programming JavaScript Applications");
        expectedList.add("Eloquent JavaScript, Second Edition");
        expectedList.add("Understanding ECMAScript 6");
        for (Book book : allBooks) {
            System.out.println("isbn : " + book.getIsbn());
            System.out.println("title : " + book.getTitle());
            Assert.assertTrue(expectedList.contains(book.getTitle()));
        }

    }

}
