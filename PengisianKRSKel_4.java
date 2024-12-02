import java.util.Scanner;

public class PengisianKRSKel_4 {
    static String[][] dataKRS = new String[100][5]; 
    static int jumlahData = 0; // Jumlah data KRS yang tersimpan
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int menu = input.nextInt();
            input.nextLine(); 

            switch (menu) {
                case 1:
                    tambahDataKRS();
                    break;
                case 2:
                    tampilkanDaftarKRS();
                    break;
                case 3:
                    analisisDataKRS();
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    running = false;
                    break;
                default:
                    System.out.println("Menu tidak valid! Silakan pilih lagi.");
            }
        }
    }

    public static void tambahDataKRS() {
        System.out.println("\n--- Tambah Data KRS ---");
        System.out.print("Nama Mahasiswa: ");
        String nama = input.nextLine();
        System.out.print("NIM: ");
        String nim = input.nextLine();

        int totalSKS = 0;

        while (true) {
            System.out.print("Kode Mata Kuliah: ");
            String kodeMK = input.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String namaMK = input.nextLine();
            System.out.print("Jumlah SKS (1-3): ");
            int sks = input.nextInt();
            input.nextLine(); // Membersihkan buffer

            // Validasi jumlah SKS
            if (sks < 1 || sks > 3) {
                System.out.println("Jumlah SKS tidak valid! SKS harus antara 1 dan 3.");
                continue;
            }

            // Validasi total SKS
            if (totalSKS + sks > 24) {
                System.out.println("Total SKS melebihi 24! Data tidak bisa ditambahkan.");
                break;
            }

            // Menyimpan data ke array 2 dimensi
            dataKRS[jumlahData][0] = nama;
            dataKRS[jumlahData][1] = nim;
            dataKRS[jumlahData][2] = kodeMK;
            dataKRS[jumlahData][3] = namaMK;
            dataKRS[jumlahData][4] = String.valueOf(sks);
            jumlahData++;

            totalSKS += sks;
            System.out.println("Data mata kuliah berhasil ditambahkan.");

            System.out.print("Tambah mata kuliah lain? (y/t): ");
            String pilihan = input.nextLine();
            if (pilihan.equalsIgnoreCase("t")) break;
        }

        System.out.println("Total SKS yang diambil: " + totalSKS + "\n");
    }

    public static void tampilkanDaftarKRS() {
        System.out.println("\n--- Tampilkan Daftar KRS Mahasiswa ---");
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = input.nextLine();

        int totalSKS = 0;
        System.out.println("Daftar KRS:");
        System.out.printf("%-10s %-15s %-10s %-30s %-5s%n", "NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS");

        for (int i = 0; i < jumlahData; i++) {
            if (dataKRS[i][1].equals(nim)) {
                System.out.printf("%-10s %-15s %-10s %-30s %-5s%n",
                        dataKRS[i][1], dataKRS[i][0], dataKRS[i][2], dataKRS[i][3], dataKRS[i][4]);
                totalSKS += Integer.parseInt(dataKRS[i][4]);
            }
        }

        if (totalSKS == 0) {
            System.out.println("Tidak ada data KRS untuk NIM tersebut.");
        } else {
            System.out.println("Total SKS: " + totalSKS + "\n");
        }
    }

    public static void analisisDataKRS() {
        System.out.println("\n--- Analisis Data KRS ---");
        int jumlahMahasiswaKurang20 = 0;
        boolean[] mahasiswaDihitung = new boolean[jumlahData];  // Array untuk menandai mahasiswa yang sudah dihitung
    
        for (int i = 0; i < jumlahData; i++) {
            String nim = dataKRS[i][1];
    
            // Cek apakah mahasiswa sudah dihitung
            if (!mahasiswaDihitung[i]) {
                int totalSKS = 0;
    
                // Hitung total SKS untuk mahasiswa tersebut
                for (int j = 0; j < jumlahData; j++) {
                    if (dataKRS[j][1].equals(nim)) {
                        totalSKS += Integer.parseInt(dataKRS[j][4]);
                    }
                }
    
                // Jika total SKS kurang dari 20, maka mahasiswa dihitung
                if (totalSKS < 20) {
                    jumlahMahasiswaKurang20++;
                }
    
                // Tandai mahasiswa ini sudah dihitung
                for (int k = 0; k < jumlahData; k++) {
                    if (dataKRS[k][1].equals(nim)) {
                        mahasiswaDihitung[k] = true;
                    }
                }
            }
        }
    
        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + jumlahMahasiswaKurang20 + "\n");
    }
}