import models.DeterminanInvers;
import models.SPL;
import operations.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void println(String str){
        System.out.println(str);
    }

    public static void print(String str){
        System.out.print(str);
    }

    public static void main(String[] args) {
        println("\n***** ==================== APLIKASI MULAI ==================== *****");
        println("===== Selamat Datang pada Aplikasi Perhitungan Matriks dan SPL =====");
        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while(program) {
            /* ============================== MENU UTAMA ============================== */
            println("\n =============== MENU UTAMA ===============");
            println("1. Sistem Persamaaan Linier");
            println("2. Determinan");
            println("3. Matriks balikan");
            println("4. Interpolasi Polinom");
            println("5. Interpolasi Bicubic Spline");
            println("6. Regresi linier berganda");
            println("7. Keluar");
            print(" >>> Pilih menu : ");
            boolean isInputMenuUtamaValid = false;
            int opsiMenuUtama = -1;
            try {
                opsiMenuUtama = Integer.parseInt(scanner.nextLine());
                isInputMenuUtamaValid = true;
            } catch (Exception e){
                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
            }
            if(isInputMenuUtamaValid){
                String tipeMenuUtama = "";
                switch (opsiMenuUtama){
                    case 1:
                        tipeMenuUtama = "SPL";
                        break;
                    case 2:
                        tipeMenuUtama = "Determinan";
                        break;
                    case 3:
                        tipeMenuUtama = "Balikan";
                        break;
                    case 4:
                        tipeMenuUtama = "Interpolasi";
                        break;
                    case 5:
                        tipeMenuUtama = "Bicubic";
                        break;
                    case 6:
                        tipeMenuUtama = "Regresi";
                        break;
                    case 7:
                        program = false;
                        break;
                    default:
                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
                }

                if(tipeMenuUtama.equals("SPL")) {
                    /* =========== MENU SPL =========== */
                    boolean SPLMenu = true;
                    while (SPLMenu) {
                        println("\n ========== Pilih Metode Penyelesaian SPL ==========");
                        println("1. Metode Eliminasi Gauss");
                        println("2. Metode Eliminasi Gauss-Jordan");
                        println("3. Metode Matriks Balikan ");
                        println("4. Kaidah Cramer");
                        println("5. Kembali");
                        print(" >>> Pilih Metode : ");
                        boolean isInputMenuSPLValid = false;
                        int opsiMenuSPL = -1;
                        try {
                            opsiMenuSPL = Integer.parseInt(scanner.nextLine());
                            isInputMenuSPLValid = true;
                        } catch (Exception e) {
                            println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                        }

                        if (isInputMenuSPLValid) {
                            String tipeMenuSPL = "";
                            switch (opsiMenuSPL) {
                                case 1:
                                    tipeMenuSPL = "Gauss";
                                    break;
                                case 2:
                                    tipeMenuSPL = "Gauss-Jordan";
                                    break;
                                case 3:
                                    tipeMenuSPL = "Balikan";
                                    break;
                                case 4:
                                    tipeMenuSPL = "Cramer";
                                    break;
                                case 5:
                                    SPLMenu = false;
                                    break;
                                default:
                                    println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                            }
                            SPL spl = new SPL(1000, 1000);
                            if (!tipeMenuSPL.isEmpty()) {
                                boolean inputSPL = true;
                                while (inputSPL) {
                                    /* =========== INPUT MATRIKS SPL =========== */
                                    println("\n ========== Pilih Metode Masukkan ==========");
                                    println("1. Masukkan Ketik");
                                    println("2. Masukkan dalam bentuk File");
                                    println("3. Kembali");
                                    print(" >>> Pilih Metode Masukkan : ");
                                    boolean isInputSPLValid = false;
                                    try {
                                        opsiMenuSPL = Integer.parseInt(scanner.nextLine());
                                        isInputSPLValid = true;
                                    } catch (Exception e) {
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                    }
                                    if (isInputSPLValid) {
                                        boolean terisiSPL = false;
                                        switch (opsiMenuSPL) {
                                            case 1:
                                                /* ============ Input Ketik =========== */
                                                boolean inputKetik = true;
                                                while (inputKetik) {
                                                    println("\n ========== Metode Ketik ==========");
                                                    if (tipeMenuSPL.equals("Balikan") || tipeMenuSPL.equals("Cramer")) {
                                                        print(" >>> Masukkan Ukuran Matriks (n x n) : ");
                                                    } else {
                                                        print(" >>> Masukkan Ukuran Matriks baris x kolom (m x n) : ");
                                                    }
                                                    int row = -1;
                                                    int col = -1;
                                                    try {
                                                        row = scanner.nextInt();
                                                        if (tipeMenuSPL.equals("Balikan") || tipeMenuSPL.equals("Cramer")) {
                                                            col = row;
                                                        } else {
                                                            col = scanner.nextInt();
                                                        }
                                                    } catch (Exception e) {
                                                        println("Ukuran Matriks harus bilangan bulat positif");
                                                    }
                                                    scanner.nextLine();
                                                    if (row != -1 && col != -1) {
                                                        if (row <= 0 || col <= 1) {
                                                            println("Ukuran Matriks harus bilangan bulat positif dan kolom harus lebih dari 1");
                                                        } else {
                                                            spl = new SPL(row, col);
                                                            println(">>> Masukkan Isi Matriks " + row + " x " + col + " : ");
                                                            boolean isInputMatrixValid = true;
                                                            int i = 0;
                                                            while (i < row && isInputMatrixValid) {
                                                                int j = 0;
                                                                while (j < col && isInputMatrixValid) {
                                                                    try {
                                                                        double elmt = scanner.nextDouble();
                                                                        spl.spl.setMElmt(elmt, i, j);
                                                                    } catch (Exception e) {
                                                                        println(e.toString());
                                                                        println("Isi Matrix salah. Pastikan isi matriks adalah bilangan real");
                                                                        isInputMatrixValid = false;
                                                                    }
                                                                    j++;
                                                                }
                                                                i++;
                                                            }
                                                            scanner.nextLine();
                                                            if (isInputMatrixValid) {
                                                                inputKetik = false;
                                                                terisiSPL = true;
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case 2:
                                                /* ============ Input File =========== */
                                                /* ASUMSI : isi FILE BENAR */
                                                boolean inputFile = true;
                                                while (inputFile) {
                                                    println("Ketik 0 untuk kembali");
                                                    print(" >>> Masukkan alamat file: ");
                                                    String path = scanner.nextLine();
                                                    if (path.equals("0")) {
                                                        inputFile = false;
                                                    } else {
                                                        File file;
                                                        Scanner readFile = null;
                                                        boolean filefound = false;
                                                        try {
                                                            file = new File(path);
                                                            readFile = new Scanner(file);
                                                            filefound = true;
                                                        } catch (FileNotFoundException e) {
                                                            println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                        }
                                                        if (filefound) {
                                                            String line;
                                                            int row = 0;
                                                            int column = 0;
                                                            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null) {
                                                                String[] saved = line.split(" ");
                                                                if (row == 0) {
                                                                    column = saved.length;
                                                                }
                                                                int i;
                                                                for (i = 0; i < column; i++) {
                                                                    double temp = Double.parseDouble(saved[i]);
                                                                    spl.spl.setMElmt(temp, row, i);
                                                                }
                                                                row++;
                                                            }
                                                            spl.spl.setMatrixRow(row);
                                                            spl.spl.setMatrixCol(column);
                                                            inputFile = false;
                                                            terisiSPL = true;
                                                        }
                                                    }
                                                }
                                                break;
                                            case 3:
                                                inputSPL = false;
                                                break;
                                            default:
                                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                        }
                                        if (terisiSPL) {
                                            if (tipeMenuSPL.equals("Gauss")) {
                                                spl.spl.gaussAndSolutions();
                                            } else if (tipeMenuSPL.equals("Gauss-Jordan")) {
                                                spl.spl.obeGaussJordan();
                                            } else if (tipeMenuSPL.equals("Balikan")) {
                                                // OPERASI MATRIKS BALIKAN
                                            } else {
                                                // OPERASI KRAMER
                                            }
                                            println(" ================== HASIL ================== ");
                                            println(spl.spl.getStep());
                                            inputSPL = false;
                                            SPLMenu = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if(tipeMenuUtama.equals("Determinan")){
                    /* =========== MENU Determinan =========== */
                    boolean detMenu = true;
                    while (detMenu) {
                        println("\n   ========== Pilih Metode Determinan ========== ");
                        println("1. Metode Ekspansi Kofaktor");
                        println("2. Metode Reduksi Baris dengan OBE");
                        println("3. Kembali");
                        print(" >>> Pilih Metode Determinan : ");

                        boolean isInputMenuDetValid = false;
                        int opsiMenuDet = -1;
                        try {
                            opsiMenuDet = Integer.parseInt(scanner.nextLine());
                            isInputMenuDetValid = true;
                        } catch (Exception e) {
                            println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                        }

                        if (isInputMenuDetValid) {
                            String tipeMenuDet = "";
                            switch (opsiMenuDet) {
                                case 1:
                                    tipeMenuDet = "Kofaktor";
                                    break;
                                case 2:
                                    tipeMenuDet = "OBE";
                                    break;
                                case 3:
                                    detMenu = false;
                                    break;
                                default:
                                    println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                            }
                            DeterminanInvers det = new DeterminanInvers(1000,1000);
                            if (!tipeMenuDet.isEmpty()) {
                                boolean inputDet = true;
                                while (inputDet) {
                                    /* =========== INPUT MATRIKS Determinan =========== */
                                    println("\n ========== Pilih Metode Masukkan ==========");
                                    println("1. Masukkan Ketik");
                                    println("2. Masukkan dalam bentuk File");
                                    println("3. Kembali");
                                    print(" >>> Pilih Metode Masukkan : ");
                                    boolean isInputSPLValid = false;
                                    try {
                                        opsiMenuDet = Integer.parseInt(scanner.nextLine());
                                        isInputSPLValid = true;
                                    } catch (Exception e) {
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                    }
                                    if (isInputSPLValid) {
                                        boolean terisiMatriks = false;
                                        switch (opsiMenuDet) {
                                            case 1:
                                                /* ============ Input Ketik =========== */
                                                boolean inputKetik = true;
                                                while (inputKetik) {
                                                    println("\n ========== Metode Ketik ==========");
                                                    print(" >>> Masukkan Ukuran Matriks (n x n) : ");
                                                    int row = -1;
                                                    int col = -1;
                                                    try {
                                                        row = scanner.nextInt();
                                                        col = row;
                                                    } catch (Exception e) {
                                                        println("Ukuran Matriks harus bilangan bulat positif");
                                                    }
                                                    scanner.nextLine();
                                                    if (row != -1) {
                                                        if (row <= 1) {
                                                            println("Ukuran Matriks harus bilangan bulat positif");
                                                        } else {
                                                            det = new DeterminanInvers(row, col);
                                                            println(">>> Masukkan Isi Matriks " + row + " x " + col + " : ");
                                                            boolean isInputMatrixValid = true;
                                                            int i = 0;
                                                            while (i < row && isInputMatrixValid) {
                                                                int j = 0;
                                                                while (j < col && isInputMatrixValid) {
                                                                    try {
                                                                        double elmt = scanner.nextDouble();
                                                                        det.contents.setMElmt(elmt,i,j);
                                                                    } catch (Exception e) {
                                                                        println(e.toString());
                                                                        println("Isi Matrix salah. Pastikan isi matriks adalah bilangan real");
                                                                        isInputMatrixValid = false;
                                                                    }
                                                                    j++;
                                                                }
                                                                i++;
                                                            }
                                                            scanner.nextLine();
                                                            if (isInputMatrixValid) {
                                                                inputKetik = false;
                                                                terisiMatriks = true;
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case 2:
                                                /* ============ Input File =========== */
                                                /* ASUMSI : isi FILE BENAR */
                                                boolean inputFile = true;
                                                while (inputFile) {
                                                    println("Ketik 0 untuk kembali");
                                                    print(" >>> Masukkan alamat file: ");
                                                    String path = scanner.nextLine();
                                                    if (path.equals("0")) {
                                                        inputFile = false;
                                                    } else {
                                                        File file;
                                                        Scanner readFile = null;
                                                        boolean filefound = false;
                                                        try {
                                                            file = new File(path);
                                                            readFile = new Scanner(file);
                                                            filefound = true;
                                                        } catch (FileNotFoundException e) {
                                                            println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                        }
                                                        if (filefound) {
                                                            String line;
                                                            int row = 0;
                                                            int column = 0;
                                                            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null) {
                                                                String[] saved = line.split(" ");
                                                                if (row == 0) {
                                                                    column = saved.length;
                                                                }
                                                                int i;
                                                                for (i = 0; i < column; i++) {
                                                                    double temp = Double.parseDouble(saved[i]);
                                                                    det.contents.setMElmt(temp,row,i);
                                                                }
                                                                row++;
                                                            }
                                                            det.contents.setMatrixRow(row);
                                                            det.contents.setMatrixCol(column);
                                                            inputFile = false;
                                                            terisiMatriks = true;
                                                        }
                                                    }
                                                }
                                                break;
                                            case 3:
                                                inputDet = false;
                                                break;
                                            default:
                                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                        }
                                        if (terisiMatriks) {
                                            if (tipeMenuDet.equals("Kofaktor")) {
                                                println(" ================== HASIL ================== ");
                                                println(" Determinan = " + det.determinanKofaktor());
                                            } else{
                                                det.CalculateOBE();
                                                println(" ================== HASIL ================== ");
                                                println(det.contents.getStep());
                                            }
                                            inputDet = false;
                                            detMenu = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if(tipeMenuUtama.equals("Balikan")){
                    /* =========== MENU Matiks Balikan =========== */
                    boolean BalikanMenu = true;
                    while (BalikanMenu) {
                        println("\n   ========== Pilih Metode Determinan ========== ");
                        println("1. Metode Matriks Balikan");
                        println("2. Metode Adjoin");
                        println("3. Kembali");
                        print(" >>> Pilih Metode Determinan : ");

                        boolean isInputMenuBalikanValid = false;
                        int opsiMenuBalikan = -1;
                        try {
                            opsiMenuBalikan = Integer.parseInt(scanner.nextLine());
                            isInputMenuBalikanValid = true;
                        } catch (Exception e) {
                            println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                        }

                        if (isInputMenuBalikanValid) {
                            String tipeMenuBalikan = "";
                            switch (opsiMenuBalikan) {
                                case 1:
                                    tipeMenuBalikan = "Balikan";
                                    break;
                                case 2:
                                    tipeMenuBalikan = "Adjoin";
                                    break;
                                case 3:
                                    BalikanMenu = false;
                                    break;
                                default:
                                    println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                            }
                            DeterminanInvers invers = new DeterminanInvers(1000,1000);
                            if (!tipeMenuBalikan.isEmpty()) {
                                boolean inputDet = true;
                                while (inputDet) {
                                    /* =========== INPUT MATRIKS Balikan =========== */
                                    println("\n ========== Pilih Metode Masukkan ==========");
                                    println("1. Masukkan Ketik");
                                    println("2. Masukkan dalam bentuk File");
                                    println("3. Kembali");
                                    print(" >>> Pilih Metode Masukkan : ");
                                    boolean isInputSPLValid = false;
                                    try {
                                        opsiMenuBalikan = Integer.parseInt(scanner.nextLine());
                                        isInputSPLValid = true;
                                    } catch (Exception e) {
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                    }
                                    if (isInputSPLValid) {
                                        boolean terisiMatriks = false;
                                        switch (opsiMenuBalikan) {
                                            case 1:
                                                /* ============ Input Ketik =========== */
                                                boolean inputKetik = true;
                                                while (inputKetik) {
                                                    println("\n ========== Metode Ketik ==========");
                                                    print(" >>> Masukkan Ukuran Matriks (n x n) : ");
                                                    int row = -1;
                                                    int col = -1;
                                                    try {
                                                        row = scanner.nextInt();
                                                        col = row;
                                                    } catch (Exception e) {
                                                        println("Ukuran Matriks harus bilangan bulat positif");
                                                    }
                                                    scanner.nextLine();
                                                    if (row != -1) {
                                                        if (row <= 1) {
                                                            println("Ukuran Matriks harus bilangan bulat positif");
                                                        } else {
                                                            invers = new DeterminanInvers(row, col);
                                                            println(">>> Masukkan Isi Matriks " + row + " x " + col + " : ");
                                                            boolean isInputMatrixValid = true;
                                                            int i = 0;
                                                            while (i < row && isInputMatrixValid) {
                                                                int j = 0;
                                                                while (j < col && isInputMatrixValid) {
                                                                    try {
                                                                        double elmt = scanner.nextDouble();
                                                                        invers.contents.setMElmt(elmt,i,j);
                                                                    } catch (Exception e) {
                                                                        println(e.toString());
                                                                        println("Isi Matrix salah. Pastikan isi matriks adalah bilangan real");
                                                                        isInputMatrixValid = false;
                                                                    }
                                                                    j++;
                                                                }
                                                                i++;
                                                            }
                                                            scanner.nextLine();
                                                            if (isInputMatrixValid) {
                                                                inputKetik = false;
                                                                terisiMatriks = true;
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case 2:
                                                /* ============ Input File =========== */
                                                /* ASUMSI : isi FILE BENAR */
                                                boolean inputFile = true;
                                                while (inputFile) {
                                                    println("Ketik 0 untuk kembali");
                                                    print(" >>> Masukkan alamat file: ");
                                                    String path = scanner.nextLine();
                                                    if (path.equals("0")) {
                                                        inputFile = false;
                                                    } else {
                                                        File file;
                                                        Scanner readFile = null;
                                                        boolean filefound = false;
                                                        try {
                                                            file = new File(path);
                                                            readFile = new Scanner(file);
                                                            filefound = true;
                                                        } catch (FileNotFoundException e) {
                                                            println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                        }
                                                        if (filefound) {
                                                            String line;
                                                            int row = 0;
                                                            int column = 0;
                                                            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null) {
                                                                String[] saved = line.split(" ");
                                                                if (row == 0) {
                                                                    column = saved.length;
                                                                }
                                                                int i;
                                                                for (i = 0; i < column; i++) {
                                                                    double temp = Double.parseDouble(saved[i]);
                                                                    invers.contents.setMElmt(temp,row,i);
                                                                }
                                                                row++;
                                                            }
                                                            invers.contents.setMatrixRow(row);
                                                            invers.contents.setMatrixCol(column);
                                                            inputFile = false;
                                                            terisiMatriks = true;
                                                        }
                                                    }
                                                }
                                                break;
                                            case 3:
                                                inputDet = false;
                                                break;
                                            default:
                                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                        }
                                        if (terisiMatriks) {
                                            println(" ================== HASIL ================== ");
                                            if(invers.determinanKofaktor() == 0){
                                                println("  Determinan = 0");
                                                println("  Matriks Tidak Memiliki Matriks\n");
                                            }
                                            else if(tipeMenuBalikan.equals("Balikan")){
                                                Matrix result = invers.inversMatrix();
                                                println("  Hasil Inverse :");
                                                result.displayMatrix();
                                            }
                                            else{
                                                Matrix result = invers.contents.getCopyAugmented().inversMatrix();
                                                println("  Hasil Inverse :");
                                                result.displayMatrix();
                                            }
                                            inputDet = false;
                                            BalikanMenu = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }






        scanner.close();
    }
}
