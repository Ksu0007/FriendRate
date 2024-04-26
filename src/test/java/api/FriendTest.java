package api;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.mailslurp.clients.ApiException;
import helpers.TestValues;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class FriendTest {
    private final static String URL_PUBL = "https://whispering-falls-70384-f5d92e367b77.herokuapp.com/";
    private static String generatedEmail;
    private static String generatedToken;
    private static String name;
    private static String id;


    @BeforeTest
    public void setFilter() {RestAssured.filters(new AllureRestAssured());}

    @Test(priority = 1)
    public void checkSuccessRegistration() throws ApiException {
        Faker faker = new Faker();
        String emailAddress = faker.internet().emailAddress();
        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecOK201());
        Register user = Register.builder()
                .email(emailAddress)
                .password(TestValues.VALID_PASSWORD)
                .build();

        String requestBody = new Gson().toJson(user);

        SuccessRegister successRegister = given()
                .body(requestBody)
                .when()
                .post("api/user/register")
                .then().log().all()
                .extract().as(SuccessRegister.class);

        Assert.assertNotNull(successRegister);
        Assert.assertNotNull(successRegister.getToken());
        Assert.assertNotNull(successRegister.getUser().getEmail());

        generatedEmail = successRegister.getUser().getEmail();
        Assert.assertEquals(generatedEmail, emailAddress);

        generatedToken = successRegister.getToken();
    }

    @Test(priority = 2)
    public void checkValidLogin() {

        System.out.println("Generated email: " + generatedEmail);
        System.out.println("Generated token: " + generatedToken);
        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecOK200());
        Login login = Login.builder()
                .email(generatedEmail)
                .password(TestValues.VALID_PASSWORD)
                .build();

        System.out.println("Login object created: " + login);

        String requestBody = new Gson().toJson(login);
        System.out.println("Request body: " + requestBody);

        SuccessLogin successLogin = given()
                .header("Authorization", "Bearer " + generatedToken)
                .body(requestBody)
                .when()
                .contentType("application/json")
                .post("api/user/login")
                .then().log().all()
                .extract().as(SuccessLogin.class);

        Assert.assertNotNull(successLogin);

    }

    @Test(priority = 3)
    public void checkAddingInfo() {
        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecOK200());
        Faker faker = new Faker();
        name = faker.name().firstName();
        String gender = "Male";
        String birthday = "02.09.1999";

        AddInfo newInfo = AddInfo.builder()
                .username(name)
                .email(generatedEmail)
                .gender(gender)
                .birthday(birthday)
                .build();

        String requestBody = new Gson().toJson(newInfo);
        SuccessAddedInfo successAddedInfo = given()
                .header("Authorization", "Bearer " + generatedToken)
                .when()
                .body(requestBody)
                .patch("api/user/update")
                .then().log().all()
                .extract().as(SuccessAddedInfo.class);
        //Assert.assertEquals(name, successAddedInfo.getName());
        Assert.assertEquals(generatedEmail, successAddedInfo.getEmail());
        Assert.assertEquals(gender, successAddedInfo.getGender());
    }

    @Test(priority = 4)
    public void checkGetIngo() {
        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecOK200());


        SuccessGetInfo getInfo = given()
                .header("Authorization", "Bearer " + generatedToken)
                .when()
                .get("api/user/current")
                .then().log().all()
                //.body(matchesJsonSchemaInClasspath("registrationResponceShema.json"))
                .extract().body().as(SuccessGetInfo.class);

        id = getInfo.get_id();

        Assert.assertEquals(generatedEmail, getInfo.getEmail());
    }

    @Test(priority = 5)
    public void testDeleteUser() {
        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecUnique(200));
        Delete delete = Delete.builder()
                ._id(id)
                .build();
        String requestBody = new Gson().toJson(delete);
        SuccessDelete successDelete = given()
                .header("Authorization", "Bearer " + generatedToken)
                .when()
                .body(requestBody)
                .delete("api/user/delete/")
                .then().log().all()
                .extract().as(SuccessDelete.class);
        Assert.assertEquals(successDelete.getMessage(), "User with ID " + id + " has been deleted successfully");
    }


    @Test(enabled = false)
    public void checkRegistrationWrongEmail() {

        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecBadRequest400());
        Register user = Register.builder()
                .email("email@")
                .password(TestValues.VALID_PASSWORD)
                .build();
        String requestBody = new Gson().toJson(user);

        FailedRegister failedRegister = given()
                .body(requestBody)
                .when()
                .post("api/user/register")
                .then().log().all()
                .extract().as(FailedRegister.class);
    }

    @Test(enabled = false)
    public void checkRegistrationShortPass() {
        Faker faker = new Faker();
        String emailAddress = faker.internet().emailAddress();
        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecBadRequest400());
        Register user = Register.builder()
                .email(emailAddress)
                .password("TRu3$")
                .build();
        String requestBody = new Gson().toJson(user);

        FailedRegister failedRegister = given()
                .body(requestBody)
                .when()
                .post("api/user/register")
                .then().log().all()
                .extract().as(FailedRegister.class);
    }

    @Test(enabled = false)
    public void checkRegistrationUsedEmail() {
        Specifications.installSpecification(Specifications.requestSpecification(URL_PUBL),
                Specifications.responseSpecInUse409());
        Register user = Register.builder()
                .email(TestValues.VALID_EMAIL)
                .password(TestValues.VALID_PASSWORD)
                .build();
        String requestBody = new Gson().toJson(user);

        FailedRegister failedRegister = given()
                .body(requestBody)
                .when()
                .post("api/user/register")
                .then().log().all()
                .extract().as(FailedRegister.class);
    }







}
