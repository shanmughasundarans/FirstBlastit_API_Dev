package Development;
import java.io.IOException;

import org.testng.annotations.Test;

public class Login_Validation extends Signin_Signup {
	
	@Test
	public void thy() throws IOException {
		Signin_Signup o = new Signin_Signup();
		o.Login_reg();
		System.out.println("value: "+o.validate());

}
}
