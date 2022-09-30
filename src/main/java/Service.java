import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.util.Random;

@WebService(name = "Service", targetNamespace = "utez")
@SOAPBinding(style = SOAPBinding.Style.RPC)

public class Service {
    Random random = new Random();
    int numRandom = random.nextInt(3);
    @WebMethod(operationName = "NumRandom")
    public String NumRandom(@WebParam(name="num")int num){
        System.out.println(numRandom);
        if (num != numRandom)
            return "Nothing was provied";
        if (num == new Random().nextInt(5)){
            return "The number is the same";
        }else {
            return "No today, try again!";
        }
    }

    @WebMethod(operationName = "consonantes")
    public String consonantes(@WebParam(name = "cad")String cad){
        return "La cadena con solo consonantes es: "+cad.replaceAll("[a,e,i,o,u]","");
    }

    @WebMethod(operationName = "RFC")
    public String RFC(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "ap") String ap,
            @WebParam(name = "am") String am,
            @WebParam(name = "anionac") String anionac,
            @WebParam(name = "mesnac") String mesnac,
            @WebParam(name = "dianac") String dianac
    )
    {

        String rfcName = "" + ap.charAt(0) + ap.charAt(1) + am.charAt(0) + nombre.charAt(0);
        String letras = "abcdefghijklmnopqrstuvwxyz";
        String clavel = "";
        for (int i = 0; i < 2; i++) {
            int randomLetters = (int) (Math.random() * (letras.length()) - 1);
            clavel += letras.charAt(randomLetters);
        }
        String numeros = "123456789";
        String claven = "";
        for (int i = 0; i < 1; i++) {
            int randomNumber = (int) (Math.random() * (numeros.length()) - 1);
            claven += numeros.charAt(randomNumber);
        }
        String fecha=(anionac+mesnac+dianac);
        String rfc = (rfcName +fecha+ clavel +claven+ "").toUpperCase();
        return "Felicidades "+ nombre+" su RFC es: " + rfc;
    }


    public static void main(String[] args) {
        System.out.println("Running server...");
        Endpoint.publish("http://localhost:8082/Service", new Service());
        System.out.println("Waiting requests...");
    }
}



