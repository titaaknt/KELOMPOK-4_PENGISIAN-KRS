import java.util.Scanner;

public class PemesananCafeKel_4 {
    static Pesanan[] daftarPesanan = new Pesanan[10]; // Kapasitas maksimal pesanan
    static int jumlahPesanan = 0; // Menyimpan jumlah total pesanan
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Tambahkan Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Masukkan angka yang valid!");
                scanner.next(); // Consume input salah
                continue;
            }
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    tambahPesanan();
                    break;
                case 2:
                    tampilkanDaftarPesanan();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    scanner.close();
                    return; // Exit program setelah scanner ditutup
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void tambahPesanan() {
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = scanner.nextLine();
        System.out.print("Masukkan nomor meja: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Masukkan nomor meja yang valid!");
            scanner.next(); // Consume input salah
            return;
        }
        int nomorMeja = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Pesanan pesanan = new Pesanan(namaPelanggan, nomorMeja); // Membuat objek Pesanan

        // Menampilkan menu kafe hanya sekali di awal
        System.out.println("===== MENU KAFE =====");
        System.out.println("1. Kopi Hitam - Rp 15000");
        System.out.println("2. Latte - Rp 22000");
        System.out.println("3. Teh Tarik - Rp 12000");
        System.out.println("4. Mie Goreng - Rp 18000");

        while (true) {
            System.out.print("Pilih menu (masukkan nomor menu, atau 0 untuk selesai): ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Masukkan angka yang valid!");
                scanner.next(); // Consume input salah
                continue;
            }
            int pilihanMenu = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (pilihanMenu == 0) {
                break;
            }

            if (pilihanMenu < 1 || pilihanMenu > 4) {
                System.out.println("Pilihan menu tidak valid!");
                continue;
            }

            System.out.print("Masukkan jumlah item untuk " + getNamaMenu(pilihanMenu) + ": ");
            if (!scanner.hasNextInt()) {
                System.out.println("Masukkan jumlah item yang valid!");
                scanner.next(); // Consume input salah
                continue;
            }
            int jumlah = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            pesanan.tambahItem(getNamaMenu(pilihanMenu), getHargaMenu(pilihanMenu), jumlah); // Memanggil method pada objek pesanan
        }

        if (jumlahPesanan < daftarPesanan.length) {
            daftarPesanan[jumlahPesanan] = pesanan;
            jumlahPesanan++; // Increment jumlah pesanan
            System.out.println("\nPesanan berhasil ditambahkan");
            System.out.println("Total harga pesanan: Rp " + pesanan.totalHarga);
        } else {
            System.out.println("Pesanan telah mencapai batas kapasitas!");
        }
    }

    public static void tampilkanDaftarPesanan() {
        if (jumlahPesanan == 0) {
            System.out.println("Tidak ada pesanan yang tercatat.");
            return;
        }
    
        System.out.println("\n===== DAFTAR PESANAN =====");
        for (int i = 0; i < jumlahPesanan; i++) {
            daftarPesanan[i].tampilkanPesanan();
        }
    }
    
    public static String getNamaMenu(int pilihan) {
        switch (pilihan) {
            case 1: return "Kopi Hitam";
            case 2: return "Latte";
            case 3: return "Teh Tarik";
            case 4: return "Mie Goreng";
            default: return " ";
        }
    }

    public static int getHargaMenu(int pilihan) {
        switch (pilihan) {
            case 1: return 15000;
            case 2: return 22000;
            case 3: return 12000;
            case 4: return 18000;
            default: return 0;
        }
    }
}

// Kelas Pesanan
class Pesanan {
    String namaPelanggan;
    int nomorMeja;
    String[] namaItem = new String[10];
    int[] hargaItem = new int[10];
    int[] jumlahItem = new int[10];
    int totalHarga = 0;
    int itemCount = 0;

    public Pesanan(String namaPelanggan, int nomorMeja) {
        this.namaPelanggan = namaPelanggan;
        this.nomorMeja = nomorMeja;
    }

    public void tambahItem(String nama, int harga, int jumlah) {
        if (itemCount < 10) {
            namaItem[itemCount] = nama;
            hargaItem[itemCount] = harga;
            jumlahItem[itemCount] = jumlah;
            totalHarga += harga * jumlah;
            itemCount++;
        } else {
            System.out.println("Batas pesanan per pelanggan tercapai!");
        }
    }

    public void tampilkanPesanan() {
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Nomor Meja: " + nomorMeja);
        System.out.println("Detail Pesanan:");
        for (int i = 0; i < itemCount; i++) {
            System.out.println("- " + namaItem[i] + " x " + jumlahItem[i] + " = Rp " + (hargaItem[i] * jumlahItem[i]));
        }
        System.out.println("Total Harga Pesanan: Rp " + totalHarga);
        System.out.println("--------------------------\n");
    }
}
