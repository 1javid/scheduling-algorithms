import algorithms.*;

public class App {

    public static void main(String[] args) throws Exception {
        FCFS.run("src/in/process.txt", "src/out/TeamNaN_FCFS.txt");
        SRTF.run("src/in/process.txt", "src/out/TeamNaN_SRTF.txt");
        RR.run("src/in/process.txt", "src/out/TeamNaN_RR.txt");
    }
}