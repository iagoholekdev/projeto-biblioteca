import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class CustomMain implements QuarkusApplication {

   private String montaString() {
       return " ▗▄▖ ▗▄▄▖▗▄▄▄▖    ▗▄▄▖ ▗▄▄▄▖▗▄▄▖ ▗▖   ▗▄▄▄▖ ▗▄▖▗▄▄▄▖▗▄▄▄▖ ▗▄▄▖ ▗▄▖       \n" +
               "▐▌ ▐▌▐▌ ▐▌ █      ▐▌ ▐▌  █  ▐▌ ▐▌▐▌     █  ▐▌ ▐▌ █  ▐▌   ▐▌   ▐▌ ▐▌      \n" +
               "▐▛▀▜▌▐▛▀▘  █      ▐▛▀▚▖  █  ▐▛▀▚▖▐▌     █  ▐▌ ▐▌ █  ▐▛▀▀▘▐▌   ▐▛▀▜▌      \n" +
               "▐▌ ▐▌▐▌  ▗▄█▄▖    ▐▙▄▞▘▗▄█▄▖▐▙▄▞▘▐▙▄▄▖▗▄█▄▖▝▚▄▞▘ █  ▐▙▄▄▖▝▚▄▄▖▐▌ ▐▌      \n" +
               "                                                                         \n" +
               "                                                                         \n" +
               "                                                                         \n" +
               " ▗▄▄▖ ▗▄▖ ▗▄▄▖▗▖  ▗▖▗▄▄▖ ▗▄▄▄▖ ▗▄▄▖▗▖ ▗▖▗▄▄▄▖    ▗▄▄▄▖ ▗▄▖  ▗▄▄▖ ▗▄▖     \n" +
               "▐▌   ▐▌ ▐▌▐▌ ▐▌▝▚▞▘ ▐▌ ▐▌  █  ▐▌   ▐▌ ▐▌  █        █  ▐▌ ▐▌▐▌   ▐▌ ▐▌    \n" +
               "▐▌   ▐▌ ▐▌▐▛▀▘  ▐▌  ▐▛▀▚▖  █  ▐▌▝▜▌▐▛▀▜▌  █        █  ▐▛▀▜▌▐▌▝▜▌▐▌ ▐▌    \n" +
               "▝▚▄▄▖▝▚▄▞▘▐▌    ▐▌  ▐▌ ▐▌▗▄█▄▖▝▚▄▞▘▐▌ ▐▌  █      ▗▄█▄▖▐▌ ▐▌▝▚▄▞▘▝▚▄▞▘    \n" +
               "                                                                         \n" +
               "                                                                         \n" +
               "                                                                         \n" +
               "▗▖  ▗▖    ▗▄▄▄▖▗▄▄▄ ▗▖ ▗▖ ▗▄▖ ▗▄▄▖ ▗▄▄▄  ▗▄▖                             \n" +
               " ▝▚▞▘     ▐▌   ▐▌  █▐▌ ▐▌▐▌ ▐▌▐▌ ▐▌▐▌  █▐▌ ▐▌                            \n" +
               "  ▐▌      ▐▛▀▀▘▐▌  █▐▌ ▐▌▐▛▀▜▌▐▛▀▚▖▐▌  █▐▌ ▐▌                            \n" +
               "  ▐▌      ▐▙▄▄▖▐▙▄▄▀▝▚▄▞▘▐▌ ▐▌▐▌ ▐▌▐▙▄▄▀▝▚▄▞▘  ";
   }

    @Override
    public int run(String... args) throws Exception {
        System.out.println(this.montaString());
        Quarkus.waitForExit();
        return 0;
    }

}
