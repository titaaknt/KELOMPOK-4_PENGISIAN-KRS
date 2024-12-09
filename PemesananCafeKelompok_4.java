import java.util.Scanner;

public class PemesananCafeKelompok_4 {
    static String[][] daftarPesanan = new String[10][4]; // [Nama, Meja, Detail Pesanan, Total Harga]
    static int jumlahPesanan = 0;
    static Scanner scanner = new Scanner(System.in);

    static String[][] menuKafe = {
        {"Kopi Hitam", "15000"},
        {"Latte", "22000"},
        {"Teh Tarik", "12000"},
        {"Mie Goreng", "18000"}
    };

    public static void main(String[] args) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Tambahkan Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (menu) {
                case 1:
                    simpanData();
                    break;
                case 2:
                    tampilkanData();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    lanjut = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi!");
                    break;
            }
        }
    }

    public static void simpanData() {
        if (jumlahPesanan >= daftarPesanan.length) {
            System.out.println("Pesanan telah mencapai kapasitas maksimum!");
            return;
        }

        System.out.print("Masukkan nama pelanggan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan nomor meja: ");
        String nomorMeja = scanner.nextLine();

        String detailPesanan = "";
        int totalHarga = 0;

        System.out.println("===== MENU KAFE =====");
        for (int i = 0; i < menuKafe.length; i++) {
            System.out.printf("%d. %s - Rp %s\n", i + 1, menuKafe[i][0], menuKafe[i][1]);
        }

        while (true) {
            System.out.print("Pilih menu (masukkan nomor menu, 0 untuk selesai): ");
            int pilihanMenu = scanner.nextInt();
            if (pilihanMenu == 0) break;

            if (pilihanMenu < 1 || pilihanMenu > menuKafe.length) {
                System.out.println("Pilihan menu tidak valid!");
                continue;
            }

            System.out.print("Masukkan jumlah item untuk " + menuKafe[pilihanMenu - 1][0] + ": ");
            int jumlah = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String namaMenu = menuKafe[pilihanMenu - 1][0];
            int hargaMenu = Integer.parseInt(menuKafe[pilihanMenu - 1][1]);
            detailPesanan += namaMenu + " x " + jumlah + " = Rp " + (hargaMenu * jumlah) + "\n";
            totalHarga += hargaMenu * jumlah;
        }

        daftarPesanan[jumlahPesanan][0] = nama;
        daftarPesanan[jumlahPesanan][1] = nomorMeja;
        daftarPesanan[jumlahPesanan][2] = detailPesanan;
        daftarPesanan[jumlahPesanan][3] = String.valueOf(totalHarga);
        jumlahPesanan++;

        System.out.println("Pesanan berhasil ditambahkan. Total: Rp " + totalHarga);
    }

    public static void tampilkanData() {
        if (jumlahPesanan == 0) {
            System.out.println("Tidak ada pesanan.");
            return;
        }

        System.out.println("===== DAFTAR PESANAN =====");
        for (int i = 0; i < jumlahPesanan; i++) {
            System.out.println("Nama: " + daftarPesanan[i][0]);
            System.out.println("Meja: " + daftarPesanan[i][1]);
            System.out.println("Pesanan:\n" + daftarPesanan[i][2]);
            System.out.println("Total: Rp " + daftarPesanan[i][3]);
            System.out.println("--------------------------");
        }
    }
}