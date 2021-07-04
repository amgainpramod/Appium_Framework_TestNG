package utilities;

import java.io.IOException;

public class Emulator {
    //chmod +x ./start-emulator-mac.sh
    public static void startEmulator() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        if (os.equalsIgnoreCase("mac")) {
            Runtime.getRuntime().exec(System.getProperty("user.dir") + "//src//main//resources//RuntimeExecutables" +
                    "//start-emulator-mac.sh");
        }
        if (os.equalsIgnoreCase("win")) {
            Runtime.getRuntime().exec(System.getProperty("user.dir") + "//src//main//resources//RuntimeExecutables" +
                    "//start-emulator-win.bat");
        }
        Thread.sleep(10000);
    }
}
