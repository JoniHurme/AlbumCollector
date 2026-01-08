import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

public class MainView {

    @GetMapping("/main")
    @ResponseBody
    public String mainView(){
        String viewMain = "This is the main view";
        return viewMain;
    }
}
