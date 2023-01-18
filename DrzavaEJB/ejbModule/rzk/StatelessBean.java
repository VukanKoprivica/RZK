package rzk;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

import rsd.CountryInfoService;
import rsd.CountryInfoServiceSoapType;

/**
 * Session Bean implementation class StatelessBean
 */
@Stateless
@WebService
public class StatelessBean implements StatelessBeanRemote {
	
	@WebServiceRef(CountryInfoService.class)
	CountryInfoServiceSoapType cisst;

    /**
     * Default constructor. 
     */
    public StatelessBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@WebMethod
	public String resolve(String s) {
		// TODO Auto-generated method stub
		((BindingProvider)cisst).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL");
		
		String response = "Your country is "+cisst.countryName(s)
		+" the capital of " + cisst.countryName(s) +" is "+cisst.capitalCity(s)
		+", the language is " +cisst.languageName(s) + " and the currency is "+ cisst.currencyName(s);

		
		System.out.println(response);
		return response;
	}

}
