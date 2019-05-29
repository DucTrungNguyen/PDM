/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Administrator
 */
import java.util.Scanner;

import java.util.Scanner;

/**
 *
 * @author PHAM VIET ANH
 */
public class PhanSoHuuTi {

    private static int tu;
    private static int mau;

    public static int getTu() {
        return tu;
    }

    public static void setTu(int tu) {
        PhanSoHuuTi.tu = tu;
    }

    public static int getMau() {
        return mau;
    }

    public static void setMau(int mau) {
        PhanSoHuuTi.mau = mau;
    }

    public PhanSoHuuTi() {
    }

    public PhanSoHuuTi(int tu, int mau) {

    }

    public PhanSoHuuTi nhapPS( int x ) {
        Scanner input = new Scanner(System.in);
        System.out.println("ThÃ´ng tin phÃ¢n sá»‘ thá»©: " + x);
        System.out.println("Nháº­p vÃ o tá»­ sá»‘");
        this.tu = input.nextInt();
        System.out.println("Nháº­p vÃ o máº«u sá»‘");
        this.mau = input.nextInt();
        PhanSoHuuTi ps = new PhanSoHuuTi(tu, mau);
        return ps;

    }

    public static void hienthiPS(PhanSoHuuTi ps) {
        System.out.println(tu + "/" + mau);
    }

    public static int UCLN(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return (a);
    }

    public static PhanSoHuuTi psToiGian(PhanSoHuuTi ps) {
        PhanSoHuuTi phanso = new PhanSoHuuTi();
        phanso.tu = ps.tu   / UCLN (ps.tu, ps.mau);
        phanso.mau = ps.mau / UCLN (ps.tu, ps.mau);
        return phanso;

    }

    public static PhanSoHuuTi nhanPS(PhanSoHuuTi ps1, PhanSoHuuTi ps2) {
        PhanSoHuuTi phanso = new PhanSoHuuTi();
        phanso.tu = ps1.tu * ps2.tu;
        phanso.mau = ps1.mau * ps2.mau;
        
        phanso = psToiGian(phanso);
        
        return phanso;

    }

    public static void main(String[] args) {
        PhanSoHuuTi ps1 = new PhanSoHuuTi();
        PhanSoHuuTi ps2 = new PhanSoHuuTi();
        ps1.nhapPS(1);
        ps1.hienthiPS(ps1);
        
        ps2.nhapPS(2);
        ps2.hienthiPS(ps2);
        System.out.println("TÃ­ch hai phÃ¢n sá»‘: ");
        hienthiPS(nhanPS(ps1, ps2));

    }

}

