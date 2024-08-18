import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase1 {

    @Test
    public void testGet() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";

        // Get the RequestSpecification of the request that is to be sent
        // to the server.
        RequestSpecification httpRequest = RestAssured.given();

        // Call RequestSpecification.get() method to get the response.
        // Make sure you specify the resource name.
        Response response = httpRequest.get("");

        // Response.asString method will directly return the content of the body
        // as String.
        System.out.println("Response Body is =>  " + response.asString());

        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test
    public void validateHeader() {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");
        // Get all the headers and then iterate over allHeaders to print each header
        Headers allHeaders = response.headers();
        // Iterate over all the Headers
        for (Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    @Test
    public void booksMessageBody() {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        //System.out.println("Response Body is: " + body.asString());

        //System.out.println("final result : " + body.asString().contains("Understanding ECMAScript 6"));

        //Assert.assertTrue(body.asString().contains("Understanding ECMAScript 6"), "book is not present");
        System.out.println("Response Body is: " + body.prettyPrint());
    }

    @Test
    public void VerifyTitleInJsonResponse() {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String city = jsonPathEvaluator.get("books[0].title");

        // Let us print the city variable to see what we got
        System.out.println("title received from Response " + city);

        // Validate the response
        // Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

    }


    @Test
    public void VerifyTitlesInJsonResponse() {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Books");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        List<String> titles = jsonPathEvaluator.getList("books.title");

        // Let us print the city variable to see what we got

        for (String title : titles) {
            System.out.println(title);
        }
        // Validate the response
        // Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

    }

    @Test
    public void getUserData() {
        //Using the preemptive directive of basic auth to send credentials to the server
        RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic("postman", "password");
        Response res = httpRequest.get("https://postman-echo.com/basic-auth");
        ResponseBody body = res.body();
        //Converting the response body to string
        String rbdy = body.asString();
        System.out.println("Data from the GET API- " + rbdy);
    }

    @Test
    public void UserRegistrationSuccessful() {
        RestAssured.baseURI = "https://demoqa.com/Account/v1";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", "test_rest");
        requestParams.put("password", "Testrest@123");
        request.body(requestParams.toJSONString());
        Response response = request.put("/User");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

    @Test

    public void post_request() {


        String jsonString = "{\"name\" : \"kanak\",\"password\" : \"kanak123\"}";

    /*
    	Construct a request specification
    */
        RequestSpecification request = RestAssured.given();

    /*
    	Setting content type to specify the format in which the request payload will be sent.
    */
        request.contentType(ContentType.JSON);

    /*
    	Adding URI
    */
        request.baseUri("https://reqres.in/api/users");

    /*
    	Adding body as string
    */
        request.body(jsonString);

    /*
    	Calling POST method on URI. After hitting, we get Response
    */
        Response response = request.post();

    /*
    	Printing Response as string
    */
        System.out.println(response.asString());

    /*
    	Get Validatable response to perform validation
    */
        ValidatableResponse validatableResponse = response.then();

    /*
    	Validate status code as 201
    */
        validatableResponse.statusCode(201);

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
    }

    public static Map<String, String> map = new HashMap<>();

    @BeforeTest
    public void putdata() {
        map.put("userId", "2");
        map.put("id", "19");
        map.put("title", "this is projectdebug.com");
        map.put("body", "i am testing REST api with REST-Assured and sending a PUT request.");
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts/";
    }

    @Test
    public void testPUT() {
        given()
                .contentType("application/json")
                .body(map)
                .when()
                .put("/100")
                .then()
                .statusCode(200)
                .and()
                .body("title", equalTo("this is projectdebug.com"));
    }


    @Test
    void TEST_put() {
        // Create object of JSONObject type
        JSONObject request = new JSONObject();
        //passing data to be modified in put() as arguments
        request.put("name", "Aarya");
        request.put("job", "Developer");
        System.out.println(request);
        System.out.println(request.toJSONString());
        // Directly checking the status code of the given URL
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then().
                statusCode(200);
    }

    @Test
    public void usermethodDetailsPosts() {
        //RestAssured.baseURI = "http://localhost:3000/posts";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", "5");
        requestParams.put("title", "a title5");
        requestParams.put("views", "105");

        request.body(requestParams.toJSONString());
        Response response = request.post("http://localhost:3000/posts");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.prettyPrint());

    }

    @Test
    public void userPutmethodDetailsPosts() {
        //RestAssured.baseURI = "http://localhost:3000/posts";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", "5");
        requestParams.put("title", "a title6");
        requestParams.put("views", "106");

        request.body(requestParams.toJSONString());
        Response response = request.put("http://localhost:3000/posts/5");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.prettyPrint());

    }

    @Test
    public void userDeleteMethodDetailsPosts() {
        //RestAssured.baseURI = "http://localhost:3000/posts";
        RequestSpecification request = RestAssured.given();
        Response response = request.delete("http://localhost:3000/posts/5");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.prettyPrint());

    }


}


