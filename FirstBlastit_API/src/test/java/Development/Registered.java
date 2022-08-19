package Development;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Registered {
	
	
	public static XSSFWorkbook wb;
	 public static XSSFSheet st;
	 public static RequestSpecification request;
	@BeforeSuite
	public void   Initializing() {
		RestAssured.baseURI=("https://dev.firstblastit.com");
		Header acceptHeader = new Header("Accept","application/json");
		Header contentTypeHeader = new Header ("Content-Type","application/json");


		ArrayList<Header> header = new ArrayList<>();
		header.add(contentTypeHeader);
		header.add(acceptHeader);

		Headers allheaders = new Headers(header);


		//RequestSpecification request = RestAssured.given().headers(allheaders);

		request = RestAssured.given().headers(allheaders);



	}
	
	
	@Test(enabled=true)
	@Parameters("file_location")
	public void OTP_Verification(String output ) throws IOException {


		FileInputStream fl = new FileInputStream(output);
		System.out.println("Sheet path " + fl);
		wb = new XSSFWorkbook(fl);
		st = wb.getSheet("NewUser");
		System.out.println("sheet = "+ st);

		Row sheet = st.getRow(1);
		st = wb.getSheet("Login");
		//Getting Authorization id from parent 
		Row Get_parent_sheetdetails_auth = st.getRow(1);
		String get_parent_sheet_auth = Get_parent_sheetdetails_auth.getCell(4).getStringCellValue();
		//Getting otp id from parent 
		Row Get_parent_sheetdetails_otp = st.getRow(1);
		String get_parent_sheet_otp = Get_parent_sheetdetails_otp.getCell(6).getStringCellValue();
		System.out.println("Got:"+ get_parent_sheet_otp);
		//Getting paylod from new sheet
		st = wb.getSheet("NewUser");
		Row get_reg_payload = st.getRow(1);

		String get_reg_payload_cell = get_reg_payload.getCell(2).getStringCellValue();

		System.out.println("cell value :" +get_reg_payload_cell);
		//appending the otp into payload

		int indexToInsert= 13;
		StringBuilder s1= new StringBuilder(get_reg_payload_cell);

		StringBuilder s2= new StringBuilder(get_parent_sheet_otp);

		s1.insert(indexToInsert, s2.toString());
		String raw_payload= s1.toString();


		request.header("Authorization","Bearer " +get_parent_sheet_auth);
		io.restassured.response.Response  Method  = request.log().all().body(raw_payload).when().post("/api/verify-otp");

		System.out.println(Method.getStatusCode());
		ResponseBody body = Method.body();
		
		int Response_status=Method.getStatusCode();
		String pritty = body.asPrettyString();
		System.out.println("Receiveed_Response ="+ pritty);
	}
	
	

}
