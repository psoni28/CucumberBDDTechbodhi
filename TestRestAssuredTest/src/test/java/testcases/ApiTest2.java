package testcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ApiTest2 {

    @Test
    public void postMethodTest() {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "4");
        jsonObject.put("title", "test title four");
        jsonObject.put("views", 400);

        httpRequest.body(jsonObject.toJSONString());

        Response response = httpRequest.post("http://localhost:3000/posts");
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.prettyPrint());

    }

    @Test
    public void putMethodTest() {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "4");
        jsonObject.put("title", "title 4");
        jsonObject.put("views", 499);

        httpRequest.body(jsonObject.toJSONString());

        Response response = httpRequest.put("http://localhost:3000/posts/4");
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.prettyPrint());

    }

    @Test
    public void deleteMethodTest() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.delete("http://localhost:3000/posts/4");
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.prettyPrint());

    }

    @Test(dataProvider = "getData")
    public void postMethodTestDataProvider(String id, String title, String views) {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("title", title);
        int view = Integer.parseInt(views);
        jsonObject.put("views", view);
        httpRequest.body(jsonObject.toJSONString());

        Response response = httpRequest.post("http://localhost:3000/posts");
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.prettyPrint());


    }

    @DataProvider(name = "getData")
    public Object[][] getData() {
        String[][] data = {
                {"5", "title 5", "500"},
                {"6", "title 6", "600"},
                {"7", "title 7", "700"},
        };
        return data;
    }
}
