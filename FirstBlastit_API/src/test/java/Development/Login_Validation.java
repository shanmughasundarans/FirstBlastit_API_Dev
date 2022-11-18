package Development;
import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login_Validation extends Signin_Signup {
	
	@Test
	@Parameters({"Ft"})
	public void Existing (String Ft) throws IOException {
		Signin_Signup o = new Signin_Signup();
//		if( ) {
//			
			
			
		//}
		//o.Login_reg(Ft);
		
		
		
		System.out.println("value: "+o.validate());

}
}
