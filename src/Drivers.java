import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Drivers {

    static Scanner in = new Scanner(System.in);
    static Dinas din = new Dinas();
    static int set;
    static boolean UserInput = true;
    static String Lanjut;

    public static void main(String[] args) throws Exception {
        Menu();
    }



    public static void Menu() throws Exception {
        System.out.println();
        System.out.print("============= Selamat Datang Di Dinas Pertanahan Ulin ===========");
        System.out.printf("\n1.Input\n2.Read\n3.Exit\n");
        System.out.print("Pilih Menu Anda: ");
        set = in.nextInt();
        if (set == 1) {
            Input();
        } else if (set == 2) {
            Read();
        } else if (set == 3) {
            System.exit(292);
        } else {
            System.out.println("Harus Input Sesuai Menu");
            Menu();
        }
    }

    public static void Input() throws Exception{
        try {
            FileWriter fileWriter = new FileWriter("Input.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (UserInput) {
                String Adress;
                int PanjangTanah, LuasTanah;
                Scanner xd = new Scanner(System.in);
                System.out.print("Masukkan Alamat Anda: ");
                Adress = xd.nextLine();

                System.out.print("Masukkan Panjang Tanah: ");
                PanjangTanah = xd.nextInt();

                System.out.print("Masukkan Luas Tanah: ");
                LuasTanah = xd.nextInt();

                din.setPanjangTanah(PanjangTanah);
                din.setAlamat(Adress);
                din.setLuasTanah(LuasTanah);

                String Input = String.format("%s,%s,%s\n", Adress, PanjangTanah, LuasTanah);
                bufferedWriter.write(Input);

                System.out.print("Apakah Ingin Input Lagi? ");
                Lanjut = in.next();
                UserInput = Lanjut.equalsIgnoreCase("y");
            }
            bufferedWriter.close();

        } catch (InputMismatchException as){
            System.err.println(as);
            System.out.println();
        } finally {
            Menu();
        }


    }

    public static void Read() throws Exception {
        String input = null;
        try {
            FileReader fileReader = new FileReader("Input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            input = bufferedReader.readLine();
            int quarter = 1;

            System.out.printf("%-2s %-20s %-15s %-15s\n", "No", "Alamat", "Panjang Tanah", "Luas Tanah");
            //Show Data
            while (input != null) {
                StringTokenizer xy = new StringTokenizer(input, ",");

                String Adress;
                String PanjangTanah, LuasTanah;

                Adress = xy.nextToken();
                PanjangTanah = xy.nextToken();
                LuasTanah = xy.nextToken();

                //Output
                System.out.printf("%2d %-20s %-15s %-15s\n", quarter++, Adress, PanjangTanah, LuasTanah);
                input = bufferedReader.readLine();
            }

            bufferedReader.close(); fileReader.close();
        } catch (FileNotFoundException a){
            System.err.println(a);
            System.out.println();
            Menu();
        } finally {
            Menu();
        }
    }
}
