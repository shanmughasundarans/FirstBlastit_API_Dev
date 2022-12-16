package Development;

import java.io.IOException;

import org.testng.annotations.Test;

public class Login_Validation {
	@Test(priority = 1, enabled = true)
	public void UserValidation() throws IOException {
		Signin_Signup one = new Signin_Signup();
		one.Login_reg();
		one.Response_Message.equalsIgnoreCase("Login successfully");
		System.out.println("Loggedin successfully");
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = { "UserValidation" })
	public void OTP_Verfication() throws IOException {
		VerifyOTP two = new VerifyOTP();
		two.Initializing();
		two.OTP_Verification();
		System.out.println("OTP Verifed");

	}

}
